package jsf.services;

import jsf.entities.User;
import jsf.entities.UsersEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Stateless
public class RegistrationService {

//check if user is valid
    public String validUserCheck(String username, String password,String access){
        //for (int i =0; i<userRepo.getUsers().size();i++) {
        String output = "index";
            if (username.equals("ben") && (password.equals("password"))) {
                if (access.equals("ADMIN")){
                    output = "admin_page";
                }
                else {
                    output = "welcome";
                }
            }
        //}
        return output;
    }

    public String validUser(){
        return "welcome";
    }

    public String invalidUser(){
        return "index";
    }

    public String registration(UsersEntity user) {
        return validUserCheck(user.getUsername(), user.getPassword(), user.getAccessLevel());
    }

}
