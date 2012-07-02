package modules;

import core.*;

public class EchoCommand implements SessionCommand {



	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}
	public void execute(Session bot, String channel, User user, String message)
	{
		bot.msg(channel, message);
	}
	public String getName()
	{
		return "echo";
	}
}
