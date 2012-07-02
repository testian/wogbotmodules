/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import java.net.*;
import core.*;
import java.io.*;
//import org.schwering.irc.lib.IRCUser;
/**
 *
 * @author testi
 */
public class IpCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        try {
        URL dyndns = new URL("http://checkip.dyndns.org/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(dyndns.openConnection().getInputStream()));
        String line = reader.readLine();
        if (line == null) throw new IOException();
        int begin = 6+line.lastIndexOf("<body>");
        int end = line.lastIndexOf("</body>");
        String body = line.substring(begin,end);
        int ipstart = body.lastIndexOf(" ")+1;
        bot.msg(channel, body.substring(ipstart));
        } catch (MalformedURLException ex) {
        }
        catch (Exception ex) {
        bot.msg(channel, "Failed to retrieve external IP-address");
        }
    }

    public String getName() {
        return "ip";
    }

    public String getUsage() {
        return "Returns the ip address of the bot";
    }

}
