package org.example.frontendtest1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HistoricalBuilding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private int yearOfConstruction;
    private String description;

    // Конструкторы
    public HistoricalBuilding() {
    }

    public HistoricalBuilding(String name, String city, int yearOfConstruction, String description) {
        this.name = name;
        this.city = city;
        this.yearOfConstruction = yearOfConstruction;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public int getYearOfConstruction() {
        return yearOfConstruction;
    }
    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setYearOfConstruction(int yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

