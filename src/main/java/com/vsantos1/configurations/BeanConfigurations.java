package com.vsantos1.configurations;

import com.vsantos1.repositories.GameRepository;
import com.vsantos1.services.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigurations {
    @Bean
    public GameService gameServiceBean(GameRepository gameRepository) {
        return new GameService(gameRepository);
    }

}
