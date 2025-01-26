package org.example.frontendtest1.controllers;

import org.example.frontendtest1.models.HistoricalBuilding;
import org.example.frontendtest1.repositories.HistoricalBuildingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class HistoricalBuildingController {

    private final HistoricalBuildingRepository repository;

    public HistoricalBuildingController(HistoricalBuildingRepository repository) {
        this.repository = repository;
    }

    // Получить все здания
    @GetMapping
    public List<HistoricalBuilding> getAllBuildings() {
        return repository.findAll();
    }

    // Добавить новое здание
    @PostMapping
    public ResponseEntity<HistoricalBuilding> addBuilding(@RequestBody HistoricalBuilding newBuilding) {
        HistoricalBuilding saved = repository.save(newBuilding);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Получить здание по ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoricalBuilding> getBuildingById(@PathVariable Long id) {
        return repository.findById(id)
                .map(building -> new ResponseEntity<>(building, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Обновить здание по ID
    @PutMapping("/{id}")
    public ResponseEntity<HistoricalBuilding> updateBuilding(
            @PathVariable Long id,
            @RequestBody HistoricalBuilding updated) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setCity(updated.getCity());
                    existing.setYearOfConstruction(updated.getYearOfConstruction());
                    existing.setDescription(updated.getDescription());
                    HistoricalBuilding saved = repository.save(existing);
                    return new ResponseEntity<>(saved, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Удалить здание
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        return repository.findById(id)
                .map(existing -> {
                    repository.delete(existing);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}