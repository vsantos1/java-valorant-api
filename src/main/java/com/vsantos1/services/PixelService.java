package com.vsantos1.services;

import com.vsantos1.dtos.PixelDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.jwt.JwtService;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.Pixel;
import com.vsantos1.models.User;
import com.vsantos1.repositories.PixelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PixelService {

    private final PixelRepository pixelRepository;


    private final UserService userService;

    public PixelService(PixelRepository pixelRepository, UserService userService) {
        this.pixelRepository = pixelRepository;

        this.userService = userService;
    }

    public Page<Pixel> findAllPaginated(Pageable pageable) {
        return pixelRepository.findAll(pageable);
    }

    public Pixel execute(PixelDTO pixelDTO, String authorization) {
        Pixel pixel = new Pixel();


        pixelDTO.setCreatedAt(new Date());
        pixelDTO.setUser(userService.loadUserByToken(authorization));
        Mapper.copyProperties(pixelDTO, pixel);
        return pixelRepository.save(pixel);
    }


    public Pixel findById(UUID id) {
        Optional<Pixel> optionalPixel = pixelRepository.findById(id);
        if (optionalPixel.isPresent()) {
            return optionalPixel.get();
        }
        throw new ResourceNotFoundException("No records found for this ID");

    }

    public Pixel update(UUID id, PixelDTO pixelDTO) {
        Pixel pixel = this.findById(id);

        Mapper.copyProperties(pixelDTO, pixel);

        return pixelRepository.save(pixel);
    }

    public void deleteById(UUID id) {
        Pixel pixel = this.findById(id);
        pixelRepository.deleteById(pixel.getId());
    }
}
