package usecases;

import java.util.List;

/**
 * A Use Case interface specifying the Output Boundary of ConnectProfiles, to be implemented by its Presenter.
 */
public interface ConnectProfilesOutputBoundary {

    /**
     * Pass the gathered connected profiles to be presented to the user for viewing.
     *
     * @param connections The list of connected profiles gathered by ConnectProfiles
     * @return The list of connected profiles
     */
    List<Integer> passConnections(List<Integer> connections);
}
