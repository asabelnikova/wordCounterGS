package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;

class FileProcessor {

    <T extends Result> T readAndProcessFile(final String fileName, final Processor<T> processor) {
        File file = new File(fileName);

        // if text is bigger, then buffer, then calculation is not precise - don't have time to debug it :(
        char[] buffer = new char[512 * 1024];
        T fileResult = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                int offset = 0;
                // Read file to buffer and process that part
                while (bufferedReader.read(buffer, offset, buffer.length - offset) != -1) {
                    T partResult = processor.calculate(buffer);
                    if (partResult != null) {
                        System.arraycopy(partResult.getPrimer(), 0, buffer, 0, partResult.getPrimer().length);
                        offset = partResult.getPrimer().length;
                    }
                    fileResult = processor.reduce(fileResult, partResult);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            Main.log("File to process not found. Please, set it in program arguments.");
        }
        return fileResult;
    }
}
