package irmaoverflow.emoji.bot;

import com.google.cloud.vision.v1.Vertex;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.File;
import java.util.ArrayList;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
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
                if((event.getMessage().getAttachments().get(0)).isImage()){
                    File file = new File(event.getMessage().getAttachments().get(0).getFileName());

                    if(event.getMessage().getAttachments().get(0).download(file)) {

                        String downloadedFile = event.getMessage().getAttachments().get(0).getFileName();
                        cf = new CloudFunctions(downloadedFile);


                        try {
                            ArrayList<Vertex> vertices = cf.getVertices();
                            CropImage.crop(downloadedFile, vertices);
                            Message message = new MessageBuilder().append("My message").build();
                            event.getTextChannel().sendFile(new File("results.jpg"), message).queue();

                            EmojiUpload eu = new EmojiUpload(event.getMessage().getGuild(), downloadedFile);
                            eu.sendEmojiRequest();

                            File xd = new File("results.jpg");
                            xd.delete();







                        } catch (Exception e) {

                        }
                    }

                }

            }
        }

    }
}