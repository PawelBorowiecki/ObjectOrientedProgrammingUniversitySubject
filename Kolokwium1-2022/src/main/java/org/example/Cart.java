package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<Product> products = new ArrayList<>();

    public Cart(List<Product> products) {
        this.products = products;
    }

    public Cart() {
    }

    public void addProduct(Product product, int amount){
        for(int i = 0; i < amount; i++){
            products.add(product);
        }
    }

    public double getPrice(int year, int month){
        int price = 0;
        for(Product p : products){
            price += p.getPrice(year, month);
        }
        return price;
    }

    public double getInflation(int year1, int month1, int year2, int month2){
        return (this.getPrice(year2,month2) - this.getPrice(year1, month1)) / this.getPrice(year1, month1) * 100 / (Math.abs(month2-month1)) * 12;
    }
}
