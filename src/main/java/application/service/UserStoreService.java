package application.service;

import application.domain.RatedSuggestion;
import application.domain.Selection;
import application.domain.User;
import application.domain.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amahendru on 12/1/15.
 */
@Service
public class UserStoreService {

    @Autowired
    private UserStore userStore;

    public void storeUserResponse(RatedSuggestion ratedSuggestion) {

        synchronized (userStore) {
            String userID = ratedSuggestion.getUserId();
            if (null != userStore && null == userStore.getUsers().get(userID)) {
                User user = new User(userID);
                userStore.getUsers().put(userID, user);
            }

            User user = userStore.getUsers().get(userID);

            Selection selection = new Selection(ratedSuggestion.getCityName(), ratedSuggestion.getPrice());
            if (ratedSuggestion.isLiked()) {
                user.getLiked().put(ratedSuggestion.getCityName(), selection);
            } else {
                user.getDisliked().put(ratedSuggestion.getCityName(), selection);
            }

        }

    }
}
