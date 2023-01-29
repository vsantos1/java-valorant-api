package com.vsantos1.resources;

import com.vsantos1.dtos.GameDTO;
import com.vsantos1.models.Game;
import com.vsantos1.models.GameMap;
import com.vsantos1.services.GameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class GameResource {

    private final GameService gameService;


    public GameResource(GameService gameService) {
        this.gameService = gameService;

    }

    @GetMapping(value = "/games")
    public ResponseEntity<Page<Game>> getAllPaginated(@PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false) String name) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAllOrQueryPaginated(name, pageable));
    }

    @GetMapping(value = "/games/{game_name}/maps")
    public ResponseEntity<List<GameMap>> getGameMaps(@PathVariable("game_name") String gameName) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findMapsByGameId(gameName));
    }

    @GetMapping(value = "/games/{game_id}")
    public ResponseEntity<Game> findById(@PathVariable("game_id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findById(id));
    }
    @PostMapping(value = "/games")
    public ResponseEntity<Game> create(@RequestBody @Valid GameDTO gameDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.execute(gameDTO));
    }

    @PutMapping(value = "/games/{game_id}")
    public ResponseEntity<Game> update(@PathVariable("game_id") Long id, @RequestBody GameDTO gameDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.update(id, gameDTO));
    }

    @DeleteMapping(value = "/games/{game_id}")
    public ResponseEntity<Void> delete(@PathVariable("game_id") Long id) {
        gameService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
