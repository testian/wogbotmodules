package modules;



import core.User;
import core.Session;
import core.SessionCommand;
import core.SessionException;

public class ModeCommand implements SessionCommand {

	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void execute(Session bot, String channel, User user, String message)
			throws SessionException {
		bot.assertAdmin(user);
		bot.getConnection().doMode(channel,message);

	}

	public String getName() {
		// TODO Auto-generated method stub
		return "mode";
	}

}
