package modules;



import core.Session;
import core.User;
import core.SessionCommand;
import core.SessionException;
import java.net.*;
import org.jdom.*;
import org.jdom.input.*;
import java.io.*;

public class LastfmCommand implements SessionCommand {

	public void execute(Session bot, String channel, User user, String message)
			throws SessionException {
		try {
		HttpURLConnection urlConnection = (HttpURLConnection)new URL("http://ws.audioscrobbler.com/1.0/user/" + message + "/recenttracks.xml").openConnection();
		InputStream xmlStream = urlConnection.getInputStream();
		Document jdomDocument = new SAXBuilder().build(xmlStream);
		//List<Element> tracks = jdomDocument.getRootElement().getChildren("track");
		Element lastPlayedTrack = jdomDocument.getRootElement().getChild("track");
		
		if (jdomDocument == null) {
			bot.msg(channel, "Ungültige XML-Struktur");
			return;
		}
		String artist = null, name = null, album = null;
		try {
		artist = lastPlayedTrack.getChild("artist").getText();
		} catch (NullPointerException ex) {
		}
		try {
		name = lastPlayedTrack.getChild("name").getText();
		} catch (NullPointerException ex) {}
		try {
		album = lastPlayedTrack.getChild("album").getText();
		} catch (NullPointerException ex) {}
		
		String dateString=null;
		try {
			
			long time = Long.parseLong(lastPlayedTrack.getChild("date").getAttributeValue("uts"));
			dateString = howLongAgo(time,2);
		
		} catch (NullPointerException ex) {
			
			
		} catch (NumberFormatException ex) {
			
			
		}
		String trackString =  message + " last.fm - Zuletzt gehört: ";
		
		if (artist != null && artist.length()>0) {
			trackString+=artist + " - ";
			
		}
		else {
			trackString+="no artist - ";
			
		}
		
		if (name != null && name.length()>0) {
			trackString+=name;
			
		}
		else {
			trackString += "no title";
			
		}
		if (album != null && album.length()>0) {
			trackString+=" (" + album + ")";
			
		}
		if (dateString != null) {
			
			trackString+=", " + dateString;
		}
		
		bot.msg(channel, trackString);
		
		} catch (IOException ex) {
			bot.msg(channel, "Fehler beim Kontakt mit last.fm");
		}
		catch (JDOMException ex) {
			bot.msg(channel, "Fehler beim Verarbeiten der Antwort");
			
		}
		
		//("http://ws.audioscrobbler.com/1.0/user/" + message + "/recenttracks.xml");
		

	}

	public String getName() {
		// TODO Auto-generated method stub
		return "lastfm";
	}

	public String getUsage() {
		// TODO Auto-generated method stub
		return "<last.fm username>";
	}
	private String howLongAgo(long unixTimeInSeconds, int precision) {
		return "Vor " + describeTimeSpan(System.currentTimeMillis()/1000 - unixTimeInSeconds, precision);
	}
	static final int YEAR_SECONDS  = 364*24*3600;
	static final int WEEK_SECONDS  = 7*24*3600;
	static final int DAY_SECONDS  = 24*3600;
	static final int HOUR_SECONDS  = 3600;
	
	private String describeTimeSpan(long timeSpan, int precision) {
		long years = timeSpan / YEAR_SECONDS;
		timeSpan-=YEAR_SECONDS*years;
		long weeks = timeSpan / WEEK_SECONDS;
		timeSpan-=WEEK_SECONDS*weeks;
		long days = timeSpan / DAY_SECONDS;
		timeSpan-=DAY_SECONDS*days;
		long hours = timeSpan / HOUR_SECONDS;
		timeSpan-=HOUR_SECONDS*hours;
		long minutes = timeSpan / 60;
		timeSpan-=60*minutes;
		long seconds = timeSpan;
		
		
		String[] lines = new String[6];
		int length=0;
		if (years>0){
			lines[length++] = years == 1 ? "einem Jahr" : years + " Jahre";
		}
		if (weeks>0){
			lines[length++] = weeks == 1 ? "einer Woche" : weeks + " Wochen";
		}
		if (days>0){
			lines[length++] = days == 1 ? "einem Tag" : days + " Tagen"; //"Tagen" ist falsch - sollte "Tage" sein aber da nur private use durch "howLongAgo" -> egal
		}
		if (hours>0){
			lines[length++] = hours == 1 ?  "einer Stunde" : hours + " Stunden";
		}
		if (minutes>0){
			lines[length++] = minutes == 1 ? "einer Minute" : minutes + " Minuten";
		}
		if (seconds>0){
			lines[length++] = seconds == 1 ? "einer Sekunde" : seconds + " Sekunden";
		}
		length = length<precision ? length : precision;
		StringBuffer ret = new StringBuffer();
		for (int i = 0 ; i < length; i++) {
			ret.append(lines[i]);
			if (length-i > 2) {
				ret.append(", ");
			}
			else if (length-i == 2){
				ret.append(" und ");
			}
		}
		return ret.toString();
		
		
	}


}
