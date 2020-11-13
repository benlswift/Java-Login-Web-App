package jsf.beans;

import jsf.entities.User;
import jsf.entities.UsersEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserBean {
    @Inject
    UsersEntity user;

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public UsersEntity getUser() {
        return user;
    }

}
