
package za.ac.rosebank.registrationlogin;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("REGISTRATION AND LOGIN");
            System.out.println();

          
            String firstName;

            do {
                System.out.print("Enter your first name: ");
                firstName = scanner.nextLine().trim();

                if (firstName.isEmpty()) {
                    System.out.println(
                        "First name cannot be empty. Please try again."
                    );
                }
            } while (firstName.isEmpty());

           
            String lastName;

            do {
                System.out.print("Enter your last name: ");
                lastName = scanner.nextLine().trim();

                if (lastName.isEmpty()) {
                    System.out.println(
                        "Last name cannot be empty. Please try again."
                    );
                }
            } while (lastName.isEmpty());

          
            String username;

            while (true) {
                System.out.print("Enter a username: ");
                username = scanner.nextLine();

                if (!username.isEmpty()
                        && username.length() <= 5
                        && username.contains("_")) {

                    System.out.println("Username successfully captured.");
                    break;

                } else {
                    System.out.println(
                        "Invalid username. It must contain an underscore "
                        + "and be no longer than 5 characters. Try again."
                    );
                }
            }

           
            String password;

            while (true) {
                System.out.print("Enter a password: ");
                password = scanner.nextLine();

                boolean hasUppercase =
                        password.matches(".*[A-Z].*");

                boolean hasNumber =
                        password.matches(".*[0-9].*");

                boolean hasSpecialCharacter =
                        password.matches(".*[^a-zA-Z0-9].*");

                if (password.length() >= 8
                        && hasUppercase
                        && hasNumber
                        && hasSpecialCharacter) {

                    System.out.println("Password successfully captured.");
                    break;

                } else {
                    System.out.println(
                        "Invalid password. It must contain at least "
                        + "8 characters, an uppercase letter, a number "
                        + "and a special character. Try again."
                    );
                }
            }

            
            String cellPhoneNumber;

            while (true) {
                System.out.print(
                    "Enter your South African cell phone number (+27...): "
                );

                cellPhoneNumber = scanner.nextLine();

                if (cellPhoneNumber.matches("\\+27[0-9]{9}")) {

                    System.out.println(
                        "Cell phone number successfully captured."
                    );
                    break;

                } else {
                    System.out.println(
                        "Invalid number. Enter +27 followed by "
                        + "exactly 9 digits. Try again."
                    );
                }
            }

            // Create and register the user
            Login login = new Login(
                    username,
                    password,
                    cellPhoneNumber,
                    firstName,
                    lastName
            );

            System.out.println();
            System.out.println("REGISTRATION RESULT");
            System.out.println(login.registerUser());

            // Login validation
            boolean loggedIn = false;

            while (!loggedIn) {

                System.out.println();
                System.out.println("LOGIN");

                String enteredUsername;

                
                while (true) {
                    System.out.print("Enter your username: ");
                    enteredUsername = scanner.nextLine();

                    if (enteredUsername.equals(username)) {
                        System.out.println("Username accepted.");
                        break;
                    } else {
                        System.out.println(
                            "Incorrect username. Please try again."
                        );
                    }
                }

                String enteredPassword;

                
                while (true) {
                    System.out.print("Enter your password: ");
                    enteredPassword = scanner.nextLine();

                    if (enteredPassword.equals(password)) {
                        System.out.println("Password accepted.");
                        break;
                    } else {
                        System.out.println(
                            "Incorrect password. Please try again."
                        );
                    }
                }

                
                login.loginUser(enteredUsername, enteredPassword);

                if (login.checkLoginStatus()) {
                    System.out.println(login.returnLoginStatus());
                    loggedIn = true;
                } else {
                    System.out.println(
                        "Login unsuccessful. Please try again."
                    );
                }
            }
            QuickChat quickChat = new QuickChat();
            quickChat.start(scanner);
        }
    }
}

