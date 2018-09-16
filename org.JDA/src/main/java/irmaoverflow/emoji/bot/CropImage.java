package irmaoverflow.emoji.bot;

import com.google.cloud.vision.v1.Vertex;

import java.net.URL;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CropImage {

  public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height)
  {
      BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
      return croppedImage;
  }

  public static void crop(String url, ArrayList<Vertex> coords) throws IOException
  {
    // Read in path to image.
    File imageFile = new File(url);

    // Create a bufferered Image object using the path to the image.
    BufferedImage bufferedImage = ImageIO.read(imageFile);

    // Warren: int x = 335, y = 192, xRange = 657, yRange = 514;
    // James : int x = 375, y = 836, xRange = 1058, yRange = 1519;
    // Coordinates to crop:

    int x = coords.get(0).getX();
    int y = coords.get(0).getY();
    int xRange = coords.get(1).getX();
    int yRange = coords.get(2).getY();
    System.out.println("YES");
    // Crop the image. JAMES: 375, 836, 1058/2, 1519
    BufferedImage croppedImage = cropImage(bufferedImage, x, y, xRange-x, yRange-y);

    // Path to cropped image.
    File pathFile = new File("face.jpg");

    // Writing the cropped image.
    try {
      ImageIO.write(croppedImage, "jpg", new File("results.jpg"));
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }
}
