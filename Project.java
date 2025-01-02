import java.io.*;
import java.util.*;

public class Project {

    private static boolean login = false;
    private static boolean book = false;
    private static String username = "";


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" =================================================================================");
        System.out.println(" ||\t\t\t\tWelcome to Ahmedabad Station\t\t\t\t||");
        System.out.println(" =================================================================================");

        String input = "0";

        while (!input.equals("8")) {
            System.out.println("\n\t\t==================================================\n\t\t\t\t\tMenus \n\t\t==================================================");
            System.out.println("\n1. Login / Sign up\n2. View today's Schedule\n3. Book a ticket\n4. View your ticket\n5. View latency of trains\n6. Order food on your seat\n7. Give Feedback\n8. Exit");
            System.out.print("\nPlease select one of these: \t");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    handleLoginOrSignup(scanner);
                    break;
                case "2":
                    viewSchedule();
                    break;
                case "3":
                    bookTicket(scanner);
                    break;
                case "4":
                    viewTicket();
                    break;
                case "5":
                    viewLatency();
                    break;
                case "6":
                    orderFood(scanner);
                    break;
                case "7":
                    giveFeedback(scanner);
                    break;
                case "8":
                    System.out.println("\n\nThank you!");
                    break;
                default:
                    System.out.println("\nSelect one from the serial numbers. Press Enter to continue.");
            }
        }
        scanner.close();
    }

    private static void handleLoginOrSignup(Scanner scanner) throws IOException {
        System.out.println("\n1) Sign up\n2) Login");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            System.out.println("Please fill these details");
            System.out.print("Username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Create a password: ");
            String password = scanner.nextLine();

            if (!newUsername.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                addUserToCSV(newUsername, email, password);
                username = newUsername;
                login = true;
                System.out.println("\n||\tSign up and Login success\t||");
            } else {
                System.out.println("\nThe details must not be empty..\n");
            }
        } else if (choice.equals("2")) {
            System.out.print("Enter your Username: ");
            String inputUsername = scanner.nextLine();
            System.out.print("Enter your Password: ");
            String inputPassword = scanner.nextLine();

            if (validateLogin(inputUsername, inputPassword)) {
                username = inputUsername;
                login = true;
                System.out.println("\n|\tYou are now logged in\t|\n");
            } else {
                System.out.println("|\nIncorrect username or password\n|");
            }
        } else {
            System.out.println("\nEnter 1 to create an account or 2 to login to an existing account\n");
        }
    }

    private static void viewSchedule() {
        System.out.println("\n\nHere's the schedule for today:\n\n");
        // Placeholder: Replace with code to display train schedule from a CSV file
        System.out.println("Train schedules will be shown here.");
    }

    private static void bookTicket(Scanner scanner) {
        if (login) {
            System.out.println("Booking ticket functionality is under development.");
        } else {
            System.out.println("\nYou first have to login..\n");
        }
    }

    private static void viewTicket() {
        if (book) {
            System.out.println("Viewing ticket functionality is under development.");
        } else {
            System.out.println("\nFirst you have to book a ticket.");
        }
    }

    private static void viewLatency() {
        System.out.println("Latency graph functionality is under development.");
    }

    private static void orderFood(Scanner scanner) {
        if (book) {
            System.out.println("Food ordering functionality is under development.");
        } else {
            System.out.println("\nFirst you have to book a ticket.");
        }
    }

    private static void giveFeedback(Scanner scanner) {
        if (login) {
            System.out.print("\nEnter your Feedback here: \n\n");
            String feedback = scanner.nextLine();
            addFeedbackToCSV(username, feedback);
            System.out.println("Thank you for your valuable feedback.\n\n");
        } else {
            System.out.println("First, you have to login.");
        }
    }

    private static boolean validateLogin(String username, String password) {
        // Placeholder: Replace with CSV file validation logic
        return true;
    }

    private static void addUserToCSV(String username, String email, String password) {
        // Placeholder: Add logic to append user data to a CSV file
    }

    private static void addFeedbackToCSV(String username, String feedback) {
        // Placeholder: Add logic to append feedback data to a CSV file
    }
}
