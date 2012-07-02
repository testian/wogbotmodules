package modules;

//import org.schwering.irc.lib.*;
import utils.*;

import core.*;

public class JoinCommand implements SessionCommand {




	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}
	public void execute(Session bot, String channel, User user, String message)
			throws PermissionDeniedException {
		bot.assertAdmin(user);
		SimpleStringTokenizer messageTokens = new SimpleStringTokenizer(message);
		if (messageTokens.hasMoreTokens())
		{
			bot.getConnection().doJoin(messageTokens.nextToken());
		}
		else {
			bot.msg(channel, "No channel specified");
		}
	
		
		// TODO Auto-generated method stub

	}
	public String getName()
	{
		return "join";
	}

}
