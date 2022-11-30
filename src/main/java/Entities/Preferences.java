package Entities;

/**
 * Class for the Preferences entity: what the user prefers to date
 */
public class Preferences {
    private final int preferredAge;
    private final String preferredGender;
    private final double preferredLocationRange;
    private final int id;

    /**
     * Constructor for the Preferences class
     * @param preferredAge user's preferred age
     * @param preferredGender user's preferred gender
     * @param preferredLocationRange user's preferred location range
     * @param id the user's id
     */
    public Preferences(int preferredAge, String preferredGender, double preferredLocationRange, int id) {
        this.preferredAge = preferredAge;
        this.preferredGender = preferredGender;
        this.preferredLocationRange = preferredLocationRange;
        this.id = id;
    }

    /**
     * @return user's preferred age
     */
    public int getPreferredAge() {
        return preferredAge;
    }

    /**
     * @return user's preferred gender
     */
    public String getPreferredGender() {
        return preferredGender;
    }

    /**
     * @return user's preferred location range
     */
    public double getPreferredLocationRange() {
        return preferredLocationRange;
    }

    /**
     * @return the user's id
     */
    public int getID() {
        return id;
    }
}
