package modules;

//import org.schwering.irc.lib.*;

import core.*;

public class InfoCommand implements SessionCommand {


	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}
	public void execute(Session bot, String channel, User user, String message) {
		bot.msg(channel, user.getNick() + " - Host: " + user.getHost() + " Username: " + user.getUsername() + " Server: " + user.getServername());

	}
	public String getName(){return "info";}

}
