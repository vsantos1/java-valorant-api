package com.vsantos1.repositories;

import com.vsantos1.models.GameMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameMapRepository extends JpaRepository<GameMap, Long> {
}
