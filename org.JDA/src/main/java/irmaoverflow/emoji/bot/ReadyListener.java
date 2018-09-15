package irmaoverflow.emoji.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.util.EventListener;

public class ReadyListener implements EventListener
{
    public static void main(String[] args)
            throws LoginException, RateLimitedException, InterruptedException
    {
        // Note: It is important to register your ReadyListener before building
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("token")
                .addEventListener(new ReadyListener())
                .buildBlocking();
    }

    public void onEvent(Event event)
    {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}