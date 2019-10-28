package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScrapper {
	private static WebScrapper singleton;
	private ArrayList<String> words;
	private ArrayList<String> urls;
	
	private WebScrapper() {
		this.words = new ArrayList<String>();
		this.urls = new ArrayList<String>();
	}
	
	public static WebScrapper getInstance() {
		if (singleton == null) {
			singleton = new WebScrapper();
		}
		return singleton;
	}
	
	public void scrapUrl(String pUrl, int pWidth) {
		this.words.clear();
		try {
	        // Create a URL for the desired website
	        URL url = new URL(pUrl);       

	        URLConnection con = url.openConnection();
	        BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String html = new String();
	        String line;
	        
	        // copies all html code to a String
	        while ((line = input.readLine())!=null) {
	            html += line;
	        }
	        
	        // parses html String to a doc using Jsoup
	        Document doc = Jsoup.parse(html); 
	        
	        // extracts indicated amount of urls from doc
	        Elements elts = doc.getElementsByTag("a");
	        Iterator<?> it = elts.iterator();
	        
	        int urlCount = 0;
	        while(it.hasNext()) {
	        	String next = it.next().toString();
	        	String[] splits = next.split("\"");
	        	if (next.contains("https://") && splits.length > 1) {	        		
	        		for (String s : splits) {
	        			if (isUrl(s) && urlCount < pWidth) {
	        				urls.add(s.toString());
	        				urlCount++;
	        			}
	        		}
	        	}
	        }
	        
	        
	        // extracts words from doc
	        String text = doc.body().text();
	        for (String s : text.split("\\W+")) {
	        	if (isUseful(s) && s.length() > 4){
	        		words.add(s.toLowerCase());
	        	}
	        }
	        
	    } catch (MalformedURLException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	public int getAmount() {
		return this.words.size();
	}
	
	public ArrayList<String> getWords(){
		return this.words;
	}
	
	public ArrayList<String> getUrls(){
		ArrayList<String> depthUrls = new ArrayList<String>();
		depthUrls.addAll(this.urls);
		this.urls.clear();
		return depthUrls;
	}
	
	private boolean isUrl(String pUrl) {
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pUrl);
		return matcher.matches();
	}
	
	private boolean isUseful(String pWord) {
		String regex = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pWord);
		return matcher.matches();
	}
}
