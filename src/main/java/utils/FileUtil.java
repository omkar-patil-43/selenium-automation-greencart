package utils;
import java.io.File;
import java.io.FilenameFilter;

public class FileUtil {

    public static void deleteAllScreenshots(String folderPath) {
        File folder = new File(folderPath);

        // Use anonymous class instead of lambda
        File[] files = folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".png");
            }
        });

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }
}
