package studentProject.storage;

import studentProject.exception.UserNotFoundException;
import studentProject.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private List<User> userList = new ArrayList<>();

    public void add(User user) {
        userList.add(user);

    }


    public Object getUserByEmail(String userDate) throws UserNotFoundException {
        for (User user : userList) {
            if (user.getEmail().equals(userDate)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
