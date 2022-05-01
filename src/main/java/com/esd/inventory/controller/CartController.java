package com.esd.inventory.controller;

import com.esd.inventory.global.GlobalData;
import com.esd.inventory.model.Product;
import com.esd.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.addProduct(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cart", GlobalData.cart);
        int count = 1;
        model.addAttribute("products", GlobalData.cart.getProducts());
        model.addAttribute("count",count);
        return "cart";
    }

    @GetMapping("cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.removeProduct(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total", GlobalData.cart.getCartSize());
        return "checkout";
    }

    @PostMapping("/orderPlaced")
    public String OrderPlaced(Model model){
        GlobalData.cart.getProducts().forEach(product -> {
            Product currentProduct = productService.getProductById(product.getId()).get();
            currentProduct.setCount(currentProduct.getCount()-product.getCount());
            productService.updateProduct(currentProduct);
        });
        model.addAttribute("result", "Text");
        model.addAttribute("parameters",new HashMap<>());
        GlobalData.cart.updateProductCount();
        return "orderPlaced";
    }


}
