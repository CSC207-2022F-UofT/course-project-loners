package project;

import java.util.Set;

public interface DataFetchSend {
    public default Set<Object> fetch() {
        return null;
    }
    public default boolean send(){
        return true;
    }
}
