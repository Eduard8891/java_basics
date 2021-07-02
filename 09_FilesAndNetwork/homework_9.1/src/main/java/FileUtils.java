import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileUtils extends SimpleFileVisitor {

    private static final long MEGABYTE = 1048576;
    private static final long GIGABYTE = 1073741824;
    private static final long KILOBYTE = 1024;

    public static long calculateFolderSize(String path) throws IOException {

        long size = Files.walk(Paths.get(path))
                .map(Path::toFile)
                .filter(File::isFile)
                .mapToLong(File::length)
                .sum();
        if (size < KILOBYTE) {
            System.out.println("Общий размер всех файлов и папок в пути " + path + " : " + size + " Байт");
            return size;
        }
        if (size > KILOBYTE & size < MEGABYTE) {
            System.out.println("Общий размер всех файлов и папок в пути " + path + " : " + size / KILOBYTE + " КБ");
            return size / KILOBYTE;
        } else if (size > MEGABYTE & size < GIGABYTE) {
            System.out.println("Общий размер всех файлов и папок в пути " + path + " : " + size / MEGABYTE + " МБ");
            return size / MEGABYTE;
        } else {
            long remainder = (size % GIGABYTE) / MEGABYTE;

            String remainderString = String.valueOf(remainder);
            if (remainder < 100) {
                remainderString = "0" + remainder;
            }
            if (remainder < 10) {
                remainderString = "00" + remainder;
            }
            System.out.println("Общий размер всех файлов и папок в пути "
                    + path + " : " + size / GIGABYTE + "." + remainderString + " ГБ");
            return size / GIGABYTE;
        }
    }
}
