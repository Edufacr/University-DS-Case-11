package bPlusTree;

import java.util.ArrayList;

public class BPlusNode<K extends Comparable<K>,V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;
    private  ArrayList<BPlusNode<K,V>> children;
    private BPlusNode<K,V> next;
    private boolean IsLeaf;

    BPlusNode(ArrayList<V> pValues, ArrayList<K> pKeys, ArrayList<BPlusNode<K,V>> children, BPlusNode<K, V> pNext, boolean pIsLeaf){
        this.values = pValues;
        this.children = children;
        this.next = pNext;
        IsLeaf = pIsLeaf;
        keys = pKeys;
    }
    BPlusNode(K pKey,BPlusNode<K,V> pChild1,BPlusNode<K,V> pChild2){
        this(false);
        keys.add(pKey);
        children.add(pChild1);
        children.add(pChild2);
    }
    BPlusNode(ArrayList<K> pKeys,ArrayList<BPlusNode<K,V>> pChildren){
        IsLeaf = false;
        keys = pKeys;
        children = pChildren;
    }
    BPlusNode(boolean pIsLeaf){
        if(pIsLeaf){
            this.values = new ArrayList<V>();
        }
        else{
            this.children = new ArrayList<BPlusNode<K,V>>();
        }
        this.next = null;
        IsLeaf = pIsLeaf;
        keys = new ArrayList<K>();
    }

    public ArrayList<K> getKeys() {
        return keys;
    }

    public ArrayList<V> getValues() {
        return values;
    }

    public BPlusNode<K, V> getNext() {
        return next;
    }

    public void setNext(BPlusNode<K, V> next) {
        this.next = next;
    }

    public boolean isLeaf() {
        return IsLeaf;
    }

    public ArrayList<BPlusNode<K,V>> getChildren() {
        return children;
    }
    public void addKey(K pKey,int pos){
        getKeys().add(pos,pKey);
    }
    public void addValue(V pValue,int pos){
        getValues().add(pos,pValue);
    }
}
