package irmaoverflow.emoji.bot;

import com.google.cloud.vision.v1.Vertex;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.entities.Message.Attachment;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.ArrayList;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String imageURL = new String();
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());

        }
        else
        {
            CloudFunctions cf = new CloudFunctions(null);
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
            if(event.getMessage().getContentDisplay().substring(0,1).equals(Constants.PREFIX)) {
                String input= event.getMessage().getContentDisplay();
                input = input.substring(1); //Remove prefix
                System.out.println(input);
                if((event.getMessage().getAttachments().get(0)).isImage()){
//                    imageURL = event.getMessage().getAttachments().get(0).getUrl();
                    File file = new File(event.getMessage().getAttachments().get(0).getFileName());

                    if(event.getMessage().getAttachments().get(0).download(file)) {

                        String downloadedFile = event.getMessage().getAttachments().get(0).getFileName();
                        System.out.println("downloaded" + downloadedFile);
                        cf = new CloudFunctions(downloadedFile);


                        try {
                            ArrayList<Vertex> vertices = cf.getVertices();
                            CropImage.crop(downloadedFile, vertices);
                            Message message = new MessageBuilder().append("My message").build();
                            event.getTextChannel().sendFile(new File("results.jpg"), message).queue();
                            


                        } catch (Exception e) {

                        }
                    }

                }
                //pass onto googCloud url




            }
        }

    }
}