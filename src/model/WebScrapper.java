package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebScrapper {
	private ArrayList<String> words;
	private ArrayList<String> urls;
	
	public WebScrapper() {
		this.words = new ArrayList<String>();
	}
	
	public void scrapUrl(String pUrl, int pWidth) {
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
	        
	        
	        // extracts words from doc
	        String text = doc.body().text();
	        for (String s : text.split(" ")) {

	        	if (s.contains(".")) {
	        		s = s.replace(".", "");
	        	} 
	        	if(s.contains(",")) {
	        		s = s.replace(",", "");
	        	}
	        	if(s.contains("?")) {
	        		s = s.replace("?", "");
	        	}
	        	if(s.contains("!")) {
	        		s = s.replace("!", "");
	        	}
	        	if(s.contains(")")) {
	        		s = s.replace(")", "");
	        	}
	        	if(s.contains("(")) {
	        		s = s.replace("(", "");
	        	}
	        	words.add(s);
	        }
	        
	        // extracts indicated amount of urls from doc
	        Elements elts = doc.getElementsByTag("a");
	        Iterator it = elts.iterator();
	        
	        while(it.hasNext() != false) {
	        	if (it.next().toString().contains("https://")) {
	        		String[] splits = it.next().toString().split("\"");
	        		for (String s : splits) {
	        			if (s.contains("https://") && this.urls.size() < pWidth) {
	        				this.urls.add(s);
	        			}
	        		}
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
		return this.urls;
	}
	
	public boolean isUrl(String pUrl) {
		try {
			URL url = new URL(pUrl);
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return false;
	}
	
	
	public static void main(String[] args) {
		WebScrapper ws = new WebScrapper();
		ws.scrapUrl("https://en.wikipedia.org/wiki/McKinley_Birthplace_Memorial_gold_dollar",3);
		System.out.println(ws.getUrls());

		
	}
}
