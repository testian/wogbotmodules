package modules;

import java.util.*;


import core.*;
import utils.*;
public class TaskCommand implements SessionCommand {

	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getName()
	{
		return "task";
	}

	
	public void execute(Session bot, String channel, User user, String message)
			throws PermissionDeniedException {
		bot.assertAdmin(user);
		SimpleStringTokenizer messageToken= new SimpleStringTokenizer(message);
		if (!messageToken.hasMoreTokens()){
			bot.msg(channel, user + ": list, kill, exit");return;
		}
		String nextToken=messageToken.nextToken();
		if (nextToken.equals("list")) { 
		int id=0;
		for (Iterator<SessionTask> i = bot.getTasks().iterator();i.hasNext();)
		{
			SessionTask task = i.next();
			bot.msg(channel, id + " Description: " + task.getDescription());
			id++;
		}
		}
		else if (nextToken.equals("kill") || nextToken.equals("exit")) {
			if (!messageToken.hasMoreTokens())
			{
				bot.msg(channel, "Ungenügend Argumente");
				return;
			}
			try {
			int id = Integer.parseInt(messageToken.nextToken());
			List<SessionTask> taskList = bot.getTasks();
			SessionTask killTask = bot.getTasks().get(id);
			if (id>=taskList.size() || id<0) {bot.msg(channel, "Ungültige Tasknummer");return;}
			if (nextToken.equals("kill"))
			{
				
				bot.msg(channel, "Töte " + killTask.getDescription());
				killTask.kill();
				
			}
			else {
				bot.msg(channel, "Beende " + killTask.getDescription());
				killTask.exit();				
			}
			}catch (NumberFormatException ex) {bot.msg(channel, "Ungültige Tasknummer");}
		}
		else {
			bot.msg(channel, user + ": list, kill, exit");
		}


	}

}
