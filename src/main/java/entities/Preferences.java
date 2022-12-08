package entities;

/**
 * An Entity class that stores the preferences of a user, which describe the type of people the user would like to date.
 * Also used as Input Data.
 */
public class Preferences {
    /** The user's preferred age of other users */
    private final int preferredAge;

    /** The user's preferred gender (male, female, or other) of other users */
    private final String preferredGender;

    /** The user's preferred location range */
    private final double preferredLocationRange;

    /** The user's ID */
    private final int id;

    /**
     * Construct a Preferences object.
     *
     * @param preferredAge The user's preferred age
     * @param preferredGender The user's preferred gender
     * @param preferredLocationRange The user's preferred location range
     * @param id The user's ID
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
     * @return The user's preferred age
     */
    public int getPreferredAge() {
        return preferredAge;
    }

    /**
     * Get the user's preferred gender.
     *
     * @return The user's preferred gender
     */
    public String getPreferredGender() {
        return preferredGender;
    }

    /**
     * Get the user's preferred location range.
     *
     * @return The user's preferred location range
     */
    public double getPreferredLocationRange() {
        return preferredLocationRange;
    }

    /**
     * Get the user's ID.
     *
     * @return The user's ID
     */
    public int getID() {
        return id;
    }
}
