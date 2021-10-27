package com.github.erdragh.jsonstatemachine;

import java.io.File;
import java.io.IOException;

import com.github.erdragh.finitestatemachine.Language;
import com.github.erdragh.finitestatemachine.StateMachine;

public class Main {
    private static StateMachine stateMachine;
    private static Language language;

    public static void main(String[] args) {
        try {
            Logger.init(false, new File("./log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (args.length != 2) {
            Logger.println("You have to give two arguments: /path/to/file.json teststring");
            return;
        }
        String path = args[0];
        String test = args[1];
        Logger.println("Testing if String '" + test + "' is part of the language defined in '" + path + "'");
        File file = new File(path);
        language = new JSONLanguage(file);
        stateMachine = new StateMachine(language);
        boolean result = stateMachine.testString(test);
        Logger.println("Result: " + result);
        System.out.println(result);
        try {
            Logger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
