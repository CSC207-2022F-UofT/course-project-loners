package entities;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * An entity to store the attributes of the user's profile, or information about the user. Also used as Input Data.
 */
public class Profile{
    private String email;
    private String name;
    private final String password;
    private int age;
    private String gender;
    private String orientation;
    private double[] location;
    private BufferedImage image;
    private String bio;
    private List<String> hobbies;
    private final String socialMedia;
    private List<String> likes;

    /**
     * Constructor for the Profile Class
     * @param name user's name
     * @param age user's age, from 1 to 100
     * @param gender user's gender, either male, female, other
     * @param orientation user's sexual orientation, straight or gay
     * @param location the user's coordinates
     * @param image the user's image
     * @param bio the user's biography
     * @param hobbies a list of the user's hobbies
     * @param socialMedia the user's social media profile handle
     * @param email the user's email, has to have @
     * @param likes the list of other profile ids that the user had liked
     * @param password the user's password
     */
    public Profile(String name, int age, String gender, String orientation, double[] location, BufferedImage image,
                   String bio, List<String> hobbies, String socialMedia, List<String> likes, String email,
                   String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.orientation = orientation;
        this.location = location;
        this.image = image;
        this.bio = bio;
        this.hobbies = hobbies;
        this.socialMedia = socialMedia;
        this.likes = likes;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor for the profile class
     * @param social the user's social media
     * @param email the user's email
     * @param password the user's password
     * @param name the user's name
     * @param age the user's age, from 1 to 100
     * @param gender the user's gender, male, female, or other
     * @param location the user's location in coordinates
     */
    public Profile(String social, String email, String password, String name, int age, String gender, double[] location) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.socialMedia = social;
    }

    /**
     * a getter for the email
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * A setter for the email
     * @param email the user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * a getter for the name
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * a setter for the name
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the user's password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * a getter for the user's age
     * @return the user's age
     */
    public int getAge() {
        return age;
    }

    /**
     * A setter for the user's age
     * @param age the user's age from 1 to 100
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * A getter for the user's gender
     * @return the user's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * A setter for the user's gender
     * @param gender the user's gender, either male, female, or other
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * A getter for the user's orientation
     * @return the user's orientation, either straight or gay
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * A setter for the user's sexual orientation
     * @param orientation the user's sexual orientation
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * A getter for the user's location
     * @return the user's location coordinates
     */
    public double[] getLocation() {
        return location;
    }

    /**
     * A setter for the user's location coordinates
     * @param location the user's location coordinates
     */
    public void setLocation(double[] location) {
        this.location = location;
    }

    /**
     * A getter for the user's BufferedImage
     * @return The user's image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * A setter for the user's BufferedImage
     * @param image the user's BufferedImage
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * A getter for the user's bio
     * @return The user's bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * A setter for the user's bio
     * @param bio the user's bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * A getter for the user's hobby list
     * @return A list of the user's hobbies
     */
    public List<String> getHobbies() {
        return hobbies;
    }

    /**
     * A setter for the user's hobbies
     * @param hobbies the user's hobby list
     */
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    /**
     * A getter for the user's social media handle
     * @return the user's social media handle
     */
    public String getSocialMedia() {
        return socialMedia;
    }

    /**
     * A getter for the user's list of likes
     * @return A list of users that the user likes
     */
    public List<String> getLikes() {
        return likes;
    }

    /**
     * A setter for the user's list of likes
     * @param likes the list of users that the user likes
     */
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
}
