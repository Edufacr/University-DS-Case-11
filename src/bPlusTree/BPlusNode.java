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
    BPlusNode(ArrayList<K> pKeys,ArrayList<V> pValues,BPlusNode<K,V> pNext){
        IsLeaf = true;
        keys = pKeys;
        values = pValues;
        next = pNext;
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
    public void addKey(K pKey,int pPos){
        getKeys().add(pPos,pKey);
    }
    public void addValue(V pValue,int pPos){
        getValues().add(pPos,pValue);
    }
    public void insertAt(int pPos,BPlusNode<K,V> pNode){
        addKey(pNode.getKeys().get(0),pPos);
        getChildren().set(pPos,pNode.getChildren().get(0));
        getChildren().add(pPos+1,pNode.getChildren().get(1));
        pNode = null;
    }
    public BPlusNode<K,V> SearchKeyRange(K pLowerKey,K pHigherKey, ArrayList<V> pList){
        K actualKey = null;
        for(int keyIndex = 0; keyIndex < getKeys().size(); keyIndex++){
            if(getKeys().get(keyIndex).compareTo(pLowerKey) >= 0 && (getKeys().get(keyIndex).compareTo(pHigherKey) <= 0 )){
                actualKey = getKeys().get(keyIndex);
                pList.add(getValues().get(keyIndex));
            }
        }
        if(actualKey != null && actualKey.compareTo(pHigherKey) < 0){
            return getNext();
        }
        return null;

    }
    public String toString(){
        return getKeys().toString();
    }
}
