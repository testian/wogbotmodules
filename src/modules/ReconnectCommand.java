package modules;



import core.*;


public class ReconnectCommand implements SessionCommand {



	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}
	public void execute(Session bot, String channel, User user, String message)
			throws PermissionDeniedException {
		bot.assertAdmin(user);
		bot.reconnect();

	}
	public String getName()
	{
		return "reconnect";
	}

}
