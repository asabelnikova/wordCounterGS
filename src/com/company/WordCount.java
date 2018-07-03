package com.company;

import java.util.Arrays;

/*
    Here we store founded words and its count
 */
class WordCount {
    private char[] word;
    private int count = 1;

    WordCount(final char[] word) {
        this.word = word;
    }

    void increaseCount(int number) {
        count += number;
    }

    char[] getWord() {
        return word;
    }

    int getCount() {
        return count;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof WordCount))
            return false;

        return Arrays.equals(this.word, ((WordCount) obj).getWord());
    }
}
