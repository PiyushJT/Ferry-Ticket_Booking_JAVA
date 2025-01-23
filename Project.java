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
        System.out.println("||\t\tFerry Reservation\t\t||");
        System.out.println("==============================================");

        String input = "0";

        while (!input.equals("8")) {
            System.out.println("\n\n\n");
            System.out.println("==============================================");
            System.out.println("\t\t\tMenus");
            System.out.println("==============================================");

            System.out.println("""

                     1. Login / Sign up
                     2. Search Ferries
                     3. Book a ticket
                     4. View your ticket
                     5. Modify your ticket
                     6. Cancel your ticket
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
                    tM.searchFerries();
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

    String[] ferryTerminals = {
            "Terminal 1",
            "Terminal 2",
            "Terminal 3",
            "Terminal 4",
            "Terminal 5",
            "Terminal 6",
            "Terminal 7",
            "Terminal 8",
            "Terminal 9",
            "Terminal 10"
    };

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
        else {
            System.out.println("Enter value from the given list..");

            System.out.println("Press Enter to continue! ");
            sc.nextLine();
        }

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

            String[] rawLogins = fileContent.split("\n");

            Logins[] logins = new Logins[rawLogins.length];

            for(int i = 0; i < rawLogins.length; i++){

                String[] login = rawLogins[i].split(",");

                logins[i] = new Logins();
                logins[i].username = login[0];
                logins[i].email = login[1];
                logins[i].password = login[2].trim();

            }

            for(Logins l : logins){

                if(inputUsername.equals(l.username) && inputPassword.equals(l.password)){
                    login = true;
                    username = l.username;
                    System.out.println("\n\nLogin Successful.");
                    System.out.println("\nPress Enter to continue! ");
                    sc.nextLine();
                    break;
                }

            }


        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }


    void signUp() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please fill these details");
        System.out.print("Username: ");
        String newUsername = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Create a password: ");
        String password = sc.nextLine();

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
                System.out.println("Press Enter to continue!");
                sc.nextLine();

            } catch (IOException e) {
                System.out.println("Error writing to logins.csv: " + e.getMessage());
            }
        } else {
            System.out.println("\nThe details must not be empty..\n");
        }
    }


    void searchFerries() {

        System.out.println("List of Ferry terminals");

        for(int i = 0; i < ferryTerminals.length; i++){
            System.out.println(i+1 + ". " + ferryTerminals[i]);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter your source number from above: ");
        String source = sc.nextLine();
        System.out.print("Enter your destination number from above: ");
        String destination = sc.nextLine();


        try {

            int sourceInt = Integer.parseInt(source);
            int destinationInt = Integer.parseInt(destination);

            boolean isValidSAndD = sourceInt < ferryTerminals.length &&
                    sourceInt >= 0 &&
                    destinationInt < ferryTerminals.length &&
                    destinationInt >= 0;

            if (isValidSAndD) {

                String loginsDataPath = "Data/schedule.csv";

                try {

                    String fileContent = new String(Files.readAllBytes(Paths.get(loginsDataPath)));

                    fileContent = fileContent.substring(
                            fileContent.indexOf("\n") + 1
                    );

                    String[] rawFerries = fileContent.split("\n");

                    Ferries[] ferries = new Ferries[rawFerries.length];

                    for(int i = 0; i < rawFerries.length; i++){

                        String[] ferry = rawFerries[i].split(",");

                        ferries[i] = new Ferries();
                        ferries[i].ferryName = ferry[0];
                        ferries[i].ferryNo = ferry[1];
                        ferries[i].departureTime = ferry[2];
                        ferries[i].from = ferry[3];
                        ferries[i].to = ferry[4];
                        ferries[i].price = ferry[5];
                        ferries[i].arrivalAtDestination = ferry[6];
                        ferries[i].seatAvailable = ferry[7];

                    }

                    for(int i = 0; i < ferryTerminals.length; i++){

                        if(ferries[i].from.equals(ferryTerminals[sourceInt-1]) && ferries[i].to.equals(ferryTerminals[destinationInt-1])){

                            System.out.println();
                            System.out.println(i+1 + ". " + ferries[i].ferryName);

                        }

                    }

                    System.out.println("\n\nPress Enter to continue!");
                    sc.nextLine();



                } catch (IOException e) {
                    System.err.println("Error reading the file: " + e.getMessage());
                }

            }
            else {
                System.out.println("Enter only valid value in given range..");
            }
        }
        catch (Exception e){

            System.out.println("Entered values are not valid. \nEnter only Integer value in given range..");
            System.out.println("Press Enter to continue!");
            sc.nextLine();

        }


    }


    void bookTicket() {
        Scanner sc = new Scanner(System.in);
        if (login) {

            System.out.println("Enter Ferry number: ");
            int ferryNo = sc.nextInt();
            System.out.println("Enter number of tickets to book: ");
            int tickets = sc.nextInt();

            System.out.println("Do you want parking y/n: ");
            Boolean parking = "y".equals(sc.next());



        } else {
            System.out.println("\nYou first have to login..\n");
            System.out.println("Press Enter to continue!");
            sc.nextLine();
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

class Logins{
    String username;
    String email;
    String password;
}

class Ferries{
    String ferryName;
    String ferryNo;
    String departureTime;
    String from;
    String to;
    String price;
    String arrivalAtDestination;
    String seatAvailable;
}