import java.io.File;

public class FileUtils {

    private static long total = 0;

    public static long calculateFolderSize(String path) {

        try {
            File folder = new File(path);
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isDirectory())
                {
                    calculateNestedFolders(path+"/"+file.getName());
                }
                System.out.println(file.getName()+": "+file.length()+" byte");
                total += file.length();
            }
            total = total/1048576;
            System.out.println("Общий размер всех файлов и папок в \""+path+"\" равен "+total+" МБ");
            return total;
        }
        catch (NullPointerException ex) {
            System.out.println("По этому пути ничего не найдено! Попробуйте еще раз.");
        }
        return total;
    }

    public static void calculateNestedFolders(String pathNestedFolders)
    {
        File nestedFolder = new File(pathNestedFolders);
        File[] nestedFiles = nestedFolder.listFiles();
        for (File file : nestedFiles)
        {
            if (file.isDirectory())
            {
                calculateNestedFolders(pathNestedFolders+"/"+file.getName());
            }
            System.out.println("\t"+file.getName()+": "+file.length()+" byte");
            total += file.length();
        }
    }
}
