package model;

import bPlusTree.BPlusTree;

import java.util.ArrayList;
import java.util.Observable;

public class SearchManager extends Observable {
    private BPlusTree<Integer,ArrayList<String>> wordsOccurrenceTree;
    private AVLTree<Word> urlTree;
    private AVLTree<Word> wordTree;
    private int indexedWords;
    private int comparisons;
    private int treeSize;

    public SearchManager(AVLTree<Word> pWordTree, BPlusTree<Integer,ArrayList<String>> pOccurrenceTree,AVLTree<Word> pUrlTree,int pIndexedWords){
        wordTree = pWordTree;
        wordsOccurrenceTree = pOccurrenceTree;
        urlTree = pUrlTree;
        indexedWords = pIndexedWords;
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
            comparisons = wordTree.getComparisons();
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
                comparisons = wordsOccurrenceTree.getComparisons();
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
            comparisons = urlTree.getComparisons();
            setChanged();
            notifyObservers(retList);
        }
    }

    public int getIndexedWords() {
        return indexedWords;
    }

    public int getComparisons() {
        return comparisons;
    }

    public static void main(String[] args) {


    }

}
