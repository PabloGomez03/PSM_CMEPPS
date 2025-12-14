/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psm;

import java.util.HashSet;
import java.util.Set;

public class WordProcessor {

    private String secretWord;
    private Set<Character> guessedLetters;

    public WordProcessor(String word) {
        this.secretWord = word.toUpperCase();
        this.guessedLetters = new HashSet<>();
    }

    public boolean guessLetter(char letter) {
        char upperLetter = Character.toUpperCase(letter);
        if (guessedLetters.contains(upperLetter)) {
            return false; 
        }
        guessedLetters.add(upperLetter);
        return secretWord.indexOf(upperLetter) >= 0;
    }

    public boolean isComplete() {
        for (char c : secretWord.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public String getMaskedWord() {
        StringBuilder sb = new StringBuilder();
        
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public String getActualWord() {
        return secretWord;
    }
}
