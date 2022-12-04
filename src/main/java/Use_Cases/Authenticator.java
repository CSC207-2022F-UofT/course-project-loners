package Use_Cases;

import Controllers_Presenters.DataFetchControl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Authenticator{

    /**
     * This method checks the user input to the email field. The input has to have the '@' symbol
     * and have a length shorter than 20 letters. If the above two conditions are satisfied, the
     * method returns true. Otherwise, it returns false.
     * @param email user input to the email field.
     * @return true if input is a valid email, false otherwise.
     */
    public static boolean isValidEmail(String email){
        if(email.indexOf('@') == -1){
            return false;
        } else return email.length() <= 20;
    }

    /**
     * This method checks the user input to the username field. The input is considered valid if the
     * length below 20 characters.
     * @param name user input to the username field
     * @return true if input is a valid username, false otherwise.
     */
    public static boolean isValidName(String name){
        return name.matches("([a-zA-Z',.-]+( [a-zA-Z',.-]+)*){2,30}");
    }

    /**
     * This method checks the user input to the age field. The input is considered valid if
     * the entered age is between 1 and 149 (inclusive).
     * @param age integer value inputted by user
     * @return true if input is a valid age, false otherwise.
     */
    public static boolean isValidAge(int age){
        return age >= 0 && age <= 150;
    }

    /**
     * This method checks whether the inputted postal code is valid. A valid postal code has to contain 6 letters in the
     * format CNC NCN, where C is a letter and N is a digit. There also has to be a space that separates the third and
     * fourth characters. The first position does not make use of the letters W or Z. None of the letters can involve
     * D, F, I, O, Q or U. The above criteria is sourced from
     * <a href="https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/postal-codes.page">...</a>
     * @param postalCode user input to postal code field
     * @return true if postal code is valid, false otherwise.
     */
    public static boolean isValidAddress(String postalCode){
        return postalCode.matches("^[A-Z]\\d[A-Z]\\d\\s?[A-Z]\\d$");
    }

    /**
     * This method checks whether the user's bio is valid. It is valid if the bio contains less than or equal to 400
     * letters.
     * @param bio user's bio in their profile
     * @return true if bio is valid, false otherwise.
     */
    public static boolean isValidBio(String bio){
        return bio.length() <= 400;
    }

    /**
     * This method checks whether the user's hobbies are valid. The length of the list of hobbies is less than 100
     * letters.
     * @param hobbies a list of hobbies entered by user in their profile
     * @return true if the list of hobbies is valid, false otherwise.
     */
    public static boolean isValidHobbies(List<String> hobbies){
        for (String hobby : hobbies) {
            if (hobby.length() > 100) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks whether the email exists in our database. In other words, it checks whether the email has been
     * registered in our system.
     * @param inputEmail email we are examining.
     * @return true if email exists in our database, false otherwise.
     */
    public static boolean emailExists(String inputEmail){
        // Returns true if email exists; returns false otherwise
        DataFetchControl fetchObject = new DataFetchControl();
        ArrayList<String> emailList = fetchObject.fetchEmails();
        for (String email : emailList){
            if (Objects.equals(email, inputEmail)){
                return true;}
        }return false;
    }

    /**
     * This method checks if the given email and password matches our records. If the email is not registered,
     * i.e. does not exist in our database, the method returns false and prints a warning message.
     * If the email has been registered, the method checks if the password matches the email.
     * If it does, we return true. If it does not, we return false.
     *
     * @param email String representing email we want to verify
     * @param password String representing password we want to check
     * @return true if email matches password, false otherwise.
     */
    public static boolean emailMatchPassword(String email, String password){
        // Check email exists in database
        if (emailExists(email)){
            // Case 1: email does not match password (wrong password)
            String databasePassword = DataFetchControl.fetchPassword(email);
            if (!Objects.equals(password, databasePassword)){
                System.out.println("Incorrect password. Please try again.");
                return false;
            }
            // Case 2: email matches password
            else{System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Email is not registered. Head to the Register page to join us!");
        return false;
    }

    /**
     * Our main method. Currently used to test two methods: (1) emailMatchPassword() and (2) isValidEmail()
     * @param args a list of Strings
     */
    public static void main(String[] args) {
        new Authenticator();
        // Testing method email_match_password
        boolean test = Authenticator.emailMatchPassword("email", "a");
        boolean test1 = Authenticator.isValidEmail("email");
        System.out.println(test);
        System.out.println(test1);
    }
}