package com.vsantos1.repositories;

import com.vsantos1.models.GameMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameMapRepository extends JpaRepository<GameMap, Long> {

    List<GameMap> findGameMapByGame_Name(String name);
}
