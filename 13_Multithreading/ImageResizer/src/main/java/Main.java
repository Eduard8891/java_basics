import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main
{
    public static int newWidth = 360;

    public static void main(String[] args)
    {
        String srcFolder = "e:/img1";
        String dstFolder = "e:/img2";
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int middle = files.length/2;
        int remainder = files.length - middle;
        int stream1 = middle/2;
        int stream2 = middle - stream1;
        int stream3 = remainder/2;
        int stream4 = remainder - stream3;


        long start = System.currentTimeMillis();
        File[] files1 = new File[stream1];
        System.arraycopy(files, 0, files1, 0, stream1);
        Resizer resizer1 = new Resizer(files1, newWidth, dstFolder, start);
        resizer1.start();
        File[] files2 = new File[stream2];
        System.arraycopy(files, stream1, files2, 0, stream2);
        Resizer resizer2 = new Resizer(files2, newWidth, dstFolder, start);
        resizer2.start();
        File[] files3 = new File[stream3];
        System.arraycopy(files, stream2+stream1, files3, 0, stream3);
        Resizer resizer3 = new Resizer(files3, newWidth, dstFolder, start);
        resizer3.start();
        File[] files4 = new File[stream4];
        System.arraycopy(files, stream3+stream2+stream1, files4, 0, stream4);
        Resizer resizer4 = new Resizer(files4, newWidth, dstFolder, start);
        resizer4.start();


    }
}
