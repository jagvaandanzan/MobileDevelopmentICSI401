package model;

import java.io.Serializable;

public class Word implements Serializable {
    private String sourceWord;
    private String targetWord;
    private int id;

    public Word() {

    }

    public Word(String sourceWord, String targetWord){
        setSourceWord(sourceWord);
        setTargetWord(targetWord);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }
}
