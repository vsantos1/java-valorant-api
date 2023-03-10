package com.vsantos1.repositories;

import com.vsantos1.models.Pixel;
import com.vsantos1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PixelRepository extends JpaRepository<Pixel, UUID> {

    Page<Pixel> findPixelsByUser(User user, Pageable pageable);

    boolean existsBySlug(String slug);

    Optional<Pixel> findBySlug(String slug);

    Integer countByUser(User user);

    Page<Pixel> findPixelsByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Pixel> findPixelsByDescriptionContainingIgnoreCase(String description, Pageable pageable);
    Page<Pixel> findPixelsByTitleAndDescriptionContainingIgnoreCase(String title, String description, Pageable pageable);
    Page<Pixel> findAllByIsVerifiedFalse(Pageable pageable);
    Page<Pixel> findPixelsByAgent_name(String name, Pageable pageable);

}
