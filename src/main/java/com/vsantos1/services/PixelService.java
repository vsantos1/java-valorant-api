package com.vsantos1.services;

import com.github.slugify.Slugify;
import com.vsantos1.dtos.PixelDTO;
import com.vsantos1.enums.Role;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.exceptions.UserNotAllowedException;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.Agent;
import com.vsantos1.models.GameMap;
import com.vsantos1.models.Pixel;
import com.vsantos1.models.User;
import com.vsantos1.repositories.PixelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class PixelService {

    private final PixelRepository pixelRepository;
    private final GameMapService gameMapService;

    private final AgentService agentService;


    private final UserService userService;

    public PixelService(PixelRepository pixelRepository, GameMapService gameMapService, AgentService agentService, UserService userService) {
        this.pixelRepository = pixelRepository;
        this.gameMapService = gameMapService;
        this.agentService = agentService;

        this.userService = userService;
    }

    public void  updatePixelViews(UUID id){
        Pixel pixel = this.findById(id);
        pixel.setViews(pixel.getViews() + 1);
        pixelRepository.save(pixel);
    }
    public Page<Pixel> findAllPaginated(Pageable pageable) {
        return pixelRepository.findAll(pageable);
    }

    public Pixel execute(PixelDTO pixelDTO, String authorization) {
        Agent agent = this.agentService.findById(pixelDTO.getAgent().getId());
        GameMap map = this.gameMapService.findById(pixelDTO.getGameMap().getId());

        if (countByUser(authorization) >= 10 && userService.isUser(authorization)) {
            throw new UserNotAllowedException("You have reached the limit of 10 pixels");
        }
        if (countByUser(authorization) >= 50 && userService.isSupporter(authorization)) {
            throw new UserNotAllowedException("You have reached the limit of 50 pixels");
        }

        if (alreadyExists(pixelDTO.getSlug())) {
            throw new UserNotAllowedException("Slug already exists");
        }
        Pixel pixel = new Pixel();


        pixelDTO.setCreatedAt(new Date());
        User user = userService.loadUserByToken(authorization);

        // If user is not admin, set isVerified will start as false
        if (user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals(Role.ROLE_ADMIN.name()))) {
            pixelDTO.setVerified(true);
        }
        pixelDTO.setVerified(false);
        pixelDTO.setUser(user);
        pixelDTO.setGameMap(map);
        pixelDTO.setAgent(agent);
        Mapper.copyProperties(pixelDTO, pixel);
        return pixelRepository.save(pixel);
    }

    public boolean createdByMe(UUID id, String authorization) {
        User user = userService.loadUserByToken(authorization);
        Pixel pixel = this.findById(id);
        return pixel.getUser().getId().equals(user.getId());
    }

    public Page<Pixel> findPixelsCreatedByUser(String token, Pageable pageable) {
        User user = userService.loadUserByToken(token);
        return pixelRepository.findPixelsByUser(user, pageable);
    }

    public Pixel findBySlug(String slug) {
        return pixelRepository.findBySlug(slug).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public Pixel findById(UUID id) {
        Optional<Pixel> optionalPixel = pixelRepository.findById(id);
        if (optionalPixel.isPresent()) {
            return optionalPixel.get();
        }
        throw new ResourceNotFoundException("No records found for this ID");

    }

    public Integer countByUser(String token) {
        User user = userService.loadUserByToken(token);
        return pixelRepository.countByUser(user);
    }


    public boolean alreadyExists(String slug) {
        return pixelRepository.existsBySlug(slug);
    }

    public Pixel updatePixelCreatedByUser(UUID id, PixelDTO pixelDTO, String authorization) {
        Slugify slug = Slugify.builder().locale(Locale.ENGLISH).build();
        String slugString = slug.slugify(pixelDTO.getTitle());
        if (!alreadyExists(slugString) && pixelDTO.getTitle() != null) {
            pixelDTO.setSlug(slugString);

        }

        if (!createdByMe(id, authorization)) {
            throw new UserNotAllowedException("You are not allowed to update this pixel");
        }


        Pixel pixel = this.findById(id);
        pixelDTO.setVerified(false);
        Mapper.copyProperties(pixelDTO, pixel);
        return pixelRepository.save(pixel);

    }

    public Pixel update(UUID id, PixelDTO pixelDTO) {
        Slugify slug = Slugify.builder().locale(Locale.ENGLISH).build();
        String slugString = slug.slugify(pixelDTO.getTitle());
        if (!alreadyExists(slugString) && pixelDTO.getTitle() != null) {
            pixelDTO.setSlug(slugString);

        }


        Pixel pixel = this.findById(id);

        pixelDTO.setUpdatedAt(new Date());
        Mapper.copyProperties(pixelDTO, pixel);

        return pixelRepository.save(pixel);
    }


    public void deleteById(UUID id) {
        Pixel pixel = this.findById(id);
        pixelRepository.deleteById(pixel.getId());
    }

    public void deleteByUser(UUID id, String authorization) {

        if (createdByMe(id, authorization)) {
            this.deleteById(id);
        }
        throw new UserNotAllowedException("You are not allowed to delete this pixel");
    }
}
