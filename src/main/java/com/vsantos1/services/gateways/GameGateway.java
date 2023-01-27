package com.vsantos1.services.gateways;

import com.vsantos1.dtos.GameDTO;
import com.vsantos1.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GameGateway {

    Page<Game> findAllOrQueryPaginated(String name, Pageable pageable);

    Game execute(GameDTO gameDTO);

    Game update(Long id, GameDTO gameDTO);

    Game findById(Long id);

    void deleteById(Long id);

}
