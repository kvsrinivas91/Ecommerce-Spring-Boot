package com.esd.inventory.service;

import com.esd.inventory.DAO.CategoryDAO;
import com.esd.inventory.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static com.esd.inventory.DAO.DAO.getSession;

@Service
public class CategoryService {



    @Autowired
    CategoryDAO categoryDAO;



    public List<Category> getAllCategory(){
        return categoryDAO.getAllCategory();
    }

    public void addCategory(Category category){
        categoryDAO.save(category);
    }

    public void removeCategoryById(int id){
        categoryDAO.deleteById(id);
    }

    public void updateCategoryById(Category category) {categoryDAO.update(category);}

    public Optional<Category> getCategoryById(int id){
        return Optional.ofNullable(categoryDAO.findById(id));
    }

}
