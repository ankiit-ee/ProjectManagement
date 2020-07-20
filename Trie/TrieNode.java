package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {

    T value;
    TrieNode[] tn = new TrieNode[100];
    int count;
    TrieNode parent;

    public TrieNode(){
        for (int i=0;i<100;i++){
            tn[i]=null;
        }
        count=0;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public int getCount(){
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public T getValue() {
        return value;
    }
//    public TrieNode next(char c){
//        return tn[c - 'a'];
//    }



}