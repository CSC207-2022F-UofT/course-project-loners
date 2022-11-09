package Entity;

import java.awt.image.BufferedImage;
import java.util.List;

public interface Authenticator {
    public default boolean is_valid_email(String email){
        return true;
    }

    public default boolean is_valid_name(String name){
        return true;
    }

    public default boolean is_valid_age(int age){
        return true;
    }

    public default boolean is_valid_gender(String gender){
        return true;
    }

    public default boolean is_valid_orientation(String orientation){
        return true;
    }

    public default boolean is_valid_location(int[] location){
        return true;
    }

    public default boolean is_valid_image(BufferedImage image){
        return true;
    }

    public default boolean is_valid_bio(String bio){
        return true;
    }

    public default boolean is_valid_hobbies(List<String> hobbies){
        return true;
    }

    public default boolean is_valid_socialMedia(String socialMedia){
        return true;
    }
}
