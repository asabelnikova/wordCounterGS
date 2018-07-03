package com.company;

public class WordCounterTest {

    private static final String TEST_FILE_NAME = "resources/mobydick.txt";

    public static void main(String[] args) {
        final WordCounter calculator = new WordCounter();
        final FileProcessor fileProcessor = new FileProcessor();
        final String result = fileProcessor.readAndProcessFile(TEST_FILE_NAME, calculator).print(20);
        if (!expected.equals(result)) {
            Main.log(result);
        } else {
            Main.log("Complete!");
        }
    }

    // result from bash program
    private static final String expected = "4284 the\n" +
            "2192 and\n" +
            "2185 of\n" +
            "1861 a\n" +
            "1685 to\n" +
            "1366 in\n" +
            "1056 i\n" +
            "1024 that\n" +
            "889 his\n" +
            "821 it\n" +
            "783 he\n" +
            "616 but\n" +
            "603 was\n" +
            "595 with\n" +
            "577 s\n" +
            "564 is\n" +
            "551 for\n" +
            "542 all\n" +
            "541 as\n" +
            "458 at\n";
}
