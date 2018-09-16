package irmaoverflow.emoji.bot;

// Imports the Google Cloud client library

import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class CloudFunctions {
    String filePath;
    CloudFunctions(String path) {
        filePath = path;
    }
//
//    public void saveImage(String imageUrl, String destinationFile) throws IOException {
//        URL url = new URL(imageUrl);
//        InputStream is = url.openStream();
//        OutputStream os = new FileOutputStream(destinationFile);
//
//        byte[] b = new byte[2048];
//        int length;
//
//        while ((length = is.read(b)) != -1) {
//            os.write(b, 0, length);
//        }
//
//        is.close();
//        os.close();
//    }

    public ArrayList<Vertex> getVertices() throws Exception {
        ArrayList<Vertex> ans = new ArrayList<>();
        // Instantiates a client
        try {
            ImageAnnotatorClient vision = ImageAnnotatorClient.create();
            // The path to the image file to annotate
//            String fileName = "https://www.dropbox.com/s/q7i1le9rqg4jwgw/james.jpg";
//            saveImage(fileName, "./input.jpg");

            // Reads the image file into memory
            Path path = Paths.get(filePath);
            byte[] data = Files.readAllBytes(path);
            ByteString imgBytes = ByteString.copyFrom(data);

            // Builds the image annotation request
            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            // Performs label detection on the image file
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return ans;
                }

                for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                    for(Vertex v : annotation.getFdBoundingPoly().getVerticesList()) {
                        ans.add(v);

                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(ans);
        return ans;
    }
}