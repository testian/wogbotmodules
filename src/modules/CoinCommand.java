package modules;



import core.Session;
import core.User;
import core.SessionCommand;
import core.SessionException;
import java.util.*;

public class CoinCommand implements SessionCommand {

	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void execute(Session bot, String channel, User user, String message)
			throws SessionException {
		
		StringTokenizer msg = new StringTokenizer(message);
		int tokenCount = msg.countTokens();
		if (tokenCount>1)
		{
			ArrayList<String> choice = new ArrayList<String>();
			while (msg.hasMoreTokens())
			{
				choice.add(msg.nextToken());
			}
			bot.msg(channel, choice.get(new Random().nextInt(tokenCount)));
			
			
		}else {
		
		
		if (new Random().nextBoolean())
		{
			bot.msg(channel, "heads");
		}
		else {
			bot.msg(channel, "tails");
		}
		}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "coin";
	}

}
