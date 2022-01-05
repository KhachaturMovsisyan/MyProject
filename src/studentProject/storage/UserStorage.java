package studentProject.storage;

import studentProject.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> userMaps = new HashMap<>();

    public void add(User user) {
        userMaps.put(user.getEmail(), user);
    }


    public User getUserByEmail(String email) {

        User user = userMaps.get(email);
        return user;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        for (User user : userMaps.values()) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
