package org.example.frontendtest1.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HistoricalBuilding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private int yearOfConstruction;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Новое поле: список ссылок на фотографии
    @ElementCollection
    @CollectionTable(name = "building_photos", joinColumns = @JoinColumn(name = "building_id"))
    @Column(name = "photo_url")
    private List<String> photoUrls = new ArrayList<>();

    // Конструкторы
    public HistoricalBuilding() {
    }

    public HistoricalBuilding(String name, String city, int yearOfConstruction, String description) {
        this.name = name;
        this.city = city;
        this.yearOfConstruction = yearOfConstruction;
        this.description = description;
    }

    public HistoricalBuilding(String name, String city, int yearOfConstruction, String description, List<String> photoUrls) {
        this.name = name;
        this.city = city;
        this.yearOfConstruction = yearOfConstruction;
        this.description = description;
        this.photoUrls = photoUrls;
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

    public List<String> getPhotoUrls() {
        return photoUrls;
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

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    // Удобные методы для работы с photoUrls
    public void addPhotoUrl(String photoUrl) {
        this.photoUrls.add(photoUrl);
    }

    public void removePhotoUrl(String photoUrl) {
        this.photoUrls.remove(photoUrl);
    }
}