package irmaoverflow.emoji.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter
{
    final static String token = "NDkwNTY1MTA0OTIwOTUyODUz.Dn7J7w.EzVC6LeXSKsiuX2vbXipzat9zQ0";
    final static String prefix = "!";
    public static void main(String args[]) throws Exception
    {

        TextChannel channel = new evt.getChannel();
        TextChannel test =


        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(token)
                    .buildBlocking();
                jda.setAutoReconnect(true);
                jda.addEventListener(new Bot());
                System.out.print("Hello");


        }
        catch(Exception e) {
            System.out.println("Token invalid");
        }

        System.out.println("Token Loaded");

    }
    public void OnMessageReceived(MessageReceivedEvent evt) {
        // Objects
        User objUser = evt.getAuthor();
        MessageChannel objMsgCh = evt.getChannel();
        Message objMsg = evt.getMessage();
        System.out.print("Print something");
        // Commmands
        if (objMsg.getContentRaw().equalsIgnoreCase(prefix + "ping"))
        {

            objMsgCh.sendMessage(objUser.getAsMention() + "Pong").queue();

        }
        else{
            objMsgCh.sendMessage(objUser.getAsMention() + "This is incorrect"). queue();
        }

    }
}
