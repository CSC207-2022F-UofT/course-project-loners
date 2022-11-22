package Use_Cases;

import Controllers_Presenters.DataFetchControl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;


public class Authenticator{

    public static boolean is_valid_email(String email){
        if(email.indexOf('@') == -1){
            return false;
        } else if (email.length() > 20) {
            return false;
        }
        return true;
    }

    public boolean is_valid_name(String name){
        if(name.length() > 20){
            return false;
        }
        return true;
    }

    public static boolean is_valid_age(int age){
        if(age < 0 || age>150){
            return false;
        }
        return true;
    }

    public boolean is_valid_gender(String gender){
        // Maybe we don't need to validate gender if you can choose from several options
        if (gender == "Female"){
            return true;
        } else if (gender == "Male") {
            return true;
        } else if (gender == "Other") {
            return true;
        } else {
            return false;
        }
    }

    public boolean is_valid_orientation(String orientation){
        // Maybe we don't need to validate orientation if you can choose from several options
        if (orientation == "Straight"){
            return true;
        } else if (orientation == "Gay") {
            return true;
        } else if (orientation == "Bi") {
            return true;
        } else {
            return false;
        }
    }
    public static boolean is_valid_address(String postalCode){
        // Canadian address consist of 6 letters with a format CNC NCN, where C is a letter and N is a digit.
        // There is a space separates the third and fourth characters
        // it does not include the letters D, F, I, O, Q or U.
        // Also, the first position does not make use of the letters W or Z.
        // referenced https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/postal-codes.page
        return postalCode.matches("^[A-Z]\\d[A-Z]\\d\\s?[A-Z]\\d$");
    }

    public boolean is_valid_location(double[] location){
        // As coordinates of a location consist of angles, the validity depends on the right angle of each.
        double latitude = location[0];
        double longitude = location[1];
        boolean status = false;
        if ((latitude >= -90 && latitude <= 90)){
           status = true;
        }
        if ((longitude >= -180 && longitude <= 180)){
            status = true;
        }
        return status;
    }

    public boolean is_valid_image(BufferedImage image){
        return true;
    }

    public boolean is_valid_bio(String bio){
        if(bio.length()>400){
            return false;
        }
        return true;
    }

    public boolean is_valid_hobbies(List<String> hobbies){
        for(int i =0; i< hobbies.size(); i++){
            if(hobbies.get(i).length() > 100){
                return false;
            }
        }
        return true;
    }

    public boolean is_valid_socialMedia(String socialMedia){
        // Maybe we don't need to validate socialMedia if you can choose from several options
        return true;
    }

    public static boolean email_exists(String input_email){
        // Returns true if email exists; returns false otherwise
        DataFetchControl datafetch_object = new DataFetchControl();
        ArrayList<String> email_list = datafetch_object.fetch_emails();
        for (String email : email_list){
            if (Objects.equals(email, input_email)){
                return true;}
        }return false;
    }

    public static boolean email_match_password(String email, String password){
        // Check email exists in database
        if (email_exists(email)){
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
        boolean test = Authenticator.email_match_password("email", "a");
        boolean test1 = Authenticator.is_valid_address("email");
        System.out.println(test);
        System.out.println(test1);
    }
}

