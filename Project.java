import static java.lang.System.in;

import java.awt.print.Book;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.crypto.spec.PSource;

class Project {


    public static void main(String[] args) {

        TicketManagement tM = new TicketManagement();

        Logins[] logins = loadLogins();
        Ferries[] ferries = loadFerries();
        Bookings[] bookings = loadBookings();


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
                    tM.loginOrSignup(logins);
                    break;
                case "2":
                    tM.searchFerries(ferries);
                    break;
                case "3":
                    tM.bookTicket(ferries);
                    break;
                case "4":
                    tM.viewTicket(bookings);
                    break;
                case "5":
                    break;
                case "6":
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
    static Logins[] loadLogins() {

        try {

            String fileContent = new String(Files.readAllBytes(Paths.get("Data/logins.csv")));

            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );

            String[] rawLogins = fileContent.split("\n");

            Logins[] logins = new Logins[rawLogins.length-1];

            for (int i = 0; i < rawLogins.length-1; i++) {

                String[] login = rawLogins[i].split(",");

                logins[i] = new Logins();
                logins[i].username = login[0];
                logins[i].email = login[1];
                logins[i].password = login[2].trim();

            }

            return logins;

        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new Logins[0];
        }
    }

    static Ferries[] loadFerries() {


        try {

            String fileContent = new String(Files.readAllBytes(Paths.get("Data/schedule.csv")));

            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );

            String[] rawFerries = fileContent.split("\n");

            Ferries[] ferries = new Ferries[rawFerries.length-1];

            for (int i = 0; i < rawFerries.length-1; i++) {

                String[] ferry = rawFerries[i].split(",");

                ferries[i] = new Ferries();
                ferries[i].ferryName = ferry[0];
                ferries[i].ferryNo = Integer.parseInt(ferry[1]);
                ferries[i].departureTime = ferry[2];
                ferries[i].from = ferry[3];
                ferries[i].to = ferry[4];
                ferries[i].price = Integer.parseInt(ferry[5]);
                ferries[i].arrivalAtDestination = ferry[6];
                ferries[i].seatAvailable = ferry[7];

            }

            return ferries;

        }
        catch (Exception e) {
            System.out.println("Error reading the file schedules" + e.getMessage());

            return new Ferries[0];
        }

    }

    static Bookings[] loadBookings() {

        try {

            String fileContent = new String(Files.readAllBytes(Paths.get("Data/booking.csv")));

            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );

            String[] rawBookings = fileContent.split("\n");

            Bookings[] bookings = new Bookings[rawBookings.length-1];

            for (int i = 0; i < rawBookings.length-1; i++) {

                String[] booking = rawBookings[i].split(",");

                bookings[i] = new Bookings();
                bookings[i].username = booking[0];
                bookings[i].ferryNo = Integer.parseInt(booking[1]);
                bookings[i].ferryName = booking[2];
                bookings[i].date = booking[3];
                bookings[i].noOfPassengers = Integer.parseInt(booking[4]);
                bookings[i].from = booking[5];
                bookings[i].to = booking[6];
                bookings[i].departureTime = booking[7];
                bookings[i].parking = Boolean.parseBoolean(booking[8]);
                bookings[i].price = Integer.parseInt(booking[9]);

            }

            return bookings;

        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new Bookings[0];
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

    boolean login = true;
    boolean book = true;
    String username = "piyush";


    void loginOrSignup(Logins[] logins) {

        Scanner sc = new Scanner(in);

        System.out.println("\n1) Login");
        System.out.println("2) Sign up");
        System.out.print("\nPlease select one of these: ");

        String choice = sc.nextLine();

        if (choice.equals("1"))
            login(logins);
        else if (choice.equals("2"))
            signUp();
        else {
            System.out.println("Enter value from the given list..");

            System.out.println("Press Enter to continue! ");
            sc.nextLine();
        }

    }


    void login(Logins[] logins) {

        Scanner sc = new Scanner(in);

        System.out.print("Enter your Username: ");
        String inputUsername = sc.next();
        System.out.print("Enter your Password: ");
        String inputPassword = sc.next();
        sc.nextLine();

        for (Logins l : logins) {

            if (inputUsername.equals(l.username) && inputPassword.equals(l.password)) {
                login = true;
                username = l.username;
                System.out.println("\n\nLogin Successful.");
                System.out.println("\nPress Enter to continue! ");
                sc.nextLine();
                break;
            }

        }



    }


    void signUp() {

        Scanner sc = new Scanner(in);

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


    void searchFerries(Ferries[] ferries) {

        System.out.println("List of Ferry terminals");

        for(int i = 0; i < ferryTerminals.length; i++){
            System.out.println(i+1 + ". " + ferryTerminals[i]);
        }

        Scanner sc = new Scanner(in);
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


                for (int i = 0; i < ferryTerminals.length; i++) {

                    if (ferries[i].from.equals(ferryTerminals[sourceInt - 1]) && ferries[i].to.equals(ferryTerminals[destinationInt - 1])) {

                        System.out.println();
                        System.out.println(ferries[i].ferryNo + " " + ferries[i].ferryName);

                    }

                }

                System.out.println("\n\nPress Enter to continue!");
                sc.nextLine();


            } else {
                System.out.println("Enter only valid value in given range..");
            }
        } catch (Exception e){

            System.out.println("Entered values are not valid. \nEnter only Integer value in given range..");
            System.out.println("Press Enter to continue!");
            sc.nextLine();

        }


    }


    void bookTicket(Ferries[] ferries) {
        Scanner sc = new Scanner(in);
        if (login) {

            System.out.println("Enter Ferry number: ");
            int ferryNo = sc.nextInt();
            System.out.println("Enter number of tickets to book: ");
            int tickets = sc.nextInt();

            int year = 0;
            int month = 0;
            int monthDate = 0;
            boolean notValid = true;

            while (notValid) {

                System.out.println("Enter the date of journey");

                System.out.println("Enter year: ");
                year = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Month: ");
                month = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Date: ");
                monthDate = sc.nextInt();
                sc.nextLine();


                notValid = month > 0 && month <= 12;


                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (monthDate <= 31)
                        notValid = false;
                }
                else if(month == 2){
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                        if(monthDate <= 29)
                            notValid = false;
                    }
                    else {
                        if (monthDate <= 28)
                            notValid = false;
                    }
                }
                else {
                    if (monthDate <= 30)
                        notValid = false;
                }

                if (notValid) {

                    System.out.println("\nThe Date you entered is invalid.");

                    System.out.println("\nEnter date again!");
                    System.out.println("\nPress Enter");
                    sc.nextLine();

                }

            }

            System.out.println("Do you want parking (extra 1000/-) y/n: ");
            boolean parking = "y".equals(sc.nextLine());


            String ferryName = "";
            String departureTime = "";
            String from = "";
            String to = "";
            int price = 0;

            for(Ferries ferry : ferries){

                if (ferry.ferryNo == ferryNo){
                    ferryName = ferry.ferryName;
                    departureTime = ferry.departureTime;
                    from = ferry.from;
                    to = ferry.to;
                    price = ferry.price * tickets + (parking?1000:0);
                    break;
                }

            }
            String date = (monthDate + "-" + month + "-" + year);

            printTicket(username, ferryNo, ferryName, date, tickets, from, to, departureTime, parking, price);

            System.out.println("Enter y to confirm this ticket.");
            boolean confirm = sc.nextLine().equals("y");

            if(confirm) {
                try (
                        FileWriter fw = new FileWriter("Data/booking.csv", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)
                ) {

                    out.print("\n" + username + "," + ferryNo + "," + ferryName + "," + date + "," + tickets + "," +
                            from + "," + to + "," + departureTime + "," + parking);

                    book = true;

                    System.out.println("\n\nTicket booked successfully.");
                    System.out.println("\n Have a safe and Happy Journey.");
                    System.out.println("Press Enter to continue!");
                    sc.nextLine();

                } catch (IOException e) {
                    System.out.println("Error writing to booking.csv: " + e.getMessage());
                }
            }
            else {
                System.out.println("\n\nYou have cancelled this booking.");
                System.out.println("You can give feedback on this platform at menu option 7.");
            }


        } else {
            System.out.println("\nYou first have to login..\n");
            System.out.println("Press Enter to continue!");
            sc.nextLine();
        }
    }


    void viewTicket(Bookings[] bookings) {

        Scanner sc = new Scanner(in);

        if (book) {
            for (Bookings booking : bookings) {

                if(booking.username.equals(username)){

                    printTicket(
                            booking.username,
                            booking.ferryNo,
                            booking.ferryName,
                            booking.date,
                            booking.noOfPassengers,
                            booking.from,
                            booking.to,
                            booking.departureTime,
                            booking.parking,
                            booking.price
                    );
                    break;
                }
            }

            System.out.println("\nPress Enter to continue.");
            sc.nextLine();

        }
        else {
            System.out.println("\nYou have no tickets booked.");

            System.out.println("\nPress Enter to continue.");
            sc.nextLine();
        }
    }


    void giveFeedback() {
        if (login) {

            Scanner sc = new Scanner(in);

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



    void printTicket(
            String username,
            int FerryNo,
            String ferryName,
            String date,
            int tickets,
            String from,
            String to,
            String departureTime,
            boolean Parking,
            int price
    ){


        System.out.println("\nThis is your booking:");
        System.out.println();

        System.out.println("Username: " + username);
        System.out.println("Ferry No: " + FerryNo);
        System.out.println("Ferry Name: " + ferryName);
        System.out.println("Journey Date: " + date);
        System.out.println("No of Tickets: " + tickets);
        System.out.println("Source Terminal: " + from);
        System.out.println("Destination: " + to);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Parking opted: " + Parking);

        System.out.println();
        System.out.println("The ticket fare is " + price);

        System.out.println();
        System.out.println();


    }

    boolean checkBookingStatus(String username){
        return false;
    }


}

class Logins{
    String username;
    String email;
    String password;
}

class Ferries{
    String ferryName;
    int ferryNo;
    String departureTime;
    String from;
    String to;
    int price;
    String arrivalAtDestination;
    String seatAvailable;
}

class Bookings{
    String username;
    String ferryName;
    int ferryNo;
    String date;
    int noOfPassengers;
    String from;
    String to;
    String departureTime;
    boolean parking;
    int price;
}