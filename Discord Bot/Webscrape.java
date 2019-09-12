package scrape.jonathan;

import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Webscrape {
	//urls of sites
	public static final String urlTrend = "https://seekingalpha.com/market-news/all";
	public static final String urlVolInfo = "https://www.marketwatch.com/tools/screener?exchange=nyse&report=MostActive";
	public static final String urlGainInfo = "https://www.marketwatch.com/tools/screener?exchange=Nyse&report=LargestPercentGainReport";
	public static final String urlLossInfo = "https://www.marketwatch.com/tools/screener?exchange=Nyse&report=LargestPercentLossReport";
	private String volume;
	private String gainperc;
	private String lossperc;
	private ArrayList<String> trend;
	private String testing;

	public Webscrape() {
		//strings for formatting discord message
		volume = (nameadjuster("SYMBOL")+ "Volume" + "\n");
		gainperc = (nameadjuster("SYMBOL") +"\t"+ nameadjuster("PRICE") +"\t"+ nameadjuster("GAIN") +"\t"+ nameadjuster("PERCENT")
		+"\n");
		lossperc = (nameadjuster("SYMBOL") +"\t"+ nameadjuster("PRICE") +"\t"+ nameadjuster("LOSS") +"\t"+ nameadjuster("PERCENT")
		+"\n");
		trend = new ArrayList<String>(0);
		try {
			
			final Document docvol = Jsoup.connect(urlVolInfo).get();
			final Document docgain = Jsoup.connect(urlGainInfo).get();
			final Document docloss = Jsoup.connect(urlLossInfo).get();
			final Document doctrend = Jsoup.connect(urlTrend).get();
			//html scraping
			for (Element row : docvol.select("div.block tr")) {
				String name = row.select("td:nth-of-type(1)").text(); 
				name = nameadjuster(name);
				
				
				final String vol = row.select("td:nth-of-type(6)").text();
				
				volume += (name+"\t"+vol+"\n");
			
			}
			
			for (Element row : docloss.select("div.block tr")) {
				String name = row.select("td:nth-of-type(1)").text(); 
				
				
				
				final String last = row.select("td:nth-of-type(3)").text();
				final String abschng = row.select("td:nth-of-type(4)").text();
				final String percchng = row.select("td:nth-of-type(5)").text();
				
				lossperc += (nameadjuster(name)+"\t"+nameadjuster(last)+"\t"+nameadjuster(abschng)+"\t"+nameadjuster(percchng)+"\n");
			
			}
			for (Element row : docgain.select("div.block tr")) {
				String name = row.select("td:nth-of-type(1)").text(); 
				
				
				
				final String last = row.select("td:nth-of-type(3)").text();
				final String abschng = row.select("td:nth-of-type(4)").text();
				final String percchng = row.select("td:nth-of-type(5)").text();
				
				gainperc += (nameadjuster(name)+"\t"+nameadjuster(last)+"\t"+nameadjuster(abschng)+"\t"+nameadjuster(percchng)+"\n");
			
			}
			
			Elements div = doctrend.select("li.mc div");
			for (Element row : div) {
				String value = row.select("div.media-left").text();
				trend.add(value);
				
				
				
			}
			trend.removeAll(Collections.singleton(null));
			trend.removeAll(Collections.singleton(" "));
			trend.removeAll(Collections.singleton(""));
			
			
			
		}	
		
		
		catch (Exception ex) {
		ex.printStackTrace();
		}
	}
	
	public ArrayList<String> trendfun() {
		return trend;
	}
	public String volumefun() {
		return volume;
	}
	public String lossfun() {
		return lossperc;
	}
	public String gainfun() {
		return gainperc;
	}
	
	
	
	//formatting function
	public String nameadjuster(String name) {
		String adjust = name;
		while (adjust.length() < 17) {
			adjust +=" ";
		}
		return adjust;
	}
	
		
		
		
}
	
	
	
	
	
	
	
	
	
	
	

