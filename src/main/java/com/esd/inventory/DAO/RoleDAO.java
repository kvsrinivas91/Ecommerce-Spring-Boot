package com.esd.inventory.DAO;


import com.esd.inventory.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO extends DAO{

    public Role findById(long id) {
        Query query = getSession().createQuery("FROM Product WHERE id = :id");
        query.setParameter("id", id);
        return (Role) query.uniqueResult();
    }

}
