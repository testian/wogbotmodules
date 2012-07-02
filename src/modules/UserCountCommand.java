package modules;

import java.util.*;
import core.*;
import utils.*;

public class UserCountCommand implements SessionCommand {

	
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getName()
	{
		return "usercount";
	}
	public void execute(Session bot, String channel, User user, String message) throws SessionException {
		// TODO Auto-generated method stub
		//Bot.getConnection().
		List<User> userList = new LinkedList<User>();
		for (Iterator<String> i = new ChannelRequest(bot, channel).getNames().iterator();i.hasNext();)
		{
			userList.add(new UserRequest(bot, i.next()).getUser());
		}
		
		
		List<User> distinctUserList = new LinkedList<User>();
		for (Iterator<User> i = userList.iterator();i.hasNext();)
		{
			User testUser=i.next();
			boolean contained=false;
			for (Iterator<User> j = distinctUserList.iterator();j.hasNext();)
			{
				if (new UserDecorator(j.next()).equals(new UserDecorator(testUser))){contained=true;} //Detect clone
			}
			if (!contained){distinctUserList.add(testUser);}
		}
		bot.msg(channel, "Anzahl User in " + channel + ": " + distinctUserList.size());
	}
	
	
}
