package bPlusTree;

import java.util.ArrayList;

public class BPlusTree<K extends Comparable<K>,V> {
    private int order;
    private BPlusNode<K,V> root;

    public BPlusTree(int pOrder){
        order = pOrder;
        root = new BPlusNode<K,V>(true);
    }
    public BPlusNode getRoot() {
        return root;
    }
    public void setRoot(BPlusNode root) {
        this.root = root;
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
                break;
            }
        }
        return partitionCheck(ret);
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
        return new BPlusNode<K,V>(pRoot.getKeys().get(median-1),pRoot,newNode);
    }
    private BPlusNode<K,V> partitionInner(BPlusNode<K,V> pRoot, int median){
        ArrayList<K> newKeyList = new ArrayList<K>(pRoot.getKeys());
        ArrayList<BPlusNode<K, V>> newChildList = new ArrayList<BPlusNode<K, V>>(pRoot.getChildren());
        splitArrays(pRoot.getKeys(),newKeyList,median);
        splitArrays(pRoot.getChildren(),newChildList,median);
        BPlusNode<K,V> newNode = new BPlusNode<K,V>(newKeyList,newChildList);
        return new BPlusNode<K,V>(pRoot.getKeys().get(median-1),pRoot,newNode);
    }
    private void splitArrays(ArrayList pStart, ArrayList pEnd,int pMedian){
        pStart.subList(pMedian,pEnd.size()).clear();
        pEnd.subList(0,pMedian).clear();
    }
    public String toString(){
        return "";
    }

    public static void main(String[] args) {
        BPlusTree<Integer,String> tree = new BPlusTree<Integer,String>(4);
        for (Integer i = 0; i<16;i++){
            tree.add(i,i.toString());
        }
        System.out.println(tree.getRoot().getKeys().toString());
    }
}
