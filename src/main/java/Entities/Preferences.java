package Entities;

/**
 * An Entity class that stores the preferences of a user, which describe the type of people the user would like to date.
 * Also the Input Data for EditPreferences and ConnectProfiles.
 */
public class Preferences {
    private final int preferredAge;
    private final String preferredGender;
    private final double preferredLocationRange;
    private final int id;

    /**
     * Construct a Preferences object.
     *
     * @param preferredAge the user's preferred age
     * @param preferredGender the user's preferred gender
     * @param preferredLocationRange the user's preferred location range
     * @param id the user's id
     */
    public Preferences(int preferredAge, String preferredGender, double preferredLocationRange, int id) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocationRange = preferredLocationRange;
        this.id = id;
    }

    /**
     * Get the user's preferred age.
     *
     * @return the user's preferred age
     */
    public int getPreferredAge() {
        return preferredAge;
    }

    /**
     * Get the user's preferred gender.
     *
     * @return the user's preferred gender
     */
    public String getPreferredGender() {
        return preferredGender;
    }

    /**
     * Get the user's preferred location range.
     *
     * @return the user's preferred location range
     */
    public double getPreferredLocationRange() {
        return preferredLocationRange;
    }

    /**
     * Get the user's ID.
     *
     * @return the user's ID
     */
    public int getID() {
        return id;
    }
}
