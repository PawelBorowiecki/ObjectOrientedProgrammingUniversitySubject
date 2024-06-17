package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Product {
    private String name;
    private static List<Product> products = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice(int year, int month);

    public static void clearProducts(){
        products.clear();
    }

    public static void addProducts(Function<Path,?> function, Path filePath){
        products.add((Product) function.apply(filePath));
    }

    public static void getProducts(String prefix) throws IndexOutOfBoundsException, AmbigiousProductException{
        int counter = 0;
        List<String> foundProducts = new ArrayList<>();
        for(Product p : products){
            if(prefix.equals(p.getName().split(prefix, 1))){
                foundProducts.add(p.getName());
                counter ++;
            }
        }
        if(counter == 0){
            throw new IndexOutOfBoundsException(prefix + " nie wskazuje na zadsen obiekt");
        }else if(counter == 1){
            System.out.println(foundProducts.get(0));
        }else{
            throw new AmbigiousProductException(foundProducts);
        }
    }

    public static void showProducts(){
        for(Product p : products){
            System.out.println(p.name);
        }
    }
}