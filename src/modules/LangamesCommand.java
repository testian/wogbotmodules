/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import core.Session;
import core.SessionCommand;
import core.SessionException;
import core.User;
import quakestat.ServerFinder;
import java.io.IOException;
import java.util.List;
import quakestat.GameServer;
/**
 *
 * @author testi
 */
public class LangamesCommand implements SessionCommand{

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        try {
        ServerFinder sf = new ServerFinder("games.list");
        List<GameServer> serverList;
        int port = -1;
        String[] fields=message.split("#");
        if (fields.length>1){
        message = fields[0];
        try{port = Integer.parseInt(fields[1]);} catch (NumberFormatException ex){System.err.println(ex);}
        }
        if (message.isEmpty()) {
        bot.msg(channel, "Looking for local games" + (port!=-1 ? ", forcing port " + port : ""));
        serverList = sf.scanLan("10.8.0.255",port);
        } else {
        bot.msg(channel, "Looking for local " + message + " games" + (port!=-1 ? ", forcing port " + port : ""));
        serverList = sf.scanLan(message,"10.8.0.255",port);
        }
        if (serverList.isEmpty()) {bot.msg(channel, "No games found."); return;}
        for (GameServer s : serverList) {
        bot.msg(channel,s.serverDescription());
        }

        }
        catch (IOException ex) {bot.msg(channel, "internal error: " + ex);}
        catch (IllegalArgumentException ex) {bot.msg(channel, "error: " + ex.getMessage());}
    }

    public String getName() {
        return "langames";
    }

    public String getUsage() {
        return "[Game Name][#port number]";
    }

}
