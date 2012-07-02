package modules;







import core.*;
import utils.*;


public class VoteCommand implements SessionCommand {
	
	public String getUsage() {
		// TODO Auto-generated method stub
		return "<Vote-ID> <Antwort>";
	}


	public String getName()
	{
		return "vote";
	}

	
	public void execute(Session bot, String channel, User user, String message) {
		SimpleStringTokenizer messageTokens = new SimpleStringTokenizer(message);
		if (messageTokens.countTokens()<2){bot.msg(channel, "Ungenügend Argumente");
		return;
		}
		String idString = messageTokens.nextToken();
		try {
		int id = Integer.parseInt(idString);
		try {
			Vote vote = Vote.getVote(id);
			if (vote == null)
			{
				bot.msg(channel, "Ungültige ID: " + id);
				return;
			}
			synchronized(vote) {
			vote.vote(user,messageTokens.remainingString());
			}
		} catch (Vote.AlreadyVotedException ex)
		{
			bot.msg(channel, "Sorry "+ user + ", du hast schon abgestimmt");
		}
		
		} catch (NumberFormatException ex){bot.msg(channel, "Ungültige ID: " + idString);}
	}

}
