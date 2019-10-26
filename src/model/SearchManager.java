package model;

import bPlusTree.BPlusTree;

import java.util.ArrayList;
import java.util.Observable;

public class SearchManager extends Observable {
    BPlusTree<Integer,ArrayList<String>> wordsOccurrenceTree;
    AVLTree<String> urlTree;
    AVLTree<Word> wordTree;
    WebLoader loader;

    SearchManager(AVLTree<Word> pWordTree){
        wordTree = pWordTree;
    }

    public void searchWord(String pText){
        if(!pText.isEmpty()){
            ArrayList<ArrayList<String>> retList = new ArrayList<ArrayList<String>>();
            Word wordContainer = new Word("");
            String[] words = parseWords(pText);
            Word retWord;
            for (String word: words) {
                wordContainer.setWord(word);
                retWord = wordTree.get(wordContainer);
                if(retWord == null){
                    retList.add(null);
                }
                else{
                    retList.add(wordTree.get(wordContainer).getList());
                }
            }
            //setChanged();
            //notifyObservers(getIntersections(retList));
            System.out.println(getIntersections(retList));
        }
    }
    private String[] parseWords(String pText){
        pText = pText.toLowerCase();
        String delims = "[ ]+";
        return pText.split(delims);
    }
    private ArrayList<String> getIntersections(ArrayList<ArrayList<String>> pList){
        if(pList.contains(null)){
            return new ArrayList<String>();
        }
        else{
            ArrayList<String> ret = pList.get(0);
            for (int pListIndex = 1; pListIndex < pList.size();pListIndex++){
                ret = intersection(ret,pList.get(pListIndex));
            }
            return ret;
        }

    }
    private ArrayList<String> intersection(ArrayList<String> pList1, ArrayList<String> pList2){
        ArrayList<String> ret = new ArrayList<String>();
        for (String word:
             pList1) {
            if(pList2.contains(word)){
                ret.add(word);
            }
        }
        return ret;
    }
    void searchRange(String pText){

    }
    void searchDomain(String pText){
        
    }
    public static void main(String[] args) {
        AVLTree<Word> wordTree = new AVLTree<Word>();
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        s1.add("A");
        s1.add("B");
        s2.add("A");
        s2.add("B");
        wordTree.add(new Word("hola",s1));
        wordTree.add(new Word("buenas",s2));
        wordTree.add(new Word("adios","C"));
        SearchManager manager = new SearchManager(wordTree);
        manager.searchWord("Hola lol");
    }

}
