
package ca.sait.services;

import ca.sait.dataaccess.UserDB;
import ca.sait.models.Item;
import ca.sait.models.User;
import java.util.List;

/**
 *
 * @author Benmarc Agulto
 */
public class UserService {

    public UserService() {

    }

    public User getUser(String email, String password) {
        UserDB userDb = new UserDB();

        User user = userDb.getUser(email);

        if (user != null && password.equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public List<User> getAll() {
        UserDB userDb = new UserDB();

        return userDb.getAll();
    }

    public void updateUser(User user) {
        if (user != null) {
            UserDB userDB = new UserDB();

            userDB.updateUser(user);
        }
    }

    public boolean updateUserInfo(User newUserInfo, User oldUserInfo) {
        if (newUserInfo != null) {
            UserDB uDb = new UserDB();
            ItemService itemService = new ItemService();

            if (!newUserInfo.getEmail().equals(oldUserInfo.getEmail())) {
                User checkUserEmail = uDb.getUser(newUserInfo.getEmail());

                if (checkUserEmail != null) {
                    return false;
                }
            }

            List<Item> usersItems = itemService.getAll(oldUserInfo);
            
            for (Item item : usersItems) {
                    itemService.deleteItem(item);
            }

            Boolean updated = uDb.updateUser(newUserInfo, oldUserInfo.getEmail());

            if (updated) {
                for (Item item : usersItems) {
                    item.setOwner(newUserInfo);
                    itemService.createItem(item);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public void deleteUser(User user) {
        if (user != null) {
            UserDB userDB = new UserDB();

            userDB.deleteUser(user);
        }
    }

    public boolean createUser(User user) {
        if (user != null) {
            UserDB userDB = new UserDB();

            return userDB.createUser(user);
        }
        return false;
    }
}
