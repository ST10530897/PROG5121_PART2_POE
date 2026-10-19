
package za.ac.rosebank.registrationlogin;

import java.util.regex.Pattern;

public class Login {

    private final String username;
    private final String password;
    private final String cellPhoneNumber;
    private final String firstName;
    private final String lastName;

    private boolean loginSuccessful;

    public Login(String username, String password, String cellPhoneNumber,
                 String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginSuccessful = false;
    }

    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecialCharacter = password.matches(".*[^a-zA-Z0-9].*");

        return hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }

    public boolean checkCellPhoneNumber() {

        if (cellPhoneNumber == null) {
            return false;
        }

        String regex = "^\\+27[0-9]{9}$";

        return Pattern.matches(regex, cellPhoneNumber);
    }

    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, a "
                    + "capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not "
                    + "contain international code.";
        }

        return "User registered successfully.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {

        loginSuccessful = username != null
                && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);

        return loginSuccessful;
    }

    public String returnLoginStatus() {

        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
  
public boolean checkLoginStatus() {
    return loginSuccessful;
}
} 


