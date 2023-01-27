package com.vsantos1.services;

import com.vsantos1.dtos.GameDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.GameMap;
import com.vsantos1.repositories.GameMapRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameMapService {

    private final GameMapRepository gameMapRepository;

    public GameMapService(GameMapRepository gameMapRepository) {
        this.gameMapRepository = gameMapRepository;
    }

    public GameMap execute(GameDTO gameDTO) {
        GameMap gameMap = new GameMap();
        Mapper.copyProperties(gameDTO, gameMap);

        return gameMapRepository.save(gameMap);
    }

    public GameMap findById(Long id) {
        Optional<GameMap> gameMapOptional = gameMapRepository.findById(id);
        if (gameMapOptional.isEmpty()) {
            throw new ResourceNotFoundException("No record found for this id");
        }
        return gameMapOptional.get();
    }

    public GameMap update(Long id, GameDTO gameDTO) {
        GameMap gameMap = this.findById(id);
        return gameMapRepository.save(gameMap);
    }

    public void delete(Long id) {
        gameMapRepository.deleteById(id);
    }
}
