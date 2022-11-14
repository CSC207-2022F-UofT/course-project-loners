package Entities;

public class Preferences {
    private int preferredAge;
    private String preferredGender;
    private double[] preferredLocation;
    private Profile user;

    public Preferences(int preferredAge, String preferredGender, double[] preferredLocation, Profile user) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocation = preferredLocation;
        this.user = user;
    }

    public int getPreferredAge() {
        return preferredAge;
    }

    public void setPreferredAge(int preferredAge) {
        this.preferredAge = preferredAge;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(String preferredGender) {
        this.preferredGender = preferredGender;
    }

    public double[] getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(double[] preferredLocation) {
        this.preferredLocation = preferredLocation;
    }
}
