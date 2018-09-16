package irmaoverflow.emoji.bot;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
    final static String token = "NDkwNTY1MTA0OTIwOTUyODUz.Dn7J7w.EzVC6LeXSKsiuX2vbXipzat9zQ0";
    public static void main (String args[]) {
        String fileName = new String();
        try {

            JDA jda = new JDABuilder(AccountType.BOT).setToken(Constants.TOKEN).buildBlocking();
            jda.addEventListener(new MessageListener());
            CloudFunctions cf = new CloudFunctions(fileName);
        }
        catch(Exception e) {
            System.out.print(e);
        }
    }
}
