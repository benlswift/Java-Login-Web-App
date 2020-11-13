package jsf.datastore;

import jsf.entities.User;
import jsf.entities.UsersEntity;

import java.util.ArrayList;

public class UserRepo {
    private ArrayList<UsersEntity> validUsers = new ArrayList<>();
    {
        addUser("ben", "password","ADMIN");
        addUser("user1","password1","USER");
    }

    public ArrayList<UsersEntity> getUsers(){
        return validUsers;
    }

    public void addUser(String username, String password, String accessLevel){
        UsersEntity user = new UsersEntity();
        int nextID = validUsers.size();
        user.setId(nextID);
        user.setUsername(username);
        user.setPassword(password);
        user.setAccessLevel(accessLevel);
        validUsers.add(user);
    }

    public void deleteUser(User userToDelete){
        validUsers.remove(userToDelete);
    }
}
