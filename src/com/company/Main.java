package com.company;

public class Main {

    /*
       Args:
       1: file's path to process
     */

    public static void main(String[] args) {
        final WordCounter calculator = new WordCounter();
        final FileProcessor fileProcessor = new FileProcessor();
        log(fileProcessor.readAndProcessFile(args[0], calculator).print(20));
    }

    static void log(Object obj) {
        System.out.println(String.valueOf(obj));
    }
}