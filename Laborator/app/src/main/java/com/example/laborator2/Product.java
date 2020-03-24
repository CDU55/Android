package com.example.laborator2;

import java.util.ArrayList;

public class Product {

    public String productName;
    public int productPrice;

    @Override
    public String toString() {
        return productName;
    }

    public Product(String productName,int productPrice) {
        this.productName = productName;
        this.productPrice=productPrice;
    }

    public static ArrayList<Product> generateProducts()
    {
        ArrayList<Product> Products=new ArrayList<>();
        Products.add(new Product("Samsung Galaxy S10",3000));
        Products.add(new Product("Samsung Galaxy S20",4500));
        Products.add(new Product("Samsung Galaxy S20 Ultra",7500));
        Products.add(new Product("Huawei P30 PRO",3000));
        Products.add(new Product("Apple Iphone 11",3800));
        Products.add(new Product("Apple Iphone 11 PRO",5500));
        Products.add(new Product("OnePLus 7",2800));
        Products.add(new Product("OnePlus 7T",3500));
        Products.add(new Product("Apple Iphone Xs",4000));
        Products.add(new Product("Huawei Mate 30 PRO",3700));
        Products.add(new Product("Xiaomi MI 8 PRO",3000));
        Products.add(new Product("Samsung Galaxy S9",2700));
        Products.add(new Product("Huawei P20 PRO",2300));
        Products.add(new Product("Apple Iphone XR",3200));
        return Products;

    }
}
