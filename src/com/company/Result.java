package com.company;

public interface Result<T extends Result, K> extends Iterable<K> {
    void add(char[] word);

    T merge(final T r2);

    // get the result to print to console or file
    String print(int linesNumber);

    char[] getPrimer();

    void setPrimer(char[] primer);
}
