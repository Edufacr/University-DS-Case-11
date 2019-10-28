/*package model;

import java.util.ArrayList;

public class TreeManager {
	
	private JsonManager json;
	private WebScrapper web;
	
	public TreeManager(){
		this.json = JsonManager.getInstance();
		this.web = WebScrapper.getInstance();
		scrapWeb();
	}
	
	private void scrapWeb() {
		int depth = this.json.getDepth();
		int count = 0;
		ArrayList<String> finalUrl = new ArrayList<String>();
		finalUrl.addAll(this.json.getUrls());
		ArrayList<String> temp = new ArrayList<String>();
		
		for (String url : finalUrl) {
			temp = loadURLs(temp, url, count, depth);
			finalUrl.addAll(temp);
			temp.clear();
		}
		System.out.println(finalUrl);
	}
	
	private ArrayList<String> loadURLs(ArrayList<String> pArray, String pUrl, int pCount, int pDepth){
		if (pCount < pDepth) {
			return loadURLs(pArray, pUrl, pCount++, pDepth);
		}
		this.web.scrapUrl(pUrl, this.json.getWidth());
		return pArray;
	}
	
	public static void main(String[] args) {
		TreeManager tm = new TreeManager();
	}
	}
 */
