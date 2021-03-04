import com.puppycrawl.tools.checkstyle.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUtils {


    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        File source = new File(sourceDirectory);
        File dest = new File(destinationDirectory);
        copyFiles(source, dest);
    }





    public static void copyFiles (File source, File dest) throws IOException {

        Files.copy(source.toPath(), dest.toPath());

        if (source.isDirectory()) {
            File []files = source.listFiles();
            for (File s : files) {
                Files.copy(s.toPath(), dest.toPath());
                if (s.isFile()) dest = new File(dest.toPath()+"/"+s.getName());
            }
        }
    }
}
