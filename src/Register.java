import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Register {

    public static void register() {
        Scanner scanner = new Scanner(System.in);

        // Get user details
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter your location: ");
        String location = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        // Generate random account number
        int accountNumber = (int) (Math.random() * 100000);

        // Create folder and file paths
        String folderName = "acc_" + accountNumber;
        String fileName = folderName + ".json";
        Path folderPath = Paths.get(System.getProperty("user.home"), "Desktop/Outputs", folderName);
        Path filePath = folderPath.resolve(fileName);

        try {
            // Create folder
            Files.createDirectories(folderPath);

            // Create JSON object
            JSONObject userDetails = new JSONObject();
            userDetails.put("name", name);
            userDetails.put("age", age);
            userDetails.put("gender", gender);
            userDetails.put("location", location);
            userDetails.put("phonenumber", phoneNumber);
            userDetails.put("bookings", new org.json.JSONArray());

            // Write JSON object to file
            try (FileWriter file = new FileWriter(filePath.toString())) {
                file.write(userDetails.toString(4)); // pretty print with 4-space indentation
                System.out.println("User registered successfully. Details saved in: " + filePath.toString());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while registering the user: " + e.getMessage());
        }
    }
}
