package Use_Cases;

import java.awt.image.BufferedImage;
import java.util.List;



public class Authenticator{

    public boolean is_valid_email(String email){
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

    public boolean is_valid_age(int age){
        if(age < 0 || age>150){
            return false;
        }
        return true;
    }

    public boolean is_valid_gender(String gender){
        // Maybe we don't need to validate gender if you can choose from several options
        return true;
    }

    public boolean is_valid_orientation(String orientation){
        // Maybe we don't need to validate orientation if you can choose from several options
        return true;
    }

    public boolean is_valid_location(double[] location){
        // Depends on how to represent locations
        return true;
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
}
