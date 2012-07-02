/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import core.Session;
import core.SessionCommand;
import core.SessionException;
import core.User;
import quakestat.GameServer;
import quakestat.ServerFinder;
import java.io.IOException;
//import java.util.ArrayList;
import quakestat.MatchException;
import quakestat.ServerListener;
import quakestat.query.QueryException;
import quakestat.query.QueryExecution;
/**
 *
 * @author testi
 */
public class FindserverCommand implements SessionCommand, ServerListener {
    private Session bot;
    private String channel;
    private User user;
    private int count;
    public FindserverCommand() {
    this.bot = null;
    this.channel = null;
    this.user = null;
    count = 0;
    }


    public FindserverCommand(Session bot, String channel, User user) {
        this.bot = bot;
        this.channel = channel;
        this.user = user;
        count = 0;
    }



    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        try {
        ServerFinder finder = new ServerFinder("games.list");
        bot.msg(channel, "Looking for servers");
        FindserverCommand sl = new FindserverCommand(bot, channel, user);
        try {
        finder.findServerByRequest(new QueryExecution(message), sl);
        } catch (MatchException ex) {
        bot.msg(channel, ex.getMessage());
        return;
        }
        if (sl.count < 1)
        bot.msg(channel,"No servers found, " + user.getNick() + ".");
        else
        bot.msg(channel, "End of list");

        


        } catch (IOException ex) {
        bot.msg(channel, "internal error: loading server list failed");
        } catch (QueryException ex) {bot.msg(channel, "Fehler: " + ex.getMessage());}

    }

    public String getName() {
        return "findserver";
    }

    public String getUsage() {
        return "[SOURCE ('<Game Name 1>','<Game Name 2>','<Game Name..>')] [WHERE (<server variable> =|<|>|CONTAINS|~=|~CONTAINS <expected value>)] [SORT BY <sort critera/server variable> ] [LIMIT <amount>]";
    }

    public void onServer(GameServer server) {
        if (count<10) {
        bot.msg(channel, server.serverDescription());
        } else if (count == 10) {
        bot.msg(channel, "More than 10 servers found..");
        }
                count++;
    }

    public boolean proceed() {
        return count<11;
    }






}
