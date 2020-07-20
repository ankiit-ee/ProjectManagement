package Trie;

import java.util.ArrayList;

public class Trie<T> implements TrieInterface {
    TrieNode root;
    int maxlevel;
    public Trie(){
        root=new TrieNode();
        maxlevel=0;
    }

    @Override
    public boolean delete(String word) {
        if(this.root==null || word==null){
            return false;
        }
        if(search(word)!=null) {
            delete(word, this.root, 0);
            return true;
        }
//        delHelp(word,root,0);
        return false;
    }


    public void delHelp(String word, TrieNode node, int level){
        if(node==null) return;
        if(level == word.length()-1){
            node.setValue(null);
            node.tn[indexCalci(word.charAt(level))] = null;
            return;
        }
        if(hasNochild(node)) {
            node.tn[indexCalci(word.charAt(level))] = null;
            return;
        }
        delHelp(word,node.tn[word.charAt(level)],level+1);

    }
    public boolean hasNochild(TrieNode node){
        for (int i = 0; i <node.tn.length ; i++) {
            if(node.tn[i]!=null) return false;
        }
        return true;
    }



    @Override
    public TrieNode search(String word) {
        TrieNode node = this.root;
        for (int i = 0; i <word.length() ; i++) {
                if (node.tn[indexCalci(word.charAt(i))] == null) return null;
                node = node.tn[indexCalci(word.charAt(i))];

        }
        if(node.getValue()==null) return null;
        return node;
    }

    @Override
    public TrieNode startsWith(String prefix) {
        TrieNode node = this.root;

        for (int i = 0; i <prefix.length() ; i++) {
            if(prefix.charAt(i)!=' ') {
                if (node.tn[indexCalci(prefix.charAt(i))] == null) return null;
                node = node.tn[indexCalci(prefix.charAt(i))];
            }
        }
        return node;
    }

    @Override
    public void printTrie(TrieNode trieNode) {
        ArrayList<String> a = new ArrayList<>();
        printTrieHelp(trieNode,a);
        for(int i=0;i<a.size();i++){
            for (int j=i+1; j<a.size();j++){
                if(a.get(j).compareTo(a.get(i))<0){
                    String c = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,c);
                }
            }
        }
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i));
        }

    }
    public ArrayList<String> printTrieHelp(TrieNode trieNode,ArrayList<String>p){
        if(trieNode.getValue()!=null) {
            p.add(trieNode.getValue().toString());
            return p;
        }
        for (int i=0;i<95;i++){
            if(trieNode.tn[i]!=null) printTrie(trieNode.tn[i]);
        }
        return p;
    }

    @Override
    public boolean insert(String word, Object value) {
        if(word.trim().length()>maxlevel) maxlevel = word.trim().length();
        TrieNode node = this.root;
        for (int i = 0; i <word.length() ; i++) {
                if (node.tn[indexCalci(word.charAt(i))] == null) {
                    node.tn[indexCalci(word.charAt(i))] = new TrieNode();
                }
                node = node.tn[indexCalci(word.charAt(i))];
            node.count++;
        }
        node.setValue(value);
        return true;

    }
    private int indexCalci(char c){
        return c-'a'+ 32 +16+20;
    }


    @Override
    public void printLevel(int level) {
        ArrayList<Character> a = new ArrayList();
        printLevelHelp(this.root,level,1,a);
        for(int i=0;i<a.size();i++){
            for (int j=i+1; j<a.size();j++){
                if(a.get(j)<a.get(i)){
                    char c = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,c);
                }
            }
        }
        System.out.print("Level " + level+": ");
        for(int i=0;i<a.size();i++){
            if(a.get(i)!= ' ' && i!=a.size()-1)System.out.print(a.get(i)+",");
            else if(a.get(i)!= ' ' && i==a.size()-1) System.out.print(a.get(i));
        }
        System.out.println();
           System.out.println("-------------");
    }

    public ArrayList<Character> printLevelHelp(TrieNode node, int level, int k, ArrayList<Character> a){
        if(k==level) {
            for (int j=0;j<95;j++){
                if(node.tn[j]!=null) a.add((char)(j+65-16-20));
            }
            return a;
        }
        for(int i=0;i<95;i++){
           if(node.tn[i]!=null) printLevelHelp(node.tn[i],level, k+1,a);
        }
        return a;
    }

    @Override
    public void print() {
        System.out.println("Printing Trie");
        for(int i=0;i<=maxlevel;i++){
            printHelp(i+1);
        }
        System.out.println("-------------");
    }
    public void printHelp(int level){
        ArrayList<Character> a = new ArrayList();
        printLevelHelp(this.root,level,1,a);
        for(int i=0;i<a.size();i++){
            for (int j=i+1; j<a.size();j++){
                if(a.get(j)<a.get(i)){
                    char c = a.get(i);
                    a.set(i,a.get(j));
                    a.set(j,c);
                }
            }
        }
        System.out.print("Level " + level+": ");
        for(int i=0;i<a.size();i++){
            if(a.get(i)!= ' ' && i!=a.size()-1)System.out.print(a.get(i)+",");
            else if(a.get(i)!= ' ' && i==a.size()-1) System.out.print(a.get(i));
        }
        System.out.println();
    }


    public TrieNode delete(String word, TrieNode node, int k){
        if(k==word.length()){
            if(node.getValue()!=null) node.setValue(null);
            if(hasNochild(node)) node=null;
            return node;
        }
        node.tn[indexCalci(word.charAt(k))] = delete(word,node.tn[indexCalci(word.charAt(k))],k+1);
        if(hasNochild(node)&& node.getValue()==null) node=null;
        return node;
    }
}