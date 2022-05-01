package com.esd.inventory.global;

import com.esd.inventory.model.Cart;
import com.esd.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static Cart cart;
    static {
        cart= new Cart();
    }
}
