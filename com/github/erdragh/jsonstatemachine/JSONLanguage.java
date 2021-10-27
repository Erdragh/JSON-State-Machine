package com.github.erdragh.jsonstatemachine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.github.erdragh.finitestatemachine.Language;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONLanguage implements Language {
    /**
     * This is a list of all chars in the alphabet of this language.
     * It can be used to get the index of a char for looking it up in the map.
     */
    private char[] alphabet; // = {'.','0','1','2','3','4','5','6','7','8','9'};
    /**
     * These are the final states. If the given string ends and the current state is one of these, the test was successful
     */
    private int[] finalStates; // = {4, 5, 6};
    /**
     * This is a two-dimensional map allocating a new state for every state+charIndex pair.
     * Organized: [state][charIndex]
     */
    private int[][] map; /*= {
        //{'.','0','1','2','3','4','5','6','7','8','9'}
        {-1, -1,  5,  5,  5,  5,  5,  5,  5,  5,  5},   //state 0
        {-1,  2,  2,  2,  2,  2,  2,  2,  2,  2,  2},   //state 1
        {-1,  3,  3,  3,  3,  3,  3,  3,  3,  3,  3},   //state 2
        {-1,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4},   //state 3
        { 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},   //state 4
        { 1,  6,  6,  6,  6,  6,  6,  6,  6,  6,  6},   //state 5
        { 1,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4},   //state 6
    };*/

    /**
     * This constructor takes a .json file as input and tries to parse it as a language
     * @param file
     * the .json file to be parsed
     */
    public JSONLanguage(File file) {
        //This string is initiated as empty, so it can be tested wether it was read.
        String jsonString = "";
        //This try-catch block tries to read a input .json file (does not have to end on .json) and prepare it for use with the json library.
        try {
            jsonString = Files.readString(Path.of(file.getAbsolutePath()));
        } catch (IOException e) {
            System.err.println("File couldn't be found or there was an error while reading it");
            e.printStackTrace();
        }
        //This returns out of the constructor if the string is empty, because if it is there is no language in the file in any way.
        if (jsonString.equals("")) {
            System.err.println("File is empty or there was an error while reading it");
            return;
        }

        //puts the string into a class for the json library
        JSONObject obj = new JSONObject(jsonString);

        //this tries to get the alphabet out of the json file.
        JSONArray alphabetJSON = obj.getJSONArray("alphabet");
        //by first initiating the alphabet array
        alphabet = new char[alphabetJSON.length()];
        //and then looping through the parts of the alphabet JSONArray and putting them into the actual alphabet array
        for (int i = 0; i < alphabetJSON.length(); i++) {
            alphabet[i] = alphabetJSON.getString(i).charAt(0);
            // Logger.println("reading alphabet character " + i + " which is '" + alphabet[i] + "'");
        }

        //this tries to get the finalStates out of the json file.
        JSONArray finalStatesJSON = obj.getJSONArray("finalStates");
        //by first initiating the finalStates array
        finalStates = new int[finalStatesJSON.length()];
        //and then looping through the parts of the finalStates JSONArray and putting them into the actual finalStates array.
        for (int i = 0; i < finalStatesJSON.length(); i++) {
            finalStates[i] = finalStatesJSON.getInt(i);
        }

        //this tries to get the map out of the json file.
        JSONArray mapJSON = obj.getJSONArray("map");
        //by first initiating the map array with its second dimension's size being defined by the length of the first subarray of the JSONArray
        map = new int[mapJSON.length()][mapJSON.getJSONArray(0).length()];
        //and then looping through the first dimension and then through the second dimension and adding the parts to the actual map array.
        for (int i = 0; i < mapJSON.length(); i++) {
            JSONArray mapJSONlayer = mapJSON.getJSONArray(i);
            for (int j = 0; j < mapJSONlayer.length(); j++) {
                map[i][j] = mapJSONlayer.getInt(j);
            }
        }
    }

    /**
     * @return
     * the alphabet of the language as an array
     */
    public char[] getAlphabet() {
        return alphabet;
    }
    /**
     * @return
     * the final States of the language as an array
     */
    public int[] getFinalStates() {
        return finalStates;
    }
    /**
     * @return
     * the state map of the language as a two-dimensional array
     */
    public int[][] getMap() {
        return map;
    }
}
