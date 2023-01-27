package com.vsantos1.services;

import com.vsantos1.dtos.GameDTO;
import com.vsantos1.models.Game;
import com.vsantos1.repositories.GameRepository;
import com.vsantos1.services.gateways.GameGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

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
        return null;
    }

    @Override
    public Game update(Long id, GameDTO gameDTO) {
        return null;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
