package com.vsantos1.services;

import com.vsantos1.dtos.GameMapDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.Game;
import com.vsantos1.models.GameMap;
import com.vsantos1.repositories.GameMapRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameMapService {

    private final GameMapRepository gameMapRepository;
    private final GameService gameService;

    public GameMapService(GameMapRepository gameMapRepository, GameService gameService) {
        this.gameMapRepository = gameMapRepository;
        this.gameService = gameService;
    }

    public GameMap execute(GameMapDTO gameMapDTO) {
        Game game = gameService.findById(gameMapDTO.getGame().getId());
        gameMapDTO.setGame(game);
        GameMap gameMap = new GameMap();
        Mapper.copyProperties(gameMapDTO, gameMap);

        return gameMapRepository.save(gameMap);
    }

    public GameMap findById(Long id) {
        Optional<GameMap> gameMapOptional = gameMapRepository.findById(id);
        if (gameMapOptional.isEmpty()) {
            throw new ResourceNotFoundException("No record found for this id");
        }
        return gameMapOptional.get();
    }

    public GameMap update(Long id, GameMapDTO gameMapDTO) {
        GameMap gameMap = this.findById(id);
        Mapper.copyProperties(gameMapDTO, gameMap);
        return gameMapRepository.save(gameMap);
    }

    public void delete(Long id) {
        gameMapRepository.deleteById(id);
    }

    public Page<GameMap> findAll(Pageable pageable) {
        return gameMapRepository.findAll(pageable);
    }
}
