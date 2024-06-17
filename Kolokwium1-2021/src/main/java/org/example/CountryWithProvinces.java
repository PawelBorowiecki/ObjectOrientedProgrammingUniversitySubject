package org.example;

import java.nio.file.Path;
import java.time.LocalDate;

public class CountryWithProvinces extends Country{
    private int deathsCount = 0;
    private int infectionCount = 0;
    private Country[] countries;

    public CountryWithProvinces(String name, Country[] countries) {
        super(name);
        this.countries = countries;
    }

    @Override
    public int getConfirmedCases(LocalDate date) {
        return this.infectionCount;
    }

    @Override
    public int getDeaths(LocalDate date) {
        return this.deathsCount;
    }
}
