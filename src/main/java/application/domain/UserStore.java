package application.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amahendru on 12/1/15.
 */
@Component
public class UserStore {

    public UserStore() {
    }

    Map<String, User> users = new HashMap<>();

    public Map<String, User> getUsers() {
        return users;
    }
}
