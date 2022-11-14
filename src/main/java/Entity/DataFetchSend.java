package Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface DataFetchSend {
    default Set<Object> fetch() {
        return null;
    }
    default boolean send(HashMap<String, Object> info){
        return true;
    }
}