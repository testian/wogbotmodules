package modules;

//import org.schwering.irc.lib.IRCUser;

import core.Session;
import core.SessionCommand;
import core.SessionException;
import utils.*;
import core.*;
import java.util.*;

public class HelpCommand implements SessionCommand {

	public void execute(Session bot, String channel, User user, String message)
			throws SessionException {
SimpleStringTokenizer tokens = new SimpleStringTokenizer(message);
	List<SessionCommand> commandList = new LinkedList<SessionCommand>();
	SessionCommandManager manager = bot.getCommandManager();
	while (tokens.hasMoreTokens())
	{
		SessionCommand command = manager.getCommand(tokens.nextToken());
		if (command != null){
			commandList.add(command);
		}
	}
	if (commandList.size()<1){
		commandList = manager.getCommands();
	}
	
	String target=channel;
	if (commandList.size()>1){
		target=user.getNick();
	}
	for (SessionCommand t : commandList)
	{
		String msg;
		if (t.getUsage() == null){
			msg = manager.getPrefix() + t.getName();
		}else {
			msg = manager.getPrefix() + t.getName() + " " + t.getUsage();
		}
		bot.msg(target, msg);
	}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "help";
	}

	public String getUsage() {
		// TODO Auto-generated method stub
		return "[Command Names]";
	}

}
