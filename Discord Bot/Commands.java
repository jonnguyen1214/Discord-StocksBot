package scrape.jonathan;

import java.util.ArrayList;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class Commands extends ListenerAdapter {
	public static boolean trendLoaded;
	public static int counter = 0;
	//When user types a message
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
    

        //check args for info
        if (args[0].equalsIgnoreCase( ScrapeBot.prefix+ "info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Scrape Bot info");
            info.setDescription("Stock bot for primarily tracking stocks on the NYSE");

            info.setColor(0xfcbb08);
            info.setFooter("Created by Jonathan", event.getMember().getUser().getAvatarUrl());
          
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
       
       //help command
        if (args[0].equalsIgnoreCase(ScrapeBot.prefix + "help")) {
            EmbedBuilder helpmsg = new EmbedBuilder();
            helpmsg.setTitle("List of commands");
            helpmsg.setDescription("info\nStockW\nStockL\nStockVol");

       
            event.getChannel().sendMessage(helpmsg.build()).queue();
            helpmsg.clear();
        }
        
     
        
        //Most Active stock
        if (args[0].equalsIgnoreCase(ScrapeBot.prefix + "stockVol")) {
            EmbedBuilder volmsg = new EmbedBuilder();
            
            volmsg.setTitle("List of most active stocks");
            Webscrape test = new Webscrape();
            

       
            event.getChannel().sendMessage(volmsg.build()).queue();
            event.getChannel().sendMessage(test.volumefun()).queue();
            volmsg.clear();
        }
        
        //Percent loss
        if (args[0].equalsIgnoreCase(ScrapeBot.prefix + "stockL")) {
            EmbedBuilder lossmsg = new EmbedBuilder();
            
            lossmsg.setTitle("List of highest percent loss stocks");
            Webscrape test = new Webscrape();
            

       
            event.getChannel().sendMessage(lossmsg.build()).queue();
            event.getChannel().sendMessage(test.lossfun()).queue();
            lossmsg.clear();
        }
        
        //Percent gain
        if (args[0].equalsIgnoreCase(ScrapeBot.prefix + "stockW")) {
            EmbedBuilder gainmsg = new EmbedBuilder();
            
            gainmsg.setTitle("List of highest percent gain stocks");
            Webscrape test = new Webscrape();
            

       
            event.getChannel().sendMessage(gainmsg.build()).queue();
            event.getChannel().sendMessage(test.gainfun()).queue();
            gainmsg.clear();
        }
        // Trends
        if (args[0].equalsIgnoreCase(ScrapeBot.prefix + "trends")) {
        	trendLoaded = true;
            EmbedBuilder trendmsg = new EmbedBuilder();
            WebscrapeBing first = new WebscrapeBing(counter);
            
            trendmsg.setTitle("Trending Stock: " + first.company(counter));
            event.getChannel().sendMessage(trendmsg.build()).queue();
            ArrayList<String> headlineList = first.news();
            String formatting = "";
    		for (String headline: headlineList) {
    			formatting += ( headline + "\n\n");
    		}
    		event.getChannel().sendMessage(formatting).queue();
    		trendmsg.clear();

       
           
            
            
            trendmsg.clear();
        }
        
        //cycling through trends
        if (args[0].equalsIgnoreCase(ScrapeBot.prefix + "next") && trendLoaded == true) {
        	counter+=1;
        	WebscrapeBing stockNews = new WebscrapeBing(counter);
        	 EmbedBuilder trendmsg = new EmbedBuilder();
             WebscrapeBing following = new WebscrapeBing(counter);
             
             trendmsg.setTitle("Trending Stock: " + following.company(counter));
             event.getChannel().sendMessage(trendmsg.build()).queue();
             ArrayList<String> headlineList = following.news();
             String formatting = "";
     		for (String headline: headlineList) {
     			formatting += ( headline + "\n\n");
     		}
     		event.getChannel().sendMessage(formatting).queue();
        	trendmsg.clear();
        }
        
        
        
       
        
        
        
        
        
    }
}