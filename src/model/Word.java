package model;

import java.util.ArrayList;

public class Word implements Comparable<Word> {
    private String word;
    private ArrayList<String> list;
    Word(String pWord){
        word = pWord;
        list = new ArrayList<String>();
    }
    @Override
    public int compareTo(Word word) {
        return 0;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
