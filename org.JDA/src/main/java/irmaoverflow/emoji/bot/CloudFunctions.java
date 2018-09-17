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

public class CloudFunctions {
    String filePath;

    CloudFunctions(String path) {
        filePath = path;
    }

    public ArrayList<Vertex> getVertices() throws Exception {
        ArrayList<Vertex> ans = new ArrayList<>();
        // Instantiates a client
        try {
            ImageAnnotatorClient vision = ImageAnnotatorClient.create();
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
        return ans;
    }
}