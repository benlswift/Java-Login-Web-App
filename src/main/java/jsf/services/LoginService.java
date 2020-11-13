package jsf.services;

import jsf.beans.UserBean;
import jsf.datastore.UserRepo;
import jsf.entities.User;
import jsf.entities.UsersEntity;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@Stateless
public class LoginService {

    @Inject
    UserRepo userRepo;

    @Inject
    User user1;
    @Inject
    SecurityContext securityContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    FacesContext facesContext;

////check if user is valid
//    public String validUserCheck(String username, String password){
//        String output = "index";
//        for (int i =0; i<userRepo.getUsers().size();i++){
//            if (username.equals(userRepo.getUsers().get(i).getUsername())
//                    && (password.equals(userRepo.getUsers().get(i).getPassword()))) {
//                user.getUser().setAccessLevel(userRepo.getUsers().get(i).getAccessLevel());
//                continueAuthentication();
//                output = "view/welcome.xhtml";
//                return output;
//            }
//        }
//        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INVALID LOGIN",null));
//        return output;
//    }

//    public String login(String username, String password) {
//
//         return validUserCheck(username, password);
//    }
    public String registration(String username, String password,String role){
        userRepo.addUser(username,password,role);
        return "admin_page.xhtml?faces-redirect=true";
    }



}
