package com.company;

public interface Processor<T extends Result> {
    T calculate(final char[] bucket);

    T reduce(T t1, T t2);
}
