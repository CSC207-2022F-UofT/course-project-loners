package Entities;

public class Preferences {
    private int preferredAge;
    private String preferredGender;
    private double preferredLocationRange;
    private int id;

    public Preferences(int preferredAge, String preferredGender, double preferredLocationRange, int id) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocationRange = preferredLocationRange;
        this.id = id;
    }

    public Preferences(int preferredAge, String preferredGender, double preferredLocationRange) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocationRange = preferredLocationRange;
        this.id = 0;
    }

    public int getPreferredAge() {
        return preferredAge;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public double getPreferredLocationRange() {
        return preferredLocationRange;
    }

    public int getID() {
        return id;
    }
}
