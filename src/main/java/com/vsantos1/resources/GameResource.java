package com.vsantos1.resources;

import com.vsantos1.models.Game;
import com.vsantos1.services.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class GameResource {

    private final GameService gameService;

    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/games")
    public ResponseEntity<Page<Game>> getAllPaginated(@PageableDefault(size = 10) Pageable pageable, @RequestParam() String name) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findAllOrQueryPaginated(name, pageable));
    }

    @GetMapping(value = "/games/{game_id")
    public ResponseEntity<Game> findById(@RequestParam("game_id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(gameService.findById(id));
    }

}
