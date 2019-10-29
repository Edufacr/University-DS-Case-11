package model;

import bPlusTree.BPlusNode;
import bPlusTree.BPlusTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TreeLoader {
	
	private JsonManager json;
	private WebScrapper web;

	private BPlusTree<Integer,ArrayList<String>> wordsOccurrenceTree;
	private AVLTree<Word> urlTree;
	private AVLTree<Word> wordTree;
	private int wordsIndexed;
	
	public TreeLoader(){
		this.json = JsonManager.getInstance();
		this.web = WebScrapper.getInstance();
		wordsIndexed = 0;
		wordsOccurrenceTree = new BPlusTree<Integer, ArrayList<String>>(4);
		urlTree = new AVLTree<Word>();
		wordTree = new AVLTree<Word>();
	}
	
	public void loadTrees() {
		int width = this.json.getWidth();
		int depth = this.json.getDepth();
		this.loadTrees(this.json.getUrls(), width, depth);
	}
	
	private void loadTrees(ArrayList<String> pUrls, int pWidth, int pDepth) {
		if (pDepth == 0) {
			return;
		}
		for (String url : pUrls) {
			try {
			this.web.scrapUrl(url, pWidth);
			insertIntoTrees(organizeWords(web.getWords()),url);
			// load trees here
			} catch(org.jsoup.UncheckedIOException e) {
				continue;
			}
		}
		
		loadTrees(this.web.getUrls(), pWidth, --pDepth);
	}
	private BPlusTree<String,int[]> organizeWords(ArrayList<String> pWords){
		BPlusTree<String,int[]> tree = new BPlusTree<String, int[]>(4);
		for (String word:pWords
			 ) {
			int[] container = tree.searchValue(word);
			if(container == null){
				container = new int[1];
				container[0] = 1;
				tree.add(word,container);
			}
			else{
				container[0]++;
			}
		}
		return tree;
	}

	private void insertIntoTrees(BPlusTree<String,int[]> pWords, String pUrl){
		BPlusNode<String,int[]> node = pWords.getFirst();
		System.out.println(pUrl);
		System.out.println(pWords.toString());
		ArrayList<WordOccurrenceContainer> list = new ArrayList<WordOccurrenceContainer>();
		String word;
		int occurrence;
		while(node != null){
			for (int keyIndex = 0; keyIndex < node.getKeys().size();keyIndex++){
				wordsIndexed++;
				word = node.getKeys().get(keyIndex);
				occurrence = node.getValues().get(keyIndex)[0];
				insertIntoWordTree(word,pUrl);
				insertIntoOccurrenceTree(occurrence,pUrl);
				list.add(new WordOccurrenceContainer(occurrence,word));
			}
			node = node.getNext();
		}
		insertIntoURL(pUrl,list);
	}
	private void insertIntoWordTree(String pWord,String pUrl){
		ArrayList<String> urlArray = new ArrayList<String>();
		Word newWord = new Word(pWord,urlArray);
		Word word = wordTree.get(newWord);
		if(word == null){
			urlArray.add(pUrl);
			wordTree.add(newWord);
		}
		else {
			word.getList().add(pUrl);
		}
	}
	private void insertIntoOccurrenceTree(int pOccurrence, String pUrl){
		ArrayList<String> urlList = wordsOccurrenceTree.searchValue(pOccurrence);
		if(urlList == null){
			urlList = new ArrayList<String>();
			urlList.add(pUrl);
			wordsOccurrenceTree.add(pOccurrence,urlList);
		}
		else {
			urlList.add(pUrl);
		}
	}
	private void insertIntoURL(String pUrl,ArrayList<WordOccurrenceContainer> pList){
		pList.sort(Collections.reverseOrder());
		ArrayList<String> topFive = new ArrayList<String>();
		for (int index = 0; index<5;index++){
			topFive.add(pList.get(index).getWord());
		}
		urlTree.add(new Word(pUrl,topFive));
	}

	public BPlusTree<Integer, ArrayList<String>> getWordsOccurrenceTree() {
		return wordsOccurrenceTree;
	}

	public AVLTree<Word> getUrlTree() {
		return urlTree;
	}

	public AVLTree<Word> getWordTree() {
		return wordTree;
	}

	public int getWordsIndexed() {
		return wordsIndexed;
	}

	public static void main(String[] args) {
		TreeLoader tm = new TreeLoader();
		tm.loadTrees();
	}
}
