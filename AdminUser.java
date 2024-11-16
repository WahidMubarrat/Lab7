import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
 class AdminUser implements Reader,Writer,AdminWorks {
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

     public void modifyUser(String userId, String newUserType) throws IOException {
         File inputFile = new File(filePath);
         File tempFile = new File("tempUser.csv");


         try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
              BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

             String line;
             boolean userFound = false;

             while ((line = reader.readLine()) != null) {
                 String[] fields = line.split(",");

                 if (fields[0].equals(userId)) {

                     fields[4] = newUserType;
                     userFound = true;
                     System.out.println("AdminUser: User with ID " + userId + " type updated to " + newUserType);
                 }

                 writer.write(String.join(",", fields));
                 writer.newLine();
             }


             if (!userFound) {
                 System.out.println("AdminUser: User with ID " + userId + " not found.");
             }

         } catch (IOException e) {
             System.out.println("Error modifying User.csv: " + e.getMessage());
             throw e;
         }


         if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
             System.out.println("Error replacing old file with the updated file.");
         }
     }

    /* public boolean renameFile(String oldFilePath, String newFilePath) throws IOException {
        Path oldFile = Paths.get(oldFilePath);
        Path newFile = Paths.get(newFilePath);

        try {
            Files.move(oldFile, newFile);
            System.out.println("File renamed successfully from " + oldFilePath + " to " + newFilePath);
            return true;
        } catch (IOException e) {
            System.out.println("Error renaming file: " + e.getMessage());
            return false;
        }
    }
*/


     public boolean renameFile(String oldFilePath, String newFilePath) {
         File oldFile = new File(oldFilePath);
         File newFile = new File(newFilePath);


         boolean renamed = oldFile.renameTo(newFile);

         if (renamed) {
             System.out.println("File renamed successfully from " + oldFilePath + " to " + newFilePath);
         } else {
             System.out.println("Error: File renaming failed.");
         }

         return renamed;
     }


     public void writeUsers(String username, String email, String password, String userType) throws IOException {
     int id = generateNewUserId();
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
         String newUser = String.join(",", String.valueOf(id), username, email, password, userType);
         writer.write(newUser);
         writer.newLine();
         System.out.println("PowerUser: User added to User.csv.");
     } catch (IOException e) {
         System.out.println("Error writing to User.csv: " + e.getMessage());
         throw e;
     }
 }

 int generateNewUserId() throws IOException {
     int id = 1;
     try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
         while (reader.readLine() != null) {
            id++;
         }
     }
     return id;
 }
}