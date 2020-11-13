package jsf.datastore;

import jsf.entities.User;

import java.util.ArrayList;

public class UserRepo {
    private ArrayList<User> validUsers = new ArrayList<>();
    {
        addUser(1,"ben", "password","ADMIN");
    }

    //CRUD
    public ArrayList<User> getUsers(){
        return validUsers;
    }

    public void addUser(int id, String username, String password, String accessLevel){
    }

    public void deleteUser(User userToDelete){
        validUsers.remove(userToDelete);
    }
}
