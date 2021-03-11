import com.puppycrawl.tools.checkstyle.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileUtils {


    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        copyFiles(Paths.get(sourceDirectory), Paths.get(destinationDirectory));
    }

    public static void copyFiles (Path source, Path dest) throws IOException {
        File [] files = new File(String.valueOf(source)).listFiles();
        File destination = new File(dest.toString());
        if (!destination.exists()) destination.mkdirs();
        for (File f: files) {
            if (f.isDirectory()) copyFiles(f.toPath(), Path.of(dest+"/"+f.getName()));
            else Files.copy(f.toPath(), Path.of(dest+"/"+f.getName()));
        }
    }
}

