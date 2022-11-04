package Entity;

public class Preferences {
    private int preferredAge;
    private String preferredGender;
    private double[] preferredLocation;

    public Preferences(int preferredAge, String preferredGender, double[] preferredLocation) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocation = preferredLocation;
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
