import static java.lang.System.in;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Project {


    public static void main(String[] args) {

        TicketManagement tM = new TicketManagement();

        Scanner sc = new Scanner(in);
        System.out.println("==============================================");
        System.out.println("||\t\tWelcome to FlightLink\t\t||");
        System.out.println("==============================================");

        String input = "0";

        while (!input.equals("8")) {
            System.out.println("\n\n\n");
            System.out.println("==============================================");
            System.out.println("\t\t\tMenus");
            System.out.println("==============================================");

            System.out.println("""

                     1. Login / Sign up
                     2. View today's Schedule
                     3. Book a ticket
                     4. View your ticket
                     5. View latency of trains
                     6. Order food on your seat
                     7. Give Feedback
                     8. Exit
                    """);

            System.out.print("Please select one of these: ");

            input = sc.nextLine();

            switch (input) {
                case "1":
                    tM.loginOrSignup();
                    break;
                case "2":
                    tM.viewSchedule();
                    break;
                case "3":
                    tM.bookTicket();
                    break;
                case "4":
                    tM.viewTicket();
                    break;
                case "5":
                    tM.viewLatency();
                    break;
                case "6":
                    tM.orderFood();
                    break;
                case "7":
                    tM.giveFeedback();
                    break;
                case "8":
                    System.out.println("\n\nThank you!");
                    break;
                default:
                    System.out.println("\nSelect one from the serial numbers.");
                    System.out.println("Press Enter to continue! ");
                    sc.nextLine();
                    break;
            }
        }
    }
}


class TicketManagement{

    boolean login;
    boolean book;
    String username;


    void loginOrSignup() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n1) Login");
        System.out.println("2) Sign up");
        System.out.print("\nPlease select one of these: ");

        String choice = sc.nextLine();

        if (choice.equals("1"))
            login();
        else if (choice.equals("2"))
            signUp();
        else
            System.out.println("Enter value from the given list..");


        System.out.println("Press Enter to continue! ");
        sc.nextLine();

    }


    void login() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your Username: ");
        String inputUsername = sc.next();
        System.out.print("Enter your Password: ");
        String inputPassword = sc.next();
        sc.nextLine();


        String loginsDataPath = "Data/logins.csv";

        try {

            String fileContent = new String(Files.readAllBytes(Paths.get(loginsDataPath)));

            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );

            int linesCount = countChar(fileContent, '\n') + 1;

            String[][] logins = new String[linesCount][3];

            String[] lines = fileContent.split("\n");

            for (int i = 0; i < lines.length; i++) {

                logins[i] = lines[i].split(",");

            }


            for (String[] loginData : logins) {

                if (inputUsername.equals(loginData[0]) && inputPassword.equals(loginData[2])) {
                    login = true;
                    break;
                }

            }
            if (login) {
                username = inputUsername;
                System.out.println("\nYou are now logged in");
            } else {

                System.out.println("\nIncorrect username or password");

            }


        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }


    void signUp() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please fill these details");
        System.out.print("Username: ");
        String newUsername = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Create a password: ");
        String password = sc.next();

        if (!(newUsername.isEmpty() || email.isEmpty() || password.isEmpty())) {

            try (

                    FileWriter fw = new FileWriter("Data/logins.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)

            ) {
                out.print("\n" + newUsername + "," + email + "," + password);

                username = newUsername;
                login = true;

                System.out.println("\nSign up and Login success\n");

            } catch (IOException e) {
                System.out.println("Error writing to logins.csv: " + e.getMessage());
            }
        } else {
            System.out.println("\nThe details must not be empty..\n");
        }
    }


    void viewSchedule() {
        System.out.println("\n\nHere's the schedule for today:\n\n");

        String loginsDataPath = "Data/schedule.csv";

        try {

            String schedule = new String(Files.readAllBytes(Paths.get(loginsDataPath)));

            int linesCount = countChar(schedule, '\n') + 1;


            String[][] flights = new String[linesCount][12];

            String[] lines = schedule.split("\n");

            for (int i = 0; i < lines.length; i++) {

                flights[i] = lines[i].split(",");

            }


            // printing
            int[] columnWidths = new int[flights[0].length];


            for (String[] row : flights) {
                for (int i = 0; i < row.length; i++) {
                    columnWidths[i] = Math.max(columnWidths[i], row[i].trim().length());
                }
            }


            // Print the formatted table
            for (int i = 0; i < flights.length; i++) {

                for (int j = 0; j < flights[i].length; j++) {

                    if (!(j == 2 || j == 3))
                        System.out.printf("%-" + columnWidths[j] + "s ", flights[i][j].trim());

                }
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


    void bookTicket() {
        if (login) {
            System.out.println("Booking ticket functionality is under development.");
        } else {
            System.out.println("\nYou first have to login..\n");
        }
    }


    void viewTicket() {
        if (book) {
            System.out.println("Viewing ticket functionality is under development.");
        } else {
            System.out.println("\nFirst you have to book a ticket.");
        }
    }


    void viewLatency() {

        System.out.println("Latency graph functionality is under development.");

    }


    void orderFood() {
        if (book) {
            System.out.println("Food ordering functionality is under development.");
        } else {
            System.out.println("\nFirst you have to book a ticket.");
        }
    }


    void giveFeedback() {
        if (login) {

            Scanner sc = new Scanner(System.in);

            System.out.print("\nEnter your Feedback here: \n\n");
            String feedback = sc.nextLine();


            try (FileWriter fw = new FileWriter("Data/feedback.csv", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(username + "=" + feedback);
                System.out.println("Feedback submitted successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to feedback.csv: " + e.getMessage());
            }

            System.out.println("Thank you for your valuable feedback.\n\n");
        } else {
            System.out.println("First, you have to login.");
        }
    }




    int countChar(String str, char ch) {

        int count = 0;
        for (int i = 0; i < str.length(); i++) {

            if (ch == str.charAt(i))
                count++;

        }

        return count;

    }


}