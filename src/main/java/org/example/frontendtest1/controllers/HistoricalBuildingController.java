package org.example.frontendtest1.controllers;

import org.example.frontendtest1.models.HistoricalBuilding;
import org.example.frontendtest1.repositories.HistoricalBuildingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buildings")
@CrossOrigin(origins = "http://localhost:3000") // Добавлено для поддержки CORS
public class HistoricalBuildingController {

    private final HistoricalBuildingRepository repository;

    public HistoricalBuildingController(HistoricalBuildingRepository repository) {
        this.repository = repository;
    }

    // Получить все здания
    @GetMapping
    public ResponseEntity<List<HistoricalBuilding>> getAllBuildings() {
        List<HistoricalBuilding> buildings = repository.findAll();
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    // Добавить новое здание
    @PostMapping
    public ResponseEntity<HistoricalBuilding> addBuilding(@RequestBody HistoricalBuilding newBuilding) {
        HistoricalBuilding savedBuilding = repository.save(newBuilding);
        return new ResponseEntity<>(savedBuilding, HttpStatus.CREATED);
    }

    // Получить здание по ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoricalBuilding> getBuildingById(@PathVariable Long id) {
        Optional<HistoricalBuilding> buildingOpt = repository.findById(id);
        return buildingOpt
                .map(building -> new ResponseEntity<>(building, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Обновить здание по ID
    @PutMapping("/{id}")
    public ResponseEntity<HistoricalBuilding> updateBuilding(
            @PathVariable Long id,
            @RequestBody HistoricalBuilding updatedBuilding) {
        Optional<HistoricalBuilding> buildingOpt = repository.findById(id);
        if (buildingOpt.isPresent()) {
            HistoricalBuilding existingBuilding = buildingOpt.get();
            existingBuilding.setName(updatedBuilding.getName());
            existingBuilding.setCity(updatedBuilding.getCity());
            existingBuilding.setYearOfConstruction(updatedBuilding.getYearOfConstruction());
            existingBuilding.setDescription(updatedBuilding.getDescription());
            existingBuilding.setPhotoUrls(updatedBuilding.getPhotoUrls()); // Обновление списка фотографий

            HistoricalBuilding savedBuilding = repository.save(existingBuilding);
            return new ResponseEntity<>(savedBuilding, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Частичное обновление (PATCH) здания по ID
    @PatchMapping("/{id}")
    public ResponseEntity<HistoricalBuilding> partiallyUpdateBuilding(
            @PathVariable Long id,
            @RequestBody HistoricalBuilding partialUpdate) {
        Optional<HistoricalBuilding> buildingOpt = repository.findById(id);
        if (buildingOpt.isPresent()) {
            HistoricalBuilding existingBuilding = buildingOpt.get();

            if (partialUpdate.getName() != null) {
                existingBuilding.setName(partialUpdate.getName());
            }
            if (partialUpdate.getCity() != null) {
                existingBuilding.setCity(partialUpdate.getCity());
            }
            if (partialUpdate.getYearOfConstruction() != 0) { // Предполагается, что 0 не является допустимым годом
                existingBuilding.setYearOfConstruction(partialUpdate.getYearOfConstruction());
            }
            if (partialUpdate.getDescription() != null) {
                existingBuilding.setDescription(partialUpdate.getDescription());
            }
            if (partialUpdate.getPhotoUrls() != null) {
                existingBuilding.setPhotoUrls(partialUpdate.getPhotoUrls());
            }

            HistoricalBuilding savedBuilding = repository.save(existingBuilding);
            return new ResponseEntity<>(savedBuilding, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить здание
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        Optional<HistoricalBuilding> buildingOpt = repository.findById(id);
        if (buildingOpt.isPresent()) {
            repository.delete(buildingOpt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Дополнительные методы для управления фотографиями (по желанию)

    /**
     * Добавить ссылку на фотографию к зданию
     */
    @PostMapping("/{id}/photos")
    public ResponseEntity<HistoricalBuilding> addPhotoUrl(
            @PathVariable Long id,
            @RequestBody String photoUrl) {
        Optional<HistoricalBuilding> buildingOpt = repository.findById(id);
        if (buildingOpt.isPresent()) {
            HistoricalBuilding building = buildingOpt.get();
            building.addPhotoUrl(photoUrl);
            repository.save(building);
            return new ResponseEntity<>(building, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удалить ссылку на фотографию из здания
     */
    @DeleteMapping("/{id}/photos")
    public ResponseEntity<HistoricalBuilding> removePhotoUrl(
            @PathVariable Long id,
            @RequestBody String photoUrl) {
        Optional<HistoricalBuilding> buildingOpt = repository.findById(id);
        if (buildingOpt.isPresent()) {
            HistoricalBuilding building = buildingOpt.get();
            building.removePhotoUrl(photoUrl);
            repository.save(building);
            return new ResponseEntity<>(building, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}