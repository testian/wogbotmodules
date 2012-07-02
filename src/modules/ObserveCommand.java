/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import core.Session;
import core.SessionCommand;
import core.GameServerListenerIRC;
import core.SessionException;
import core.User;
import quakestat.ServerFinder;
import quakestat.GameServerMonitor;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
/**
 *
 * @author testi
 */
public class ObserveCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        String[] fields1 = message.split("#");
        if (fields1.length != 2) {
        bot.msg(channel, getUsage() + ", " + user.getNick() + ".");
        return;
        }
        String[] fields2 = fields1[0].split(":");
        if (fields2.length != 2) {
        bot.msg(channel, getUsage() + ", " + user.getNick() + ".");
        return;
        }
        try {
        int port = Integer.parseInt(fields2[1]);
        GameServerMonitor monitor = new GameServerMonitor(new ServerFinder("games.list"),fields1[1],InetAddress.getByName(fields2[0]),port);
        bot.msg(channel, "Starting Gameserver-Observation for an hour");
        monitor.scan(new GameServerListenerIRC(bot,channel), 120, 30*1000);
        bot.msg(channel, "Finishing observation of " + fields2[0] + ":" + port);
        } catch (NumberFormatException ex) {bot.msg(channel, "invalid port number specified.");} catch (IllegalArgumentException ex) {
        bot.msg(channel, ex.getMessage());
        } catch (UnknownHostException ex) {
        bot.msg(channel, ex.getMessage());
        } catch (IOException ex) {bot.msg("internal error loading the server list", ex.getMessage());}
    }

    public String getName() {
        return "observe";
    }

    public String getUsage() {
        return "<address:port>#Game Name - observe a gameserver over time";
    }

}
