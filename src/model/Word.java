package model;

import java.util.ArrayList;

public class Word implements Comparable<Word> {
    private String word;
    private ArrayList<String> list;
    public Word(String pWord){
        word = pWord;
        list = new ArrayList<String>();
    }
    public Word(String pWord,String s){
        word = pWord;
        list = new ArrayList<String>();
        list.add(s);
    }
    public Word(String pWord, ArrayList<String> s){
        word = pWord;
        list = s;
    }
    @Override
    public int compareTo(Word word) {
        return getWord().compareTo(word.getWord());
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

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
