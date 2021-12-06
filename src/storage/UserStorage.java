package storage;

import model.User;

public class UserStorage {
        private User[] users = new User[10];
        private int size = 0;


        public void add(User user) {

            if (size == users.length) {
                extend();
            }
            users[size++] = user;
        }

        private void extend() {
            User[] newArray = new User[users.length + 10];
            System.arraycopy(users, 0, newArray, 0, users.length);
            users = newArray;
        }

    public User getUserByEmail(String userDate) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(userDate)){
                return users[i];
            }
        }
        return null;
    }

    public User getUserByEmailAndpassword(String email, String password) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)&&users[i].getPassword().equals(password)){
                return users[i];
            }
        }
        return null;
    }
}
