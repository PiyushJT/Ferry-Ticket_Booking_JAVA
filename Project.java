import java.util.Scanner;

class Project {

    static Scanner sc = new Scanner(System.in);

    static String input;

    public static void main(String[] args) {

        menu();

    }

    static void menu() {

        // Printing the Menus
        System.out.println("""
                ==================================================
                \t\t\t\t\tMenus
                ==================================================
                """);

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

        input = sc.next();


    }

    static void login_signup(){


        String inpt2 = sc.next();

        while (inpt2.equals("1") || inpt2.equals("2")) {
            System.out.println("\n\n1) Sign up\n2) Login\n\n");
            inpt2 = sc.nextLine();

            if (inpt2.equals("1")) {
                // Sign up
                System.out.println("Please fill these details");
                System.out.print("Username: \n");
                String username = sc.nextLine();

                System.out.print("Email: \n");
                String email = sc.nextLine();

                System.out.print("Create a password: \n");
                String password = sc.nextLine();


                System.out.println("\n\n Press Enter to continue!");
                sc.nextLine();

            } else if (inpt2.equals("2")) {
                // Login
                System.out.print("Enter your Username:\n");
                String username = sc.nextLine();

            } else {
                System.out.println("\nEnter 1 if you want to create an account\nOr enter 2 if want to login to an existing account\n");
                System.out.println("Press Enter to continue\t");
                sc.nextLine();
            }
        }


    }

}