import java.io.*; // Input and Output in External files.
import java.nio.file.*; // Paths for input and output.
import java.util.*; // Scanner for input.


class Project {

    public static void main(String[] args) {

        TicketManagement tM = new TicketManagement();


        // Load all the files
        Logins[] logins = loadLogins();
        Ferries[] ferries = loadFerries();
        Bookings[] bookings = loadBookings();
        Feedback[] feedback = loadFeedback();


        // Welcoming the User
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("||\t\tFerry Reservation\t\t||");
        System.out.println("==============================================");


        // Main Menu
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
                     5. Cancel your ticket
                     6. Give Feedback
                     7. Admin Login
                     8. Exit
                    """);

            System.out.print("Please select one of these: ");


            // Sorting of functionalities based on input
            input = sc.nextLine();

            switch (input) {
                case "1":
                    tM.loginOrSignup(logins, bookings);
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
                    tM.cancelTicket(bookings);
                    break;
                case "6":
                    tM.giveFeedback();
                    break;
                case "7":
                    tM.adminLogin(logins, bookings, ferries, feedback);
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


    // Method to load all login data.
    static Logins[] loadLogins() {


        // Trying to read Logins file
        try {

            // Store all the data of the file in fileContent.
            String fileContent = new String(
                    Files.readAllBytes(Paths.get("Data/logins.csv"))
            );


            // Removing first line as it is the header.
            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );


            // Array of all the logins. (all data of a user is not differentiated -> username, email, password)
            String[] rawLogins = fileContent.split("\n");


            // Declaring the array of objects of Logins.
            Logins[] logins = new Logins[rawLogins.length];


            // Filling the data in array.
            for (int i = 0; i < rawLogins.length; i++) {

                String[] login = rawLogins[i].split(",");

                // Creating the new object.
                logins[i] = new Logins();
                logins[i].username = login[0];
                logins[i].email = login[1];
                logins[i].password = login[2].trim();

            }

            return logins;
        }

        // If the file is not available or cannot be read.
        catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new Logins[0];
        }
    }


    // Method to load all ferries data
    static Ferries[] loadFerries() {

        // Trying to read Ferries file
        try {

            // Store all the data of the file in fileContent.
            String fileContent = new String(
                    Files.readAllBytes(Paths.get("Data/schedule.csv"))
            );


            // Removing first line as it is the header.
            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );


            // Array of all the Ferries. (all data of a ferry is not differentiated ->
            // ferryNo, ferryName, from, etc)
            String[] rawFerries = fileContent.split("\n");


            // Declaring the array of objects of Ferries.
            Ferries[] ferries = new Ferries[rawFerries.length];


            // Filling the data in array.
            for (int i = 0; i < rawFerries.length; i++) {

                String[] ferry = rawFerries[i].split(",");

                ferries[i] = new Ferries();
                ferries[i].ferryName = ferry[0];
                ferries[i].ferryNo = Integer.parseInt(ferry[1]);
                ferries[i].departureTime = ferry[2];
                ferries[i].from = ferry[3];
                ferries[i].to = ferry[4];
                ferries[i].price = Integer.parseInt(ferry[5]);
                ferries[i].arrivalAtDestination = ferry[6];

            }

            return ferries;

        }

        // If the file is not available or not readable
        catch (Exception e) {
            System.out.println("Error reading the file schedules" + e.getMessage());
            return new Ferries[0];
        }

    }


    // Method to load all bookings data
    static Bookings[] loadBookings() {


        // Trying to read Bookings file
        try {

            // Store all the data of the file in fileContent.
            String fileContent = new String(
                    Files.readAllBytes(Paths.get("Data/booking.csv"))
            );


            // Removing the first line as it is the header
            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );

            // Array of all the bookings. (all data of a booking is not differentiated ->
            // username, ferryNo, etc)
            String[] rawBookings = fileContent.split("\n");


            // Declaring the array of objects of Bookings.
            Bookings[] bookings = new Bookings[rawBookings.length];


            // Filling the data in array.
            for (int i = 0; i < rawBookings.length; i++) {

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

        // If the file is not available or not readable
        catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new Bookings[0];
        }
    }


    // Method to load all the feedbacks
    static Feedback[] loadFeedback() {


        // Trying to read Feedbacks file
        try {

            // Store all the data of the file in fileContent.
            String fileContent = new String(
                    Files.readAllBytes(Paths.get("Data/feedback.csv"))
            );


            // Removing the first line as it is the header
            fileContent = fileContent.substring(
                    fileContent.indexOf("\n") + 1
            );

            // Array of all the feedbacks. (all data of a feedback is not differentiated ->
            String[] rawFeedback = fileContent.split("\n");


            // Declaring the array of objects of Feedbacks.
            Feedback[] feedbacks = new Feedback[rawFeedback.length];


            // Filling the data in array.
            for (int i = 0; i < rawFeedback.length; i++) {

                String[] feedback = rawFeedback[i].split(",");

                feedbacks[i] = new Feedback();
                feedbacks[i].username = feedback[0];
                feedbacks[i].feedback = feedback[1];
                feedbacks[i].rating = Integer.parseInt(feedback[2]);

            }

            return feedbacks;

        }

        // If the file is not available or not readable
        catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return new Feedback[0];
        }

    }

}


// Class to manage the Ticket Reservation System
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



    // Method to sort user into Login or Signup
    void loginOrSignup(
            Logins[] logins,
            Bookings[] bookings
    ) {

        Scanner sc = new Scanner(System.in);

        // Menu
        System.out.println("\n1) Login");
        System.out.println("2) Sign up");
        System.out.print("\nPlease select one of these: ");

        String choice = sc.nextLine();

        // Sorting of functionalities based on input
        if (choice.equals("1"))
            login(logins, bookings);
        else if (choice.equals("2"))
            signUp();
        else {
            System.out.println("Enter value from the given list..");

            System.out.println("Press Enter to continue! ");
            sc.nextLine();
        }

    }


    // Method to login
    void login(
            Logins[] logins,
            Bookings[] bookings
    ) {

        Scanner sc = new Scanner(System.in);

        // Getting the Credentials
        System.out.print("Enter your Username: ");
        String inputUsername = sc.next();
        System.out.print("Enter your Password: ");
        String inputPassword = sc.next();
        sc.nextLine();


        // Traversing through all the logins to check if the credentials are correct
        for (Logins l : logins) {

            // If the user is found
            if (inputUsername.equals(l.username) && inputPassword.equals(l.password)) {
                login = true;
                username = l.username;

                // Check if the user has already booked any tickets or not
                book = checkBookingStatus(username, bookings);


                System.out.println("\n\nLogin Successful.");
                System.out.println("\nPress Enter to continue! ");
                sc.nextLine();

                return; // End the function if the user is found
            }

        }

        // If the user is not found
        System.out.println("\nUsername or password invalid.");
        System.out.println("\nPress Enter to continue.");
        sc.nextLine();

    }


    // Method to signup
    void signUp() {

        Scanner sc = new Scanner(System.in);


        // Taking the credentials
        System.out.println("Please fill these details");
        System.out.print("Username: ");
        String newUsername = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Create a password: ");
        String password = sc.nextLine();


        // If all the credentials are not empty -> then only continue
        if (!(newUsername.isEmpty() || email.isEmpty() || password.isEmpty())) {


            // Trying to write the new user in the logins file
            try (

                    // fw is used as a dependency to write in the file.
                    FileWriter fw = new FileWriter("Data/logins.csv", true);

                    // bw is used to write in the file in batches (so that it does the job faster).
                    BufferedWriter bw = new BufferedWriter(fw);

                    // out is used to write in the file.
                    PrintWriter out = new PrintWriter(bw)

            ) {

                // Actually writing at the end of the file.
                out.print("\n" + newUsername + "," + email + "," + password);


                // Updating the login status
                login = true;
                username = newUsername;


                System.out.println("\nSign up and Login success\n");
                System.out.println("Press Enter to continue!");
                sc.nextLine();

            }

            // If the file is not available or cannot be written
            catch (Exception e) {
                System.out.println("Error writing to logins.csv: " + e.getMessage());
            }
        }

        else {
            System.out.println("\nThe details must not be empty..\n");
        }
    }


    // Method to search ferries from (source & destination)
    void searchFerries(
            Ferries[] ferries
    ) {


        // Printing the list of ferry terminals to search source & destination.
        System.out.println("List of Ferry terminals");

        for(int i = 0; i < ferryTerminals.length; i++){
            System.out.println(i+1 + ". " + ferryTerminals[i]);
        }


        // Taking the source & destination
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter your source number from above: ");
        String source = sc.nextLine();
        System.out.print("Enter your destination number from above: ");
        String destination = sc.nextLine();



        // Trying to convert the source & destination to integer
        try {

            // Converting the input value to integer
            int sourceInt = Integer.parseInt(source);
            int destinationInt = Integer.parseInt(destination);


            // Checking if the source & destination are valid or not
            boolean isValidSAndD = sourceInt < ferryTerminals.length &&
                    sourceInt >= 0 &&
                    destinationInt < ferryTerminals.length &&
                    destinationInt >= 0;


            // If it is valid
            if (isValidSAndD) {

                // Printing the list of ferries where the source & destination are same
                for (Ferries ferry : ferries) {

                    if (ferry.from.equals(ferryTerminals[sourceInt - 1]) && ferry.to.equals(ferryTerminals[destinationInt - 1])) {

                        System.out.println();
                        System.out.println(ferry.ferryNo + " " + ferry.ferryName);
                    }
                }


                System.out.println("\n\nPress Enter to continue!");
                sc.nextLine();


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


    // Method to book tickets
    void bookTicket(
            Ferries[] ferries
    ) {

        Scanner sc = new Scanner(System.in);


        // User can only book a ticket if s/he is logged in
        if (login) {


            // Taking the booking details
            System.out.println("Enter Ferry number: ");
            int ferryNo = sc.nextInt();
            System.out.println("Enter number of tickets to book: ");
            int tickets = sc.nextInt();


            // Getting (valid) date
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


            // whether user wants parking facility or not.
            System.out.println("Do you want parking (extra 1000/-) y/n: ");
            boolean parking = "y".equals(sc.nextLine());


            // Predefining some variables for ticket booking
            String ferryName = "";
            String departureTime = "";
            String from = "";
            String to = "";
            int price = 0;


            // searching for the ferry from ferryNo.
            for(Ferries ferry : ferries){

                // Booking the ticket if the ferryNo is found.
                if (ferry.ferryNo == ferryNo){
                    ferryName = ferry.ferryName;
                    departureTime = ferry.departureTime;
                    from = ferry.from;
                    to = ferry.to;
                    price = ferry.price * tickets + (parking?1000:0);
                    break;
                }

            }


            // If the user has entered invalid ferryNo. -> cancel the booking process.
            if(ferryName.isEmpty()){

                System.out.println("The ferry number is invalid");

                System.out.println("\n\nPress enter to continue..");
                sc.nextLine();
                return;

            }


            String date = (monthDate + "-" + month + "-" + year);


            // Printing the details entered by user to confirm the booking
            printTicket(username, ferryNo, ferryName, date, tickets, from, to, departureTime, parking, price);


            // Asking user to confirm or cancel the booking.
            System.out.println("Enter y to confirm this ticket.");
            boolean confirm = sc.nextLine().equals("y");


            // If the user confirms the booking.
            if(confirm) {

                // Trying to write the booking details in the booking file.
                try (
                        FileWriter fw = new FileWriter("Data/booking.csv", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)
                ) {


                    // Actually writing at the end of the file.
                    out.print("\n" + username + "," + ferryNo + "," + ferryName + "," + date + "," + tickets + "," +
                            from + "," + to + "," + departureTime + "," + parking);


                    // Changing the booking status to true.
                    book = true;

                    System.out.println("\n\nTicket booked successfully.");
                    System.out.println("\n Have a safe and Happy Journey.");
                    System.out.println("Press Enter to continue!");
                    sc.nextLine();

                }

                // If the file is not available or cannot be written
                catch (Exception e) {
                    System.out.println("Error writing to booking.csv: " + e.getMessage());
                }
            }
            else {
                System.out.println("\n\nYou have cancelled this booking.");
                System.out.println("You can give feedback on this platform at menu option 7.");
            }

        }

        // If the user has not logged in
        else {
            System.out.println("\nYou first have to login..\n");
            System.out.println("Press Enter to continue!");
            sc.nextLine();
        }
    }


    // Method to view ticket
    void viewTicket(
            Bookings[] bookings
    ) {

        Scanner sc = new Scanner(System.in);


        // If the user has booked any tickets
        if (book) {

            // Searching for the user's ticket
            for (Bookings booking : bookings) {


                // When found -> print
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

        }
        else {
            System.out.println("\nYou have no tickets booked.");

        }
        System.out.println("\nPress Enter to continue.");
        sc.nextLine();
    }


    // Method to cancel ticket
    void cancelTicket(
            Bookings[] bookings
    ){

        Scanner sc = new Scanner(System.in);


        // If the user has booked the ticket -> then only he can cancel it (huhh obvious :/ )
        if(book) {

            // Creating a new array with 1 length less
            Bookings[] newBookings = new Bookings[bookings.length-1];
            int ind = -1;


            // Getting the index of the user's ticket
            for (int i = 0; i < bookings.length; i++){
                if(bookings[i].username.equals(username)){
                    ind = i;
                    break;
                }
            }


            // Copying all the data except the user's ticket
            for(int i = 0; i < bookings.length; i++){

                if(i < ind){
                    newBookings[i] = bookings[i];
                }
                else if(i > ind){
                    newBookings[i-1] = bookings[i];
                }

            }


            // Confirming the cancellation
            System.out.println("Do you really want to cancel your ticket? y/n: ");
            if("y".equals(sc.nextLine())) {


                // Trying to write the cancellation details in the booking file.
                try (
                        FileWriter fw = new FileWriter("Data/booking.csv"); // overwriting
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)
                ) {

                    // Actually writing at the end of the file.
                    out.print("Username,Ferry No.,Ferry name,Date,No of passenger,From,To,Departure Time,Parking,Price");


                    // Writing the new booking details (overwriting the old file)
                    for (Bookings b : newBookings) {

                        out.print("\n" + b.username + "," + b.ferryNo + "," + b.ferryName + "," + b.date + "," + b.noOfPassengers + "," +
                                b.from + "," + b.to + "," + b.departureTime + "," + b.parking + "," + b.price);
                    }

                    // Changing the booking status to false.
                    book = false;

                    System.out.println("\nTicket Cancellation Successful");

                }

                // If the file is not available or cannot be written
                catch (Exception e) {
                    System.out.println("Error writing to logins.csv: " + e.getMessage());
                }
            }

            System.out.println("\n\nPress Enter to continue.");
            sc.nextLine();

        }
        else {
                System.out.println("\nYou have no tickets booked.");

                System.out.println("\nPress Enter to continue.");
                sc.nextLine();
            }

    }


    // Method to give feedback
    void giveFeedback() {

        Scanner sc = new Scanner(System.in);

        // User can only give feedback if s/he is logged in
        if (login) {

            System.out.print("\nEnter your Feedback here: \n\n");
            String feedback = sc.nextLine();

            int rating = -1;

            System.out.println();

            // Getting valid rating from 0 to 10.
            while (rating >10 || rating < 0) {
                System.out.println("Give rating from 0 to 10");
                rating = sc.nextInt();
                sc.nextLine();

                if(rating >10 || rating < 0)
                    System.out.println("Invalid rating. enter again..");
            }


            // Trying to write the feedback details in the feedback file.
            try (
                    FileWriter fw = new FileWriter("Data/feedback.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)
            ) {

                // Actually writing at the end of the file.
                out.print("\n" + username + "," + feedback + "," + rating);


                System.out.println("Feedback submitted successfully.");
                System.out.println("Thank you for your valuable feedback.");

                System.out.println("\n\nPress Enter to continue.");
                sc.nextLine();

            }

            // If the file is not available or cannot be written
            catch (Exception e) {
                System.out.println("Error writing to feedback.csv: " + e.getMessage());
            }

        }
        else {
            System.out.println("First, you have to login.");

            System.out.println("\n\nPress Enter to continue.");
            sc.nextLine();


        }
    }


    // Method for admin login
    void adminLogin(
            Logins[] logins,
            Bookings[] bookings,
            Ferries[] ferries,
            Feedback[] feedback
    ) {

        Scanner sc = new Scanner(System.in);

        // Admin Password
        String password = "Admin@1900!";

        // Taking the admin password
        System.out.println("Enter Password: ");
        String inputPass = sc.nextLine();


        // Validating the password
        if (inputPass.equals(password)) {

            String inpt = "";


            // Menu
            while (!inpt.equals("5")) {

                System.out.println("""
                        
                         1. View Login Details
                         2. View Schedule
                         3. View Bookings
                         4. View Feedback
                         5. Back
                        """);

                System.out.print("Please select oe of these: ");

                inpt = sc.nextLine();

                // Sorting the functionalities of admin
                switch (inpt) {
                    case "1":
                        showLogins(logins);
                        break;
                    case "2":
                        showSchedule(ferries);
                        break;
                    case "3":
                        showBookings(bookings);
                        break;
                    case "4":
                        showFeedback(feedback);
                        break;
                    case "5":
                        System.out.println("\n\nThank you!");
                        break;

                    default:
                        System.out.println("\nSelect one from the serial numbers.");
                        System.out.println("Press Enter to continue! ");
                        sc.nextLine();
                        break;
                }

            }
        } else {
            System.out.println("\nPassword invalid..");
            System.out.println("\nPress Enter to continue.");
            sc.nextLine();
        }

    }


    // Method to print ticket details
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


    // Method to check if the user has booked any tickets or not
    boolean checkBookingStatus(
            String username,
            Bookings[] bookings
    ){

        for (Bookings booking : bookings) {

            if(booking.username.equals(username)){
                return true;
            }

        }
        return false;

    }


    // Displaying all login data
    void showLogins(Logins[] logins){

        Scanner sc = new Scanner(System.in);

        for(Logins login : logins){

            System.out.println();
            System.out.println("Username: " + login.username);
            System.out.println("Email: " + login.email);
            System.out.println("Password: " + login.password);
            System.out.println();

        }

        System.out.println("Press Enter to continue: ");
        sc.nextLine();

    }


    // Displaying all booking data
    void showBookings(Bookings[] bookings){

        Scanner sc = new Scanner(System.in);

        for(Bookings booking : bookings){

            System.out.println();
            System.out.println("Username: " + booking.username);
            System.out.println("Ferry No: " + booking.ferryNo);
            System.out.println("Ferry Name: " + booking.ferryName);
            System.out.println("Journey Date: " + booking.date);
            System.out.println("No of Tickets: " + booking.noOfPassengers);
            System.out.println("Source Terminal: " + booking.from);
            System.out.println("Destination: " + booking.to);
            System.out.println("Departure Time: " + booking.departureTime);
            System.out.println("Parking opted: " + booking.parking);
            System.out.println("Price: " + booking.price);
            System.out.println();

        }

        System.out.println("Press Enter to continue: ");
        sc.nextLine();

    }


    // Displaying all schedules
    void showSchedule(Ferries[] ferries){

        Scanner sc = new Scanner(System.in);

        for(Ferries ferry : ferries){

            System.out.println();
            System.out.println("Ferry Name: " + ferry.ferryName);
            System.out.println("Ferry No: " + ferry.ferryNo);
            System.out.println("Departure Time: " + ferry.departureTime);
            System.out.println("From: " + ferry.from);
            System.out.println("To: " + ferry.to);
            System.out.println("Price: " + ferry.price);
            System.out.println("Arrival at Destination: " + ferry.arrivalAtDestination);
            System.out.println();

        }

        System.out.println("Press Enter to continue: ");
        sc.nextLine();

    }


    // Displaying all feedbacks
    void showFeedback(Feedback[] feedbacks){


        Scanner sc = new Scanner(System.in);

        float totalRating = 0;

        for(Feedback feedback : feedbacks){

            System.out.println();
            System.out.println("Username: " + feedback.username);
            System.out.println("Feedback: " + feedback.feedback);
            System.out.println("Rating: " + feedback.rating);
            System.out.println();

            totalRating += feedback.rating;

        }


        // Printing the average rating
        System.out.println();
        System.out.println("Average Rating is " + totalRating/feedbacks.length);

        System.out.println("\nPress Enter to continue: ");
        sc.nextLine();

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

class Feedback{
    String username;
    String feedback;
    int rating;
}