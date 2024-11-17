import java.io.File;
import java.io.IOException;
public class FileMaker {
    private static FileMaker instance;
    private final String userFilePath = "User.csv";
    private final String adminFilePath = "Admin.csv";

    private FileMaker() {
        initializeFiles();
    }

    public static FileMaker getInstance() {
        if (instance == null) {
            instance = new FileMaker();
        }
        return instance;
    }

    private void initializeFiles() {
        createFile(userFilePath);
        createFile(adminFilePath);
    }

    private void createFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println(filePath + " created successfully.");
                }
            } catch (IOException e) {
                System.err.println("Error creating " + filePath + ": " + e.getMessage());
            }
        }
    }

    public File getUserFile() {
        return new File(userFilePath);
    }

    public File getAdminFile() {
        return new File(adminFilePath);
    }
}

