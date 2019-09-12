package scrape.jonathan;

import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebscrapeBing {
	
	public static String urlBing = "https://www.bing.com/news/search?q=";
	private ArrayList<String> searchTerm;
	private String url;

	private ArrayList<String> headlines;
 
	
	
	public WebscrapeBing(int counter) {
		Webscrape trendterms = new Webscrape();
		searchTerm = new ArrayList<String>(0);
		searchTerm = trendterms.trendfun();
		headlines = new ArrayList<String>(0);
		
		
		//Collect an array with all searchterms
		
		//append first term to bing url
		url = urlBing + "\"" + searchTerm.get(counter) + "\"";
	
		//when user types next, call function again with int number aka counter. Reprint the news stuff
		
		try {
			final Document doc = Jsoup.connect(url).get();
			Elements div = doc.select("div.t_t");
			for (Element row : div) {
				final String headline = row.text();
				headlines.add(headline);
				
				
			}
			
			headlines.removeAll(Collections.singleton(null));
			headlines.removeAll(Collections.singleton(" "));
			headlines.removeAll(Collections.singleton(""));

		}
		
		catch (Exception ex) {
			ex.printStackTrace();
			}
	
		
		
		
		
		
		
		
		
	}
	
	public ArrayList<String> news() {
		return(headlines);
	}
	public String company(int i) {
		return searchTerm.get(i);
	}
	
	
}
