import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BookVacation {

    public static void main(String[] args) {
        bookVacation();
    }

    public static void bookVacation() {
        Scanner scanner = new Scanner(System.in);

        // Get account number
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        String folderName = "acc_" + accountNumber;
        Path folderPath = Paths.get(System.getProperty("user.home"), "Desktop/Outputs", folderName);
        Path filePath = folderPath.resolve(folderName + ".json");

        // Check if user exists
        if (!Files.exists(filePath)) {
            System.out.println("Account does not exist. Please register first.");
            return;
        }

        // Validate booking type
        System.out.print("Enter booking type (hotel, apartment, villa): ");
        String bookingType = scanner.nextLine().toLowerCase();
        if (!bookingType.equals("hotel") && !bookingType.equals("apartment") && !bookingType.equals("villa")) {
            System.out.println("Invalid booking type.");
            return;
        }

        // Get booking details
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDate = scanner.nextLine();
        System.out.print("Enter number of people: ");
        int numOfPeople = scanner.nextInt();
        System.out.print("Enter number of days stay: ");
        int numOfDays = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Generate random booking number
        int bookingNumber = (int) (Math.random() * 100000);
        String bookingName = "vcc_" + bookingNumber;
        String bookingFileName = "vcc_" + bookingNumber + ".json";
        Path bookingFilePath = folderPath.resolve(bookingFileName);

        // Add booking to user's account
        addBookingToFile(filePath, bookingFileName);

        // Save booking details in a new JSON file
        saveBookingDetails(bookingFilePath, bookingType, destination, startDate, endDate, numOfPeople, numOfDays);
    }

    private static void addBookingToFile(Path userFilePath, String bookingName) {
        try {
            // Read existing file
            String content = new String(Files.readAllBytes(userFilePath));
            JSONObject userDetails = new JSONObject(content);
            JSONArray bookings = userDetails.getJSONArray("bookings");

            // Add booking number to array
            bookings.put(bookingName);

            // Write updated JSON to file
            try (FileWriter file = new FileWriter(userFilePath.toString())) {
                file.write(userDetails.toString(4)); // pretty print with 4-space indentation
                System.out.println("Booking reference added to user account.");
            }
        } catch (IOException e) {
            System.err.println("Failed to read or write JSON file: " + e.getMessage());
        }
    }

    private static void saveBookingDetails(Path bookingFilePath, String bookingType, String destination, String startDate, String endDate, int numOfPeople, int numOfDays) {
        try {
            // Create booking JSON object
            JSONObject bookingDetails = new JSONObject();
            bookingDetails.put("bookingType", bookingType);
            bookingDetails.put("destination", destination);
            bookingDetails.put("startDate", startDate);
            bookingDetails.put("endDate", endDate);
            bookingDetails.put("numOfPeople", numOfPeople);
            bookingDetails.put("numOfDays", numOfDays);

            // Write JSON object to file
            try (FileWriter file = new FileWriter(bookingFilePath.toString())) {
                file.write(bookingDetails.toString(4)); // pretty print with 4-space indentation
                System.out.println("Booking details saved successfully. Details saved in: " + bookingFilePath.toString());
            }
        } catch (IOException e) {
            System.err.println("Failed to write booking details to file: " + e.getMessage());
        }
    }
}
