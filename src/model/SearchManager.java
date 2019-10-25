package model;

import bPlusTree.BPlusTree;

import java.util.ArrayList;
import java.util.Observable;

public class SearchManager extends Observable {
    BPlusTree<Integer,ArrayList<String>> wordsOccurrenceTree;
    AVLTree<String> urlTree;
    AVLTree<Word> wordTree;
    WebLoader loader;

    SearchManager(){

    }

    public void searchWord(String pText){
        if(!pText.isEmpty()){
            ArrayList<ArrayList<String>> retList = new ArrayList<ArrayList<String>>();
            Word wordContainer = new Word("");
            String[] words = parseWords(pText);
            for (String word: words) {
                wordContainer.setWord(word);
                retList.add(wordTree.get(wordContainer).getList());
            }
            setChanged();
            notifyObservers(getIntersections(retList));
        }
    }
    private String[] parseWords(String pText){
        String delims = "[ ]+";
        return pText.split(delims);
    }
    private ArrayList<String> getIntersections(ArrayList<ArrayList<String>> pList){
        if(pList.contains(null)){
            return new ArrayList<String>();
        }
        else{
            ArrayList<String> ret = new ArrayList<String>();
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


}
