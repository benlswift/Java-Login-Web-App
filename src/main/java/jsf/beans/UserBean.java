package jsf.beans;

import jsf.datastore.UserRepo;
import jsf.entities.User;
import jsf.entities.UsersEntity;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class UserBean {
    @Inject
    User user;
    @Inject
    SecurityContext securityContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    FacesContext facesContext;

    @Inject
    UserRepo userRepo;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    //check if user is valid
    public String validUserCheck(){
        String username = getUser().getUsername();
        String password = getUser().getPassword();
        String output = "index";
        for (int i =0; i<userRepo.getUsers().size();i++){
            if (username.equals(userRepo.getUsers().get(i).getUsername())
                    && (password.equals(userRepo.getUsers().get(i).getPassword()))) {
                getUser().setAccessLevel(userRepo.getUsers().get(i).getAccessLevel());
                //continueAuthentication();
                output = "view/welcome.xhtml";
                return output;
            }
        }
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INVALID LOGIN",null));
        return output;
    }

    public void submit(){
        switch (continueAuthentication()){
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "INVALID LOGIN",null));
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
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(user.getUsername(),user.getPassword())));
    }

    public String logout(){
//        ExternalContext externalContext = facesContext.getExternalContext();
//        try {
//            ((HttpServletRequest)externalContext.getRequest()).logout();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }

        facesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }



}
