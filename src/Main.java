import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter user type (adminUser, powerUser, regularUser): ");
        String userType = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();


        if (authenticate(userType, password)) {
            switch (userType) {
                case "adminUser":
                    System.out.println("Welcome to the Admin Panel.");
                    AdminUser adminUser = new AdminUser();
                    adminPanel(adminUser);
                    break;
                case "powerUser":
                    System.out.println("Welcome to the Power User Panel.");
                    PowerUser powerUser = new PowerUser();
                    powerUserPanel(powerUser);
                    break;
                case "regularUser":
                    System.out.println("Welcome to the Regular User Panel.");
                    RegularUser regularUser = new RegularUser();
                    regularUserPanel(regularUser);
                    break;
                default:
                    System.out.println("Invalid user type.");
                    break;
            }
        } else {
            System.out.println("Authentication failed. Incorrect password or user type.");
        }
        scanner.close();
    }


    private static boolean authenticate(String userType, String password) {
        return (userType.equalsIgnoreCase("adminUser") && password.equals("admin123"))
                || (userType.equalsIgnoreCase("powerUser") && password.equals("power123"))
                || (userType.equalsIgnoreCase("regularUser") && password.equals("regular123"));
    }


    private static void adminPanel(AdminUser adminUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin Panel Options:");
        System.out.println("1. View Users");
        System.out.println("2. Add User");
        System.out.println("3. Modify User");
        System.out.println("4. Rename File");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    adminUser.viewUsers();
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String userPassword = scanner.nextLine();
                    System.out.print("Enter user type (RegularUser, PowerUser, AdminUser): ");
                    String userType = scanner.nextLine();
                    adminUser.writeUsers(username, email, userPassword, userType);
                    break;
                case 3:
                    System.out.print("Enter UserID to modify: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new user type: ");
                    String newUserType = scanner.nextLine();
                    adminUser.modifyUser(userId, newUsername, newEmail, newUserType);
                    break;
                case 4:
                    System.out.print("Enter current file path: ");
                    String oldFilePath = scanner.nextLine();
                    System.out.print("Enter new file path: ");
                    String newFilePath = scanner.nextLine();
                    adminUser.renameFile(oldFilePath, newFilePath);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error in Admin Panel: " + e.getMessage());
        }
    }


    private static void powerUserPanel(PowerUser powerUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Power User Panel Options:");
        System.out.println("1. View Users");
        System.out.println("2. Add User Detail");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    powerUser.viewUsers();
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter user type (RegularUser, PowerUser): ");
                    String userType = scanner.nextLine();
                    powerUser.writeUsers(username, email, password, userType);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error in Power User Panel: " + e.getMessage());
        }
    }


    private static void regularUserPanel(RegularUser regularUser) {
        System.out.println("Regular User Panel Options:");
        System.out.println("1. View Users");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            if (choice == 1) {
                regularUser.viewUsers();
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (IOException e) {
            System.out.println("Error in Regular User Panel: " + e.getMessage());
        }
    }
}

