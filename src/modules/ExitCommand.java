package modules;


import core.*;

public class ExitCommand implements SessionCommand {

	public void execute(Session bot, String channel, User user, String message) throws SessionException {
		bot.assertAdmin(user);
		bot.teardown();
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return "exit";
	}

	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

}
