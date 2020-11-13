package jsf.services;

import jsf.entities.User;
import jsf.entities.UsersEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name="Users.findByName", query = "SELECT a FROM Users a WHERE a.username = :name")

public class UserDAO {
    EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public UsersEntity findUserByID(int id){
        return entityManager.find(UsersEntity.class,id);
    }

    public List<UsersEntity> findByUserName(String username) {
        List<UsersEntity> listOfUsers = entityManager.createNamedQuery("Users.findByName")
                .setParameter("name",username).getResultList();
        return listOfUsers;
    }

//    public UsersEntity findUserByName(String username){
//
//    }
}
