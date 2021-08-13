package mathpck;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ImageConvert {

    public String getrescale(String inputImagePath) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = 28, scaledHeight = 28;
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        String outputImagePath = "/home/surya/rescaled.png";

        ImageIO.write(outputImage, "png", new File(outputImagePath));
        return outputImagePath;
    }

    public Matrix convertTo2D(String inputImagePath) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        final byte[] pixels = ((DataBufferByte) inputImage.getRaster().getDataBuffer()).getData();
        final int width = inputImage.getWidth();
        final int height = inputImage.getHeight();
        final boolean hasAlphaChannel = inputImage.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {

            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        int pxval=0;

        Matrix ans=new Matrix(784,1);
        for(int i=0;i<28;i++){
            for(int j=0;j<28;j++){
                ans.setval((255-result[i][j])/255,pxval++);
            }
        }
        return ans;
    }
}
