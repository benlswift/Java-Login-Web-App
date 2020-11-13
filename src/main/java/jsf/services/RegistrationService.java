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
import java.nio.file.attribute.UserDefinedFileAttributeView;

import static javax.security.enterprise.AuthenticationStatus.SUCCESS;

@Named
@Stateless
public class RegistrationService {

    @Inject
    UserBean user;
    @Inject
    UserRepo userRepo;

    @Inject
    SecurityContext securityContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    FacesContext facesContext;

//check if user is valid
    public String validUserCheck(String username, String password){
        String output = "index";
        for (int i =0; i<userRepo.getUsers().size();i++){
            if (username.equals(userRepo.getUsers().get(i).getUsername())
                    && (password.equals(userRepo.getUsers().get(i).getPassword()))) {
                output = "view/welcome.xhtml";
                return output;
            }
        }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INVALID LOGIN",null));
        return output;
    }

    public String registration(String username, String password) {

         return validUserCheck(username, password);
    }


    public void submit(){
        switch (continueAuthentication()){
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No, no, no",null));
                break;
            case SUCCESS:
                try {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/view/welcome.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate((HttpServletRequest)externalContext.getRequest(),
                (HttpServletResponse)externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(user.getUser().getUsername(), user.getUser().getPassword())));
    }


}
