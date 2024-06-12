package com.github.zipcodewilmington.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {
    private static final char DEFAULT_SEPARATOR = ',';  // (1)

    public static void writeLine(Writer w, List<String> values) throws IOException {
        boolean first = true;

        StringBuilder sb = new StringBuilder();

        for (String value : values) {
            if (!first) {
                sb.append(DEFAULT_SEPARATOR);
            }
            sb.append(value);
            first = false;
        }
        sb.append("\n");

        w.append(sb.toString());  // (4)
    }
}