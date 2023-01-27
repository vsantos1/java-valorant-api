package com.vsantos1.resources;

import com.vsantos1.dtos.GameMapDTO;
import com.vsantos1.models.Game;
import com.vsantos1.models.GameMap;
import com.vsantos1.services.GameMapService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class GameMapResource {

    private final GameMapService gameMapService;

    public GameMapResource(GameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    @GetMapping(value = "/game-map")
    public ResponseEntity<Page<GameMap>> findAll(@PageableDefault() Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(gameMapService.findAll(pageable));
    }


    @GetMapping(value = "/game-map/{map_id}")
    public ResponseEntity<GameMap> findById(@PathVariable("map_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gameMapService.findById(id));
    }

    @PostMapping(value = "/game-map")
    public ResponseEntity<GameMap> execute(@RequestBody @Valid GameMapDTO gameMapDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameMapService.execute(gameMapDTO));
    }

    @PutMapping(value = "/game-map/{map_id}")
    public ResponseEntity<GameMap> update(@PathVariable("map_id") Long id, @RequestBody @Valid GameMapDTO gameMapDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(gameMapService.update(id, gameMapDTO));
    }

    @DeleteMapping(value = "/game-map/{map_id}")
    public ResponseEntity<GameMap> delete(@PathVariable("map_id") Long id) {
        gameMapService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
