package ru.job4j.isp.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> englishDictionary;

    public Dictionary() {
        englishDictionary = new HashMap<>();
    }

    public boolean addNewWord(String englishWord, String russianWord) {
        if (englishDictionary.containsKey(englishWord.toLowerCase())) {
            return false;
        } else {
            englishDictionary.put(englishWord.toLowerCase(), russianWord.toLowerCase());
            return true;
        }
    }

    public String translateWord(String englishWord) {
        if (englishWord == null) {
            return "";
        }
        if (englishDictionary.containsKey(englishWord.toLowerCase())) {
            return englishDictionary.get(englishWord.toLowerCase());
        }
        return null;
    }

    public String translateSentence(String sentence) {

        StringBuilder phraseInRussian = new StringBuilder();

        String[] res = sentence.split("\\s*(\\s|,|!|\\.)\\s*");

        for (int i = 0; i < res.length; i++) {
            if (translateWord(res[i]) == null) {
                phraseInRussian.append(res[i] + " ");
            } else {
                phraseInRussian.append(translateWord(res[i]) + " ");
            }
        }

        return phraseInRussian.toString();
    }

    public String readText() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        System.out.println("Enter the string sentence.");
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }
}
