package Entity;

import java.util.Set;

public interface DataFetchSend {
    default Set<Object> fetch() {
        return null;
    }
    default boolean send(){
        return true;
    }
}
