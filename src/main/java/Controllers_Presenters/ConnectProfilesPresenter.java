package Controllers_Presenters;

import Use_Cases.ConnectProfilesOutputBoundary;

import java.util.List;

/**
 * A Presenter class that implements the Output Boundary of ConnectProfiles, by passing the gathered connected profiles
 * to be received by the UI.
 */
public class ConnectProfilesPresenter implements ConnectProfilesOutputBoundary {

    /**
     * Pass the gathered connected profiles to be presented to the user for viewing.
     *
     * @param connections the list of connected profiles gathered by ConnectProfiles
     * @return the list of connected profiles
     */
    @Override
    public List<Integer> passConnections(List<Integer> connections) {
        return connections;
    }
}
