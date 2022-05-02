package com.esd.inventory.DAO;

import com.esd.inventory.model.Category;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDAO extends DAO{

    public List<Category> getAllCategory() {
        Query<Category> query = getSession().createQuery("FROM Category ");
        List<Category> list = query.list();
        return list;
    }

    public void save(Category category) {
        try {
            begin();
            getSession().save(category);
            commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Category category) {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id){
        Transaction txn = getSession().beginTransaction();
        javax.persistence.Query query = getSession().createQuery("delete Category where id = :ID");
        query.setParameter("ID", id);

        int result = query.executeUpdate();
        if (result > 0) {
            System.out.println(id + " Category was removed");
        }
            txn.commit();
    }

    public Category findById(int id) {
        Query query = getSession().createQuery("FROM Category WHERE id = :id");
        query.setParameter("id", id);
        return (Category) query.uniqueResult();
    }






}
