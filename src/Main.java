import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Register");
            System.out.println("2. Book Vacation");
            System.out.println("3. My Total Vacations");
            System.out.println("4. Pay Bill");
            System.out.println("5. Close");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Register.register();
                    break;
                case 2:
                    BookVacation.bookVacation();
                    break;
                case 3:
                    MyTotalVacations.myTotalVacations();
                    break;
                case 4:
                    PayBill.payBill();
                    break;
                case 5:
                    System.out.println("Thank You....! Visit Again..!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

}
