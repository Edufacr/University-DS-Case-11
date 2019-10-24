/*package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;


public class JsonManager implements IConstants{
	
	private static JsonManager singleton;
	private int width;
	private int depth;
	private ArrayList<String> urls;
	
	private JsonManager() {
		this.urls = new ArrayList<String>();
		try {
			this.loadJson(JSON_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JsonManager getInstance() {
		if (singleton == null) {
			singleton = new JsonManager();
		}
		return singleton;
	}
	
	private void loadJson(String pPath) throws IOException {
		
		
		
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(new FileReader(pPath));
			
			long jWidth = (long) json.get("anchura");
			long jDepth = (long) json.get("profundidad");
			
			this.width = (int) jWidth;
			this.depth = (int) jDepth;
			
			
			JSONArray urlArray = (JSONArray) json.get("urls");
			Iterator jsonArrayIterator = urlArray.iterator();
			
			while(jsonArrayIterator.hasNext()) {
				String url = (String) jsonArrayIterator.next();
				this.urls.add(url);
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return this.width;
	}

	public int getDepth() {
		return this.depth;
	}

	public ArrayList<String> getUrls() {
		return this.urls;
	}
}
*/
