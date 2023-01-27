package com.vsantos1.services;

import com.vsantos1.dtos.GameDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.Game;
import com.vsantos1.models.GameMap;
import com.vsantos1.repositories.GameMapRepository;
import com.vsantos1.repositories.GameRepository;
import com.vsantos1.services.gateways.GameGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService implements GameGateway {

    private final GameRepository gameRepository;
    private final GameMapRepository gameMapRepository;

    public GameService(GameRepository gameRepository, GameMapRepository gameMapRepository) {
        this.gameRepository = gameRepository;
        this.gameMapRepository = gameMapRepository;
    }

    public List<GameMap> findMapsByGameId(Long id) {
        return gameMapRepository.findGameMapByGameId(id);
    }

    @Override
    public Page<Game> findAllOrQueryPaginated(String name, Pageable pageable) {

        if (name == null) {
            return this.gameRepository.findAll(pageable);
        }

        return this.gameRepository.findAllByNameContainingIgnoreCase(name, pageable);

    }

    @Override
    public Game execute(GameDTO gameDTO) {
        Game game = new Game();

        Mapper.copyProperties(gameDTO, game);
        return gameRepository.save(game);
    }

    @Override
    public Game update(Long id, GameDTO gameDTO) {
        Game game = this.findById(id);
        Mapper.copyProperties(gameDTO, game);

        return gameRepository.save(game);
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isEmpty()) {
            throw new ResourceNotFoundException("No record found for this id");
        }
        return gameOptional.get();

    }

    @Override
    public void deleteById(Long id) {

        Game game = this.findById(id);

        gameRepository.deleteById(game.getId());

    }
}
