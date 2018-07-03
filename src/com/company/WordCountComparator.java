package com.company;

import java.util.Comparator;

/*
    Comparator for sorting final words statistics
 */
public class WordCountComparator implements Comparator<WordCount> {
    @Override
    public int compare(final WordCount o1, final WordCount o2) {
        if (o1 == null && o2 != null)
            return -1;
        else if (o1 != null && o2 == null)
            return 1;
        else if (o1 == null)
            return 0;
        else
            return Integer.compare(o1.getCount(), o2.getCount());
    }
}
