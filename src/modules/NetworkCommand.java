/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import core.Session;
import core.SessionCommand;
import core.SessionException;
import core.User;
import java.net.InetAddress;
/**
 *
 * @author testi
 */
public class NetworkCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        
        if (bot.getConnection().getNick().equals(channel)) channel = user.getNick();
        bot.msg(channel, "Host list");
        synchronized (bot.getNetReporter()) {
        for (InetAddress a : bot.getNetReporter().onlineHosts()) {
        bot.msg(channel, "Hostname: " + a.getHostName() + " Address: " + a.getHostAddress());
        }
        }
        bot.msg(channel, "Host list end");
    }

    public String getName() {
        return "network";
    }

    public String getUsage() {
        return "displays the hosts found in the bots local network";
    }

}
