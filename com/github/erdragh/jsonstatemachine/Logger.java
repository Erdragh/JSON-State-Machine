package com.github.erdragh.jsonstatemachine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static boolean printToConsole = false;
    private static File logFile;
    private static BufferedWriter writer;

    public static void init(boolean printToConsoleIn, File logFileIn) throws IOException {
        printToConsole = printToConsoleIn;
        logFile = logFileIn;
        writer = new BufferedWriter(new FileWriter(logFile));
    }

    public static void close() throws IOException {
        writer.close();
    }

    public static void println(Object obj) {
        if (printToConsole) System.out.println(obj);
        String tempString = obj.toString();
        try {
            writer.write(tempString + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
