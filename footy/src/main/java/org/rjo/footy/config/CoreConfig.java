package org.rjo.footy.config;

import org.rjo.footy.core.services.GameEventHandler;
import org.rjo.footy.core.services.GameService;
import org.rjo.footy.persistence.GameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring configuration for 'core'
 */
@Configuration
public class CoreConfig {

	@Bean
	public GameService gameService(GameRepository gameRepository) {
		return new GameEventHandler(gameRepository);
	}

}
