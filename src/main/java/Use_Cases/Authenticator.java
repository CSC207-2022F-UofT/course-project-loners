package Use_Cases;

import Controllers_Presenters.DataFetchControl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Authenticator{

    public static boolean isValidEmail(String email){
        if(email.indexOf('@') == -1){
            return false;
        } else return email.length() <= 20;
    }

    public boolean isValidName(String name){
        return name.length() <= 20;
    }

    public static boolean isValidAge(int age){
        return age >= 0 && age <= 150;
    }

    public boolean isValidGender(String gender){
        // Maybe we don't need to validate gender if you can choose from several options
        if (Objects.equals(gender, "Female")){
            return true;
        } else if (Objects.equals(gender, "Male")) {
            return true;
        } else return Objects.equals(gender, "Other");
    }

    public boolean isValidOrientation(String orientation){
        // Maybe we don't need to validate orientation if you can choose from several options
        if (Objects.equals(orientation, "Straight")){
            return true;
        } else if (Objects.equals(orientation, "Gay")) {
            return true;
        } else return Objects.equals(orientation, "Bi");
    }
    public static boolean isValidAddress(String postalCode){
        // Canadian address consist of 6 letters with a format CNC NCN, where C is a letter and N is a digit.
        // There is a space separates the third and fourth characters
        // it does not include the letters D, F, I, O, Q or U.
        // Also, the first position does not make use of the letters W or Z.
        // referenced https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/postal-codes.page
        return postalCode.matches("^[A-Z]\\d[A-Z]\\d\\s?[A-Z]\\d$");
    }

    public boolean isValidLocation(double[] location){
        // As coordinates of a location consist of angles, the validity depends on the right angle of each.
        double latitude = location[0];
        double longitude = location[1];
        boolean status = latitude >= -90 && latitude <= 90;
        if ((longitude >= -180 && longitude <= 180)){
            status = true;
        }
        return status;
    }

    public boolean isValidImage(BufferedImage image){
        return true;
    }

    public boolean isValidBio(String bio){
        return bio.length() <= 400;
    }

    public boolean isValidHobbies(List<String> hobbies){
        for (String hobby : hobbies) {
            if (hobby.length() > 100) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidSocialMedia(String socialMedia){
        // Maybe we don't need to validate socialMedia if you can choose from several options
        return true;
    }

    public static boolean emailExists(String inputEmail){
        // Returns true if email exists; returns false otherwise
        DataFetchControl fetchObject = new DataFetchControl();
        ArrayList<String> emailList = fetchObject.fetch_emails();
        for (String email : emailList){
            if (Objects.equals(email, inputEmail)){
                return true;}
        }return false;
    }

    public static boolean emailMatchPassword(String email, String password){
        // Check email exists in database
        if (emailExists(email)){
            // Case 1: email does not match password (wrong password)
            String database_password = DataFetchControl.fetch_password(email);
            if (!Objects.equals(password, database_password)){
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

    public static void main(String[] args) {
        new Authenticator();
        // Testing method email_match_password
        boolean test = Authenticator.emailMatchPassword("email", "a");
        boolean test1 = Authenticator.isValidEmail("email");
        System.out.println(test);
        System.out.println(test1);
    }
}