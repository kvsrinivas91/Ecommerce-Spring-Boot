package com.esd.inventory.global;

import com.esd.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;

    static {
        cart= new ArrayList<Product>();
    }
}
