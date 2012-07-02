/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modules;
import core.Session;
import core.SessionCommand;
import core.SessionException;
import core.User;
import utils.SimpleStringTokenizer;
/**
 *
 * @author testi
 */
public class MsgCommand implements SessionCommand {

    public void execute(Session bot, String channel, User user, String message) throws SessionException {
        bot.assertAdmin(user);
        SimpleStringTokenizer t = new SimpleStringTokenizer(message);
        if (t.hasMoreTokens()) {
        String target = t.nextToken();
        bot.msg(target, t.remainingString());
        } else {
        bot.msg(channel, "Kein Ziel angegeben, " + user.getNick() + ".");
        }
    }

    public String getName() {
        return "msg";
    }

    public String getUsage() {
        return "msg-Funktion";
    }

}
