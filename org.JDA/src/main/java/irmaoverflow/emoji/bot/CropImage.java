import java.util.*;
import java.io.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class CropImage {

  public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height)
  {
      BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
      return croppedImage;
  }

  public static void main(String [] args) throws IOException
  {
    // Read in path to image.
    File imageFile = new File("james.jpg");

    // Create a bufferered Image object using the path to the image.
    BufferedImage bufferedImage = ImageIO.read(imageFile);

    // Warren: int x = 335, y = 192, xRange = 657, yRange = 514;
    // James : int x = 375, y = 836, xRange = 1058, yRange = 1519;
    // Coordinates to crop:
    int x = 375, y = 836, xRange = 1058, yRange = 1519;

    // Crop the image. JAMES: 375, 836, 1058/2, 1519
    BufferedImage croppedImage = cropImage(bufferedImage, x, y, xRange-x, yRange-y);

    // Path to cropped image.
    File pathFile = new File("james2.jpg");

    // Writing the cropped image.
    ImageIO.write(croppedImage,"jpg", pathFile);
  }
}
