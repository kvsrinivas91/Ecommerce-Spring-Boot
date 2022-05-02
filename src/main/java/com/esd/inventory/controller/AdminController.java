package com.esd.inventory.controller;

import com.esd.inventory.model.ProductDTO;
import com.esd.inventory.model.Category;
import com.esd.inventory.model.Product;
import com.esd.inventory.service.CategoryService;
import com.esd.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryS;

    @Autowired
    ProductService productS;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryS.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){

        if(category.getId() ==  0){
            categoryS.addCategory(category);
        }
        else {
            categoryS.updateCategoryById(category);
        }
        int tempId = 0;
        {

        }

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryS.removeCategoryById(id);
        return "redirect:/admin/categories";

    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> categoryUpdate = categoryS.getCategoryById(id);
        if(categoryUpdate.isPresent()){
            model.addAttribute("category", categoryUpdate.get());
            return "categoriesAdd";
        } else
            return "404";
    }

    // Product Section
    @GetMapping("/admin/products")
    public String productsGet(Model model){
        model.addAttribute("products", productS.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String productAddandGet(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryS.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String productAddandPost(@ModelAttribute("productDTO")ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam("imgName")String imgName) throws IOException{

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryS.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        product.setCount(productDTO.getCount());

        String imageUUID;

        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else{
            imageUUID = imgName;
        }

        product.setImageName(imageUUID);

        if(productDTO.getId()==null){
            productS.addProduct(product);
        }
        else {
            productS.updateProduct(product);
        }

        return "redirect:/admin/products";
    }


    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductItem(@PathVariable long id){
        productS.removeProductById(id);
        return "redirect:/admin/products";

    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProductandGet(@PathVariable long id, Model model){
        Product product = productS.getProductById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId((product.getCategory().getId()));
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight((product.getWeight()));
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        productDTO.setCount(product.getCount());



        model.addAttribute("categories", categoryS.getAllCategory());
        model.addAttribute("productDTO", productDTO);


        return "productsAdd";
    }

}
