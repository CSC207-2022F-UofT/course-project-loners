package Entities;

public class Preferences {
    private int preferredAge;
    private String preferredGender;
    private double[] preferredLocation;
    private String id;

    public Preferences(int preferredAge, String preferredGender, double[] preferredLocation, String id) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocation = preferredLocation;
        this.id = id;
    }

    public int getPreferredAge() {
        return preferredAge;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public double[] getPreferredLocation() {
        return preferredLocation;
    }

    public String getID() {
        return id;
    }
}
