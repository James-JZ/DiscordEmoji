//package irmaoverflow.emoji.bot;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.protocol.HTTP;
//import org.json.JSONObject;
//
//import javax.xml.bind.DatatypeConverter;
//import java.io.File;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmojiUpload {
//    String guildID;
//    File img;
//    final String url ="https://discordapp.com/api/guilds/" + guildID + "/emojis";
//
//    EmojiUpload(File image, String id) {
//        img = image;
//        this.guildID = id;
//    }
//    public void sendEmojiRequest() {
//        System.out.println(url);
//        JSONObject obj = new JSONObject();
//        try {
//            byte[] array = Files.readAllBytes(img.toPath());
//
//        String imageString = "data:image/jpg," +
//                DatatypeConverter.printBase64Binary(array);
//        obj.put("name", img.getName());
//        obj.put("image", imageString);
//        System.out.println(obj);
//        sendPost(obj);
//        }
//        catch(Exception e) {
//            System.out.println("Byte conversion failed");
//        }
//
//    }
//    private void sendPost(JSONObject obj) throws Exception {
//        HttpClient httpclient = HttpClients.createDefault();
//        HttpPost httppost = new HttpPost(url);
//        httppost.setHeader("Authorization: Bot", "NDkwNTY1MTA0OTIwOTUyODUz.Dn9wfg.Ir3GOBntjQg3Ci05eGiStOTk3Tw");
//        httppost.addHeader("Authorization: Bot", "NDkwNTY1MTA0OTIwOTUyODUz.Dn9wfg.Ir3GOBntjQg3Ci05eGiStOTk3Tw");
//        httppost.setHeader("User-Agent", "emojiBot (http://www.github.com/RandyT97, v0.1)");
//        httppost.addHeader("User-Agent", "emojiBot (http://www.github.com/RandyT97, v0.1)");
//        httppost.setHeader("Content-Type", "application/json");
//        httppost.addHeader("Content-Type", "application/json");
////        // Request parameters and other properties.
////        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
////        params.add(new BasicNameValuePair("Authorization", "Bot NDkwNTY1MTA0OTIwOTUyODUz.Dn9wfg.Ir3GOBntjQg3Ci05eGiStOTk3Tw"));
////        params.add(new BasicNameValuePair("User-Agent", "emojiBot (http://www.example.com, v0.1)"));
////        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//        StringEntity entity = new StringEntity(obj.toString(), HTTP.UTF_8);
//        //Execute and get the response.
//
//        httppost.setEntity(entity);
////        httpclient.execute(httppost);
////        httpclient.execute(httppost);
////        httpclient.execute(httppost);
//        HttpResponse resp = httpclient.execute(httppost);
//        System.out.println(resp);
////        System.out.println(httppost.toString());
////        entity = response.getEntity(response);
////
////        if (entity != null) {
////            InputStream instream = entity.getContent();
////            try {
////                // do something useful
////            } finally {
////                    instream.close();
////            }
////        }
//    }
//
//}
//
