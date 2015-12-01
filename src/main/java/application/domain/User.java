package application.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by amahendru on 12/1/15.
 */
public class User {

    String userID;
    Map<String, Selection> liked = new LinkedHashMap<>();
    Map<String, Selection> disliked = new LinkedHashMap<>();


    public User(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public Map<String, Selection> getLiked() {
        return liked;
    }

    public Map<String, Selection> getDisliked() {
        return disliked;
    }
}
