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
import quakestat.Player;
import quakestat.GameServer;
import utils.SimpleStringTokenizer;
import java.io.IOException;
/**
 *
 * @author testi
 */
public class ServerinfoCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        try {
        ServerFinder sf = new ServerFinder("games.list");
        String fields[] = message.split("#");
        if (fields.length > 2){
        bot.msg(channel, "Invalid number of arguments");
        return;
        }

        GameServer server;
        if (fields.length  == 2)
            server = sf.getServerInfo(fields[1],fields[0]);
        else
            server = sf.getServerInfo(message);
        if (server == null) {
        bot.msg(channel, "Server " + fields[0] + " not found.");
        }
        bot.msg(channel, server.serverDescription());
        for (Player  p : server.getPlayers()) {
        bot.msg(channel, "  " + p.playerDescription());
        }

        } catch (IOException ex) {bot.msg(channel,"Internal error: " + ex);}
        catch (IllegalArgumentException ex) {bot.msg(channel, "Error: " + ex.getMessage());}
    }

    public String getName() {
        return "serverinfo";
    }

    public String getUsage() {
        return "<host:port>#<Game Name>";
    }

}
