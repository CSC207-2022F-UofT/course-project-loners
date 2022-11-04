package project;

import java.awt.image.BufferedImage;
import java.util.List;


public class Profile {
    private String email;
    private String name;
    private String password;
    private int age;
    private String gender;
    private String orientation;
    private int[] location;
    private BufferedImage image;
    private String bio;
    private List<String> hobbies;
    private String socialMedia;
    private List<String> likes;

    public Profile(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Profile(String name, int age, String gender, String orientation, int[] location, BufferedImage image,
                   String bio, List<String> hobbies, String socialMedia) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.orientation = orientation;
        this.location = location;
        this.image = image;
        this.bio = bio;
        this.hobbies = hobbies;
        this.socialMedia = socialMedia;
    }

    public void addLike(String email){
        likes.add(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
}
