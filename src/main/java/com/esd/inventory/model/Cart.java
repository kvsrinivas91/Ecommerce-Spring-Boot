package com.esd.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    private double totalCartAmount = 0.0;

    public double getTotalCartAmount() {
        return totalCartAmount;
    }

    public void updateItemCurrentCount(int count, Product product){
        product.setCurrentCount(count);
        this.updateTotalAmount();
    }

    public void setTotalCartAmount(double totalCartAmount) {
        this.totalCartAmount = totalCartAmount;
    }

    public Cart(){
        products = new ArrayList<>();
    }

    public void clear(){
        products.clear();
    }

    public int getCartSize(){
        return this.products.size();
    }

    public void addProduct(Product product){
        this.products.add(product);
        this.updateTotalAmount();
    }


    public void removeProduct(int index){
        this.products.remove(index);
        this.updateTotalAmount();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void updateTotalAmount(){
        this.totalCartAmount =  products.stream().mapToDouble(Product::getCurrentCountPrice).sum();
    }

    public void updateProductCount(){
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println(this.products.get(i).getId());
        }
    }
}
