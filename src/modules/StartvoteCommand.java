package modules;





import core.*;



import java.util.*;

import utils.*;

public class StartvoteCommand implements SessionCommand {
	public String getUsage() {
		// TODO Auto-generated method stub
		return "[Sekunden bis Ende] <Frage>";
	}













	public String getName()
	{
		return "startvote";
	}

	
	
	
	
	

	
	
	
	
	
	
	public void execute(Session bot, String channel, User user, String message) {
		SimpleStringTokenizer messageToken = new SimpleStringTokenizer(message);
		
		

				

		
		int time=0;
		String question=null;
		int countTokens = messageToken.countTokens();
		boolean numberCondition=false;
		
		if (countTokens>1) {
		try {
			time = Integer.parseInt(messageToken.nextToken());
			if (time<1) {bot.msg(channel, "Mindestens eine Sekunde");return;}
			question = messageToken.remainingString();
			numberCondition=true;
		} catch (NumberFormatException ex) {}
		

		}
		
		
		
		

		
	if (!numberCondition) {
		if (countTokens<1){bot.msg(channel, "Nicht genug Argumente");return;}
			question = message;
			time=500;
	}
		
			Vote newVote = new Vote(user, question);
			Timer endVote = new Timer();
			
			
			
			
			
			
			
			
			
			
			

			
			
			
			
			
			
			VoteTask endTask = new VoteTask(bot, channel, newVote);

			
			
			bot.msg(channel,"Neue Abstimmung - " + newVote.getId() + ": '" + question + "'");
			endVote.schedule(endTask,((long)time)*1000);

	}

}
