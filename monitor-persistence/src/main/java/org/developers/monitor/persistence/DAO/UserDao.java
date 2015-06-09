/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.util.List;

import org.apache.tomcat.jni.User;
import org.developers.monitor.persistence.Users;
import org.developers.monitor.persistence.service.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kamciak
 */
@Service
public class UserDao extends Dao<Integer, Users> {
    
    public boolean isUserNameAvailable(String userName)
    {
        
        List<Users> users = entityManager.createNamedQuery("Users.findByUserName", Users.class)
            .setParameter("userName", userName)
            .getResultList();

        return users.isEmpty();
    }
    
    public List<Users> getAllUsers()
    {
        return entityManager.createNamedQuery("Users.findAll", Users.class)
            .getResultList();
    }
    
    public Users getUserById(Integer id) throws UserNotFoundException
    {
        try 
        {
            Users user = entityManager.createNamedQuery("Users.findByIdUser", Users.class)
                                .setParameter("idUser", id)
                                .getSingleResult();
            
            return user;
        }
        catch(Exception ex)
        {
            throw new UserNotFoundException(String.format("No user in database with id: [%s]", id));
        }
    }
    
    public Users getUserByUserName(String userName) throws UserNotFoundException {
        try {
            List<Users> users = entityManager.createNamedQuery("Users.findByUserName", Users.class)
                    .setParameter("userName", userName)
                    .getResultList();
            return users.get(0);
        } catch(Exception ex) {
            throw new UserNotFoundException(String.format("No user in database with username: [%s]", userName));
        }
    }
}
