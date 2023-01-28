package com.vsantos1.repositories;

import com.vsantos1.models.Pixel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PixelRepository extends JpaRepository<Pixel, UUID> {
}
