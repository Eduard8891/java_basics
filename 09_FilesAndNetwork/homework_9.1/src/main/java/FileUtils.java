import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileUtils extends SimpleFileVisitor{

    public static long calculateFolderSize(String path) throws IOException {

        long size = Files.walk(Paths.get(path))
                .map(Path::toFile)
                .filter(File::isFile)
                .mapToLong(File::length)
                .sum();

        long sizeTotal = size/1048576;

        System.out.println("Общий размер всех файлов и папок в пути " + path +" : "+ + sizeTotal + " Мб");

        return size;
    }
}
