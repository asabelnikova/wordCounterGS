package com.company;

import java.util.Iterator;

public class WordCountingResult implements Result<WordCountingResult, WordCount> {
    private final Sort sort = new Sort();

    private int size = 0;

    // Using hashmap here will be more efficient. This variant is simplier
    private WordCount[] result = new WordCount[10];

    // save ending of text part, if it ends on a letter. we count it in next text part
    // it not accurate calculation, I didn't tests it quite well - don't have a time
    private char[] primer = new char[0];

    @Override
    public String print(int linesNumber) {
        sort.sort(result, new WordCountComparator());

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(linesNumber, result.length); i++) {
            sb.append(result[i].getCount())
                    .append(" ")
                    .append(new String(result[i].getWord()))
                    .append("\n");
        }
        return sb.toString();
    }

    @Override
    public char[] getPrimer() {
        return primer;
    }

    @Override
    public void setPrimer(final char[] primer) {
        this.primer = primer;
    }

    @Override
    public void add(final char[] word) {
        add(new WordCount(word));
    }

    @Override
    public WordCountingResult merge(final WordCountingResult toMerge) {
        for (final WordCount w : toMerge) {
            add(w);
        }
        return this;
    }

    @Override
    public Iterator<WordCount> iterator() {
        return new Iterator<WordCount>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public WordCount next() {
                return result[pos++];
            }
        };
    }

    private void add(WordCount w) {
        for (int i = 0; i < size; i++) {
            if (result[i].equals(w)) {
                result[i].increaseCount(w.getCount());
                return;
            }
        }
        if (size >= result.length) {
            resize();
        }
        result[size++] = w;
    }

    private void resize() {
        WordCount[] newResultArray = new WordCount[result.length * 2];
        System.arraycopy(result, 0, newResultArray, 0, result.length);
        result = newResultArray;
    }
}
