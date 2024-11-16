
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
class PowerUser implements Reader, Writer {
    String filePath = "C:\\Users\\wahid\\IdeaProjects\\Lab7\\src\\user.csv";


    public void viewUsers() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Reading from User.csv:");
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                System.out.println("UserID: " + fields[0] + ", Username: " + fields[1] + ", Email: " + fields[2] +
                        ", Password: " + fields[3] + ", UserType: " + fields[4]);
            }
        } catch (IOException e) {
            System.out.println("Error reading User.csv: " + e.getMessage());
            throw e;
        }
    }


    public void writeUsers(String username, String email, String password, String userType) throws IOException {
        int userId = generateNewUserId();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String newUser = String.join(",", String.valueOf(userId), username, email, password, userType);
            writer.write(newUser);
            writer.newLine();
            System.out.println("PowerUser: User added to User.csv.");
        } catch (IOException e) {
            System.out.println("Error writing to User.csv: " + e.getMessage());
            throw e;
        }
    }

    private int generateNewUserId() throws IOException {
        int id = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                id++;
            }
        }
        return id;
    }
}


