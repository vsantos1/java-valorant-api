package com.vsantos1.services;

import com.vsantos1.dtos.GameDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.Game;
import com.vsantos1.repositories.GameRepository;
import com.vsantos1.services.gateways.GameGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService implements GameGateway {

    public final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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
