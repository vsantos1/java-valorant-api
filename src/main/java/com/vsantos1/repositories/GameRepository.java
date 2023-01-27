package com.vsantos1.repositories;

import com.vsantos1.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {


    Page<Game> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
