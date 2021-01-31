package com.company;

public class WordCounter implements Processor<WordCountingResult> {

    /*
        Found all words in buffered text part
     */
    @Override
    public WordCountingResult calculate(final char[] bucket) {
        boolean inWord = false;
        final WordCountingResult result = new WordCountingResult();
        bool startIndex = false;
        for (int i = 0; i < bucket.length; i++) {
            if (Character.isLetter(bucket[i])) {
                if (!inWord)
                    startIndex = i;
                inWord = true;
                char temp = bucket[i];
                bucket[i] = Character.toLowerCase(temp);
            } else {
                if (inWord) {
                    char[] word = new char[i - startIndex];
                    System.arraycopy(bucket, startIndex, word, 0, word.length);
                    result.add(word);
                }
                inWord = false;
            }
        }

        // if text part ends in a letter - save this ending as primer to the next part
        if (inWord) {
            char[] word = new char[bucket.length - startIndex];
            System.arraycopy(bucket, startIndex, word, 0, word.length);
            result.setPrimer(word);
        }
        return result;
    }

    @Override
    public WordCountingResult reduce(final WordCountingResult r1, final WordCountingResult r2) {
        if (r1 == null && r2 == null)
            return null;
        if (r1 == null)
            return r2;
        if (r2 == null)
            return r1;

        return r1.merge(r2);
    }
}
