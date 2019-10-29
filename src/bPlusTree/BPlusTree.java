package bPlusTree;

import java.util.ArrayList;

public class BPlusTree<K extends Comparable<K>,V> {
    private int order;
    private BPlusNode<K,V> root;
    private BPlusNode<K,V> first;
    private int comparisons;

    public BPlusTree(int pOrder){
        order = pOrder;
        root = new BPlusNode<K,V>(true);
        first = root;
    }
    public BPlusNode getRoot() {
        return root;
    }
    public void setRoot(BPlusNode root) {
        this.root = root;
    }
    public BPlusNode getFirst(){
        return getFirst(root);
    }
    public BPlusNode getFirst(BPlusNode<K,V> pNode){
        if(pNode.isLeaf()){
            return pNode;
        }
        else{
            return getFirst(pNode.getChildren().get(0));
        }
    }
    public void add(K pKey, V pValue){
        root = add(pKey,pValue,root);
    }
    private BPlusNode<K,V> add(K pKey, V pValue, BPlusNode<K,V> pRoot){
        if(pRoot.isLeaf()){
            return addToLeaf(pKey,pValue,pRoot);
        }
        else{
            return addToInner(pKey,pValue,pRoot);
        }
    }
    private BPlusNode<K,V> addToLeaf(K pKey, V pValue, BPlusNode<K,V> pRoot){
        for (int keyIndex = 0; keyIndex <= pRoot.getKeys().size();keyIndex++){
            if(keyIndex == pRoot.getKeys().size() || pKey.compareTo(pRoot.getKeys().get(keyIndex)) < 0){
                pRoot.addKey(pKey,keyIndex);
                pRoot.addValue(pValue,keyIndex);
                break;
            }
        }
        return partitionCheck(pRoot);
    }
    private BPlusNode<K,V> addToInner(K pKey, V pValue, BPlusNode<K,V> pRoot){
        BPlusNode<K,V> ret = null;
        for (int keyIndex = 0; keyIndex <= pRoot.getKeys().size();keyIndex++){
            if(keyIndex == pRoot.getKeys().size() || pKey.compareTo(pRoot.getKeys().get(keyIndex)) < 0){
                ret = add(pKey,pValue,pRoot.getChildren().get(keyIndex));
                if(ret != pRoot.getChildren().get(keyIndex)){
                    pRoot.insertAt(keyIndex,ret);
                }
                break;
            }
        }
        return partitionCheck(pRoot);
    }
    private BPlusNode<K,V> partitionCheck(BPlusNode<K,V> pRoot){
        if(pRoot.getKeys().size() >= order){
            int median = order /2;
            if(pRoot.isLeaf()){
                return partitionLeaf(pRoot,median);
            }
            else{
                return partitionInner(pRoot,median);
            }
        }
        else{
            return pRoot;
        }
    }
    private BPlusNode<K,V> partitionLeaf(BPlusNode<K,V> pRoot, int median){
        ArrayList<K> newKeyList = new ArrayList<K>(pRoot.getKeys());
        ArrayList<V> newValueList = new ArrayList<V>(pRoot.getValues());
        splitArrays(pRoot.getKeys(),newKeyList,median);
        splitArrays(pRoot.getValues(),newValueList,median);
        BPlusNode<K,V> newNode = new BPlusNode<K,V>(newKeyList,newValueList,pRoot.getNext());
        pRoot.setNext(newNode);
        return new BPlusNode<K,V>(newNode.getKeys().get(0),pRoot,newNode);
    }
    private BPlusNode<K,V> partitionInner(BPlusNode<K,V> pRoot, int median){
        ArrayList<K> newKeyList = new ArrayList<K>(pRoot.getKeys());
        ArrayList<BPlusNode<K, V>> newChildList = new ArrayList<BPlusNode<K, V>>(pRoot.getChildren());
        splitArrays(pRoot.getKeys(),newKeyList,median);
        splitChildrenArray(pRoot.getChildren(),newChildList,median);
        BPlusNode<K,V> newNode = new BPlusNode<K,V>(newKeyList,newChildList);
        K key = newNode.getKeys().get(0);
        newNode.getKeys().remove(0);
        return new BPlusNode<K,V>(key,pRoot,newNode);
    }
    private void splitArrays(ArrayList pStart, ArrayList pEnd,int pMedian){
        pStart.subList(pMedian,pEnd.size()).clear();
        pEnd.subList(0,pMedian).clear();
    }
    private void splitChildrenArray(ArrayList pStart,ArrayList pEnd,int pMedian){
        pStart.subList(pMedian+1,pEnd.size()).clear();
        pEnd.subList(0,pMedian+1).clear();
    }
    public BPlusNode<K,V> searchNode(K pKey){
    	this.comparisons = 0;
        return searchNode(pKey,getRoot());
    }
    private BPlusNode<K,V> searchNode(K pKey,BPlusNode<K,V> pRoot){
        if(pRoot.isLeaf()){
            return pRoot;
        }
        else {
        	this.comparisons++;
            return searchInnerNode(pKey,pRoot);
        }
    }
    private BPlusNode<K,V>searchInnerNode(K pKey, BPlusNode<K,V> pRoot){
        for (int keyIndex = 0; keyIndex <= pRoot.getKeys().size();keyIndex++){
            if(keyIndex == pRoot.getKeys().size() || pKey.compareTo(pRoot.getKeys().get(keyIndex)) < 0){
            	this.comparisons++;
                return searchNode(pKey,pRoot.getChildren().get(keyIndex));
            }
        }
        return null;
    }
    public ArrayList<V> searchKeyRange(K pLowerKey,K pHigherKey){
        BPlusNode<K,V> node = searchNode(pLowerKey);
        ArrayList<V> array = new ArrayList<V>();
        while(node != null){
            node = node.SearchKeyRange(pLowerKey,pHigherKey,array);
        }
        return array;
    }
    public V searchValue(K pKey){
        BPlusNode<K,V> node = searchNode(pKey);
        for (int keyIndex = 0; keyIndex < node.getKeys().size(); keyIndex++){
            if(node.getKeys().get(keyIndex).compareTo(pKey) == 0){
                return node.getValues().get(keyIndex);
            }
        }
        return null;
    }
    public int getComparisons() {
    	return this.comparisons;
    }
    @Override
    public String toString(){
        BPlusNode<K,V> tmp = getFirst();
        String ret = "";
        while(tmp != null){
            ret = ret + tmp.getKeys().toString();
            tmp = tmp.getNext();
        }
        return ret;
    }
    public int getSize(){
        int[] ret = new int[1];
        getSize(getRoot(),ret);
        return ret[0];
    }
    public void getSize(BPlusNode<K,V> pRoot, int[]pNum){
        pNum[0]++;
        if(!pRoot.isLeaf()){
            for (BPlusNode<K,V> node:
                    pRoot.getChildren()) {
                getSize(node,pNum);
            }
        }

    }
    public static void main(String[] args) {
        BPlusTree<Integer,Integer> tree = new BPlusTree<Integer,Integer>(4);
//        for (Integer i = 0; i<16;i++){
//            tree.add(i,i);
//        }
        System.out.println(tree.toString());
//        System.out.println(tree.searchKeyRange(4,10).toString());
    }
}
