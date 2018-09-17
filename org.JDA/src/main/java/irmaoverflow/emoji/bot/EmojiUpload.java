package irmaoverflow.emoji.bot;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.managers.GuildController;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;

public class EmojiUpload {
    Guild guild;
    String url;
    String imageString;
    String fileName;
    File img;
    GuildController guildController;

//    GuildController guildController = new GuildController();


    EmojiUpload(Guild guild, String fileName) {
//        this.guildID = id;
        this.url = "https://discordapp.com/api/guilds/" + guild.getId() + "/emojis";
        this.fileName = fileName;
        guildController = new GuildController(guild);
    }
    public void sendEmojiRequest() {
        try {
                img = new File("results.jpg");
                StringBuilder sb = new StringBuilder();
                sb.append("data:image/jpeg;base64,");
                sb.append(Base64.encodeBase64String(FileUtils.readFileToByteArray(img)));
                imageString = sb.toString();

                Icon emoji = Icon.from(img);
                System.out.println(guildController.createEmote(fileName, emoji).queue());
//                sendPost();
                File dl = new File(fileName);
                dl.delete();
        }
        catch(Exception e) {
            System.out.println("Byte conversion failed");
        }

    }

    private void sendPost() throws Exception {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String bodyStr = String.format("{\"name\": \"%s\",\"image\": \"%s\"}", FilenameUtils.removeExtension(fileName), imageString);
        System.out.println(bodyStr);



        RequestBody body = RequestBody.create(mediaType, bodyStr);
        Request request = new Request.Builder()
                .url("https://discordapp.com/api/guilds/490352653612744707/emojis")
                .post(body)
                .addHeader("cookie", "__cfduid=d6e157e16c5a98b7dc4157c495bea28f11537073072")
                .addHeader("authorization", "Bot " + Constants.TOKEN)
                .addHeader("user-agent", "emojiBot (http://www.github.com/RandyT97, v0.1)")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}

