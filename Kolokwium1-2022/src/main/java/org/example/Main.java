package org.example;

import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        NonFoodProduct nonFoodProduct = NonFoodProduct.fromCsv(Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1-2022\\data\\nonfood\\benzyna.csv"));
        System.out.println(nonFoodProduct.getPrice(2011, 5));

        FoodProduct foodProduct = FoodProduct.fromCsv(Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1-2022\\data\\food\\jablka.csv"));
        System.out.println(foodProduct.getPrice(2010, 10,"LUBELSKIE"));
        System.out.println(foodProduct.getPrice(2010, 10));

//        Product.addProducts(NonFoodProduct::fromCsv, Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1-2022\\data\\food\\jablka.csv"));
//        Product.addProducts(FoodProduct::fromCsv, Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1-2022\\data\\nonfood\\benzyna.csv"));

        try {
            Product.getProducts("Abc");
            Product.getProducts("Bu");
            Product.getProducts("Ja");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        Cart cart = new Cart(Product.addProducts(FoodProduct::fromCsv, Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1-2022\\data\\nonfood\\benzyna.csv")));
//        cart.getPrice(2012,6);
//        cart.getInflation(2011, 7, 2014, 12);
    }
}