package model;

import bPlusTree.BPlusTree;

import java.util.ArrayList;
import java.util.Observable;

public class SearchManager extends Observable {
    BPlusTree<Integer,ArrayList<String>> wordsOccurrenceTree;
    AVLTree<Word> urlTree;
    AVLTree<Word> wordTree;
    TreeLoader loader;

    public SearchManager(AVLTree<Word> pWordTree, BPlusTree<Integer,ArrayList<String>> pOccurrenceTree,AVLTree<Word> pUrlTree){
        wordTree = pWordTree;
        wordsOccurrenceTree = pOccurrenceTree;
        urlTree = pUrlTree;
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
            setChanged();
            notifyObservers(getIntersections(retList));
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
    public void searchRange(String pText){
        if(!pText.isEmpty()){
            String[] numbers = parseWords(pText);
            if(numbers.length >= 2){
                int lowerKey = Integer.parseInt(numbers[0]);
                int highKey = Integer.parseInt(numbers[1]);
                ArrayList<String> retList = getUnion(wordsOccurrenceTree.searchKeyRange(lowerKey,highKey)); //getUnion
                setChanged();
                notifyObservers(retList);
            }
        }
    }
    private ArrayList<String> getUnion(ArrayList<ArrayList<String>> pList){
        if(pList.contains(null)){
            return new ArrayList<String>();
        }
        else{
            ArrayList<String> ret = pList.get(0);
            for (int pListIndex = 1; pListIndex < pList.size();pListIndex++){
                ret = union(ret,pList.get(pListIndex));
            }
            return ret;
        }
    }
    private ArrayList<String> union(ArrayList<String> pList1, ArrayList<String> pList2){
        ArrayList<String> ret = new ArrayList<String>(pList1);
        for (String word:
                pList2) {
            if(ret.contains(word)){
                continue;
            }
            ret.add(word);
        }
        return ret;
    }
    public void searchDomain(String pText){
        if(!pText.isEmpty()){
            ArrayList<String> retList = new ArrayList<String>();
            String[] words = parseWords(pText);
            Word wordContainer = new Word(words[0]);
            Word retWord;
            retWord = urlTree.get(wordContainer);
            if(retWord == null){
                retList.add(null);
            }
            else{
                retList.addAll(retWord.getList());
            }
            setChanged();
            notifyObservers(retList);
        }
    }
    public static void main(String[] args) {


    }

}
