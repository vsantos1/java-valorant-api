package com.vsantos1.resources;

import com.github.slugify.Slugify;
import com.vsantos1.dtos.PixelDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.models.Pixel;
import com.vsantos1.models.User;
import com.vsantos1.repositories.UserRepository;
import com.vsantos1.services.PixelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class PixelResource {

    private final PixelService pixelService;


    public PixelResource(PixelService pixelService) {
        this.pixelService = pixelService;
    }

    @GetMapping(value = "/pixels")
    public ResponseEntity<Page<Pixel>> getAllPaginated(@PageableDefault() Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.findAllPaginated(pageable));
    }

    @GetMapping(value = "/pixels/{pixel_id}")
    public ResponseEntity<Pixel> getById(@PathVariable("pixel_id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.findById(id));
    }


    @PostMapping(value = "/pixels")
    public ResponseEntity<Pixel> create(@RequestBody @Valid PixelDTO pixelDTO, @RequestHeader String authorization) {
        final Slugify slug = Slugify.builder().locale(Locale.ENGLISH).build();

        pixelDTO.setSlug(slug.slugify(pixelDTO.getTitle()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pixelService.execute(pixelDTO, authorization));
    }

    @PutMapping(value = "/pixels/{pixel_id}")
    public ResponseEntity<Pixel> update(@PathVariable("pixel_id") UUID id, @RequestBody PixelDTO pixelDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(pixelService.update(id, pixelDTO));
    }
    @DeleteMapping(value = "/pixels/{pixel_id}")
    public ResponseEntity<Void> delete(@PathVariable("pixel_id") UUID id) {
        pixelService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
