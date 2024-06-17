package org.example;

import java.util.List;

public class AmbigiousProductException extends Exception{
    public AmbigiousProductException(List<String> list) {
        list.forEach(System.out::println);
    }
}
