package model;

import java.util.ArrayList;

public class TreeLoader {
	
	private JsonManager json;
	private WebScrapper web;
	
	public TreeLoader(){
		this.json = JsonManager.getInstance();
		this.web = WebScrapper.getInstance();
	}
	
	public void loadTrees() {
		int width = this.json.getWidth();
		int depth = this.json.getDepth();
		this.loadTrees(this.json.getUrls(), width, depth-1);
	}
	
	private void loadTrees(ArrayList<String> pUrls, int pWidth, int pDepth) {
		if (pDepth == 0) {
			return;
		}
		
		for (String url : pUrls) {
			try {
			this.web.scrapUrl(url, pWidth);
			// load trees here
			} catch(org.jsoup.UncheckedIOException e) {
				continue;
			}
		}
		
		loadTrees(this.web.getUrls(), pWidth, --pDepth);
	}
}
