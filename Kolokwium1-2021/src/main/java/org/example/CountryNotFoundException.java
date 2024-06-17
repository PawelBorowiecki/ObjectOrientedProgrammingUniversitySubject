package org.example;

public class CountryNotFoundException extends Throwable{
    public String getMessage(Country country) {
        return country.getName();
    }
}
