package model;

import java.util.PriorityQueue;

public class TreeManager {
	
	private JsonManager json;
	private WebScrapper web;
	private PriorityQueue<String> queue;
	
	public TreeManager(){
		this.json = JsonManager.getInstance();
		this.web = WebScrapper.getInstance();
		scrapWeb();
	}
	
	private void scrapWeb() {
		int width = this.json.getWidth();
		for (String url : this.json.getUrls()) {
			this.web.scrapUrl(url, width);
		}
	}
	
	public static void main(String[] args) {
		PriorityQueue<String> queue = new PriorityQueue<String>();
		queue.add("B");
		queue.add("D");
		queue.add("A");
		queue.add("E");
		queue.add("C");

	}
}
