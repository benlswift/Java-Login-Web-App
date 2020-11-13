package jsf.datastore;

import jsf.entities.User;

import java.util.ArrayList;

public class UserRepo {
    private ArrayList<User> validUsers = new ArrayList<>();
    {
        addUser(1,"ben", "password","ADMIN");
        addUser(2,"user1","password1","USER");
    }

    //CRUD
    public ArrayList<User> getUsers(){
        return validUsers;
    }

    public void addUser(int id, String username, String password, String accessLevel){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAccessLevel(accessLevel);
        validUsers.add(user);
    }

    public void deleteUser(User userToDelete){
        validUsers.remove(userToDelete);
    }
}
