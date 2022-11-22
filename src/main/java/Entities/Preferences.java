package Entities;

public class Preferences {
    private int preferredAge;
    private String preferredGender;
    private double[] preferredLocation;
    private double preferredLocationRange;
    private int id;

    public Preferences(int preferredAge, String preferredGender, double[] preferredLocation,
                       double preferredLocationRange, int id) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocation = preferredLocation;
        this.preferredLocationRange = preferredLocationRange;
        this.id = id;
    }

    public Preferences(int preferredAge, String preferredGender,
                       double preferredLocationRange) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocation = new double[]{0.0, 0.0};
        this.preferredLocationRange = preferredLocationRange;
        this.id = 0;
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
    public double getPreferredLocationRange() {
        return preferredLocationRange;
    }

    public int getID() {
        return id;
    }
}
