package Controllers_Presenters;

import Use_Cases.ConnectProfilesOutputBoundary;

import java.util.List;

public class ConnectProfilesPresenter implements ConnectProfilesOutputBoundary {

    @Override
    public List<Integer> passConnections(List<Integer> connections) {
        return connections;
    }
}
