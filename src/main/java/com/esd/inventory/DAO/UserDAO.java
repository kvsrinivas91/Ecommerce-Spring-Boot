package com.esd.inventory.DAO;

import com.esd.inventory.model.Category;
import com.esd.inventory.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;



@Component
public class UserDAO extends DAO{

    public User findUserByEmail(String email) {
        Query query = getSession().createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    public void save(User user) {
        try {
            begin();
            getSession().save(user);
            commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
