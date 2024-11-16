


import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    private static final String TEMP_FILE_PATH = "C:\\Users\\wahid\\IdeaProjects\\Lab7\\src\\user.csv";
    private AdminUser adminUser;

    @BeforeEach
    public void setUp() {

        adminUser = new AdminUser();

    }

    @Test
    public void testViewUsers() throws IOException {

        adminUser.writeUsers("umar", "umar@p6", "umarp6", "RegularUser");


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        adminUser.viewUsers();


        String output = outputStream.toString();

        assertTrue(output.contains("Username: umar"), "Username should be displayed.");
        assertTrue(output.contains("Email: umar@p6"), "Email should be displayed.");
        assertTrue(output.contains("UserType: RegularUser"), "User Type should be displayed.");

    }


    @Test
    public void testWriteUsers() throws IOException {

        String username = "navid";
        String email = "navid@ma";
        String password = "navidma";
        String userType = "RegularUser";
        adminUser.writeUsers(username, email, password, userType);


        try (BufferedReader reader = new BufferedReader(new FileReader(TEMP_FILE_PATH))) {
            String line = reader.readLine();

            String[] fields = line.split(",");
           assertEquals("1", fields[0]);
            assertEquals("navid", fields[1]);
            assertEquals("navid@ma", fields[2]);
            assertEquals("navidma", fields[3]);
            assertEquals("RegularUser", fields[4]);
        }
    }


    @Test
    public void testModifyUser() throws IOException {




        adminUser.modifyUser("1", "PowerUser");


        try (BufferedReader reader = new BufferedReader(new FileReader(TEMP_FILE_PATH))) {
            String line = reader.readLine();

            String[] fields = line.split(",");
            assertEquals("PowerUser", fields[4]);
        }
    }

@Test
public void testRenameFile() {

    String oldFilePath = "testOldFile.csv";
    String newFilePath = "testNewFile.csv";

    File oldFile = new File(oldFilePath);
    File newFile = new File(newFilePath);

    try {

        boolean isFileCreated = oldFile.createNewFile();
        assertTrue(isFileCreated, "Failed to create test old file.");


       if (newFile.exists()) {
            assertTrue(newFile.delete(), "Failed to clean up existing new file.");
        }


        AdminUser adminUser = new AdminUser();
        boolean isRenamed = adminUser.renameFile(oldFilePath, newFilePath);


        assertTrue(isRenamed, "File renaming failed.");
        assertFalse(oldFile.exists(), "Old file still exists after renaming.");
        assertTrue(newFile.exists(), "New file does not exist after renaming.");

    } catch (IOException e) {
        fail("Test setup failed due to IOException: " + e.getMessage());
    }

}
}

