package com.vsantos1.resources;

import com.github.slugify.Slugify;
import com.vsantos1.dtos.PixelDTO;
import com.vsantos1.models.Pixel;
import com.vsantos1.repositories.filter.PixelQueryFilter;
import com.vsantos1.services.PixelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class PixelResource {

    private final PixelService pixelService;


    public PixelResource(PixelService pixelService) {
        this.pixelService = pixelService;
    }

    @GetMapping(value = "/pixels")
    public ResponseEntity<Page<Pixel>> getAllPaginated(@PageableDefault() Pageable pageable,
                                                       @RequestParam(required = false) String title,@RequestParam(required = false) String description) {

        PixelQueryFilter query = new PixelQueryFilter(title, description);
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.findAllWithQueriesPaginated(query,pageable));
    }

    @GetMapping(value = "/pixels/verify")
    public ResponseEntity<Page<Pixel>> getAllNotVerified(@PageableDefault() Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.findAllNotVerified(pageable));
    }

    @GetMapping(value = "/pixels/{pixel_slug}")
    public ResponseEntity<Pixel> getById(@PathVariable("pixel_slug") String slug) {
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.findBySlug(slug));
    }


    @PostMapping(value = "/pixels")
    public ResponseEntity<Pixel> create(@RequestBody @Valid PixelDTO pixelDTO, @RequestHeader String authorization) {
        final Slugify slug = Slugify.builder().locale(Locale.ENGLISH).build();

        pixelDTO.setSlug(slug.slugify(pixelDTO.getTitle()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pixelService.execute(pixelDTO, authorization));
    }


    @GetMapping(value = "/pixels/me/count")
    public ResponseEntity<Integer> countByUser(@RequestHeader String authorization) {
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.countByUser(authorization));
    }
    @GetMapping(value = "/pixels/me")
    public ResponseEntity<Page<Pixel>> getPixelsCreatedByUser(@RequestHeader String authorization, @PageableDefault() Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(pixelService.findPixelsCreatedByUser(authorization, pageable));
    }

    @PutMapping(value = "/pixels/me/edit/{pixel_id}")
    public ResponseEntity<Pixel> updatePixelCreatedByUser(@PathVariable("pixel_id") UUID id, @RequestBody PixelDTO pixelDTO, @RequestHeader String authorization) {

        return ResponseEntity.status(HttpStatus.OK).body(pixelService.updatePixelCreatedByUser(id, pixelDTO, authorization));
    }

    @PutMapping(value = "/pixels/{pixel_id}/views")
    public ResponseEntity<Void> updatePixelViews(@PathVariable("pixel_id") UUID id) {
        pixelService.updatePixelViews(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping(value = "/pixels/edit/{pixel_id}")
    public ResponseEntity<Pixel> update(@PathVariable("pixel_id") UUID id, @RequestBody PixelDTO pixelDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(pixelService.update(id, pixelDTO));
    }

    @DeleteMapping(value = "/pixels/delete/{pixel_id}")
    public ResponseEntity<Void> delete(@PathVariable("pixel_id") UUID id) {
        pixelService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/pixels/me/{pixel_id}")
    public ResponseEntity<Void> deleteByUser(@PathVariable("pixel_id") UUID id, @RequestHeader String authorization) {
        pixelService.deleteByUser(id, authorization);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
