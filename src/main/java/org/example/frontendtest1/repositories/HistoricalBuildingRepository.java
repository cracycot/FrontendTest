package org.example.frontendtest1.repositories;

import org.example.frontendtest1.models.HistoricalBuilding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricalBuildingRepository extends JpaRepository<HistoricalBuilding, Long> {
}