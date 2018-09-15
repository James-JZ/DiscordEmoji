package irmaoverflow.emoji.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Bot {
    final static String token = "NDkwNTY1MTA0OTIwOTUyODUz.Dn7J7w.EzVC6LeXSKsiuX2vbXipzat9zQ0";

    public static void main(String args[]) {
        try {
            JDA jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        }
        catch(Exception e) {
            System.out.println("Token invalid");
        }

        System.out.println("Token Loaded");
    }
}
