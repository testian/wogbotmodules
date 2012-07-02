/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import core.Session;
import core.SessionCommand;
import core.SessionException;
import core.User;
import core.TimedMonitor;
/**
 *
 * @author testi
 */
public class MonitorCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        synchronized(bot) {
        TimedMonitor monitor = bot.getMonitor(channel);
        monitor.setMonitoringDuration(2*3600*1000);
        }
        bot.msg(channel, "Monitoring events for the next 2 hours");
    }

    public String getName() {
        return "monitor";
    }

    public String getUsage() {
        return "Tells the bot to monitor events to the channel for the next 2 hours";
    }

}
