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
/**
 *
 * @author testi
 */
public class GamesCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        try {
        ServerFinder serverFinder = new ServerFinder("games.list");
        StringBuffer allGames = new StringBuffer();
        List<String> games = serverFinder.getSupportedGames();
        int i = 0;

        for (String s : games) {
        allGames.append(s);
        if (i < games.size()-1) {
        allGames.append(", ");
        }
            
            i++;

        }
        bot.msg(channel,"Supported games: " + allGames.toString());
        } catch (IOException ex) {bot.msg(channel, "Internal error: " + ex.getMessage());}
    }

    public String getName() {
        return "games";
    }

    public String getUsage() {
        return "returns a list of supported internet games to browse";
    }

}
