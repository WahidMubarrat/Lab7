
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class RegularUser implements Reader{
   String filePath = "C:\\Users\\wahid\\IdeaProjects\\Lab7\\src\\user.csv";

    @Override
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
}





