package scrape.jonathan;

import javax.security.auth.login.LoginException;


import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
//creating a bot

public class ScrapeBot {
	public static JDA jda;
	public static String prefix = "~";
	
	public static void main(String[] args) throws LoginException {
		
		jda = new JDABuilder(AccountType.BOT).setToken("NjA4MTk4NTQ0MTg4MjQzOTY4.XUtulA.s9o4mP5Rym1KJf4pWTf8DTnPMUY").buildAsync();
		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		jda.getPresence().setGame(Game.playing("Stonks"));	

		jda.addEventListener(new Commands());
		

	

	
	}
	
}
