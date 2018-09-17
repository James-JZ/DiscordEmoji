package irmaoverflow.emoji.bot;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
    public static void main (String args[]) {
        try {
            JDA jda = new JDABuilder(AccountType.BOT).setToken(Constants.TOKEN).buildBlocking();
            jda.addEventListener(new MessageListener());
        }
        catch(Exception e) {
            System.out.print(e);
        }
    }
}
