package org.rjo.footy.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.rjo.footy.core.domain.Game;
import org.rjo.footy.persistence.GameMemoryRepository;
import org.rjo.footy.persistence.GameRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config for 'persistence'.
 */
@Configuration
public class PersistenceConfig {

   @Bean
   public GameRepository gameRepository() {
      return new GameMemoryRepository(defaultGames());
   }

   private List<Game> defaultGames() {
      List<Game> defaultGames = new ArrayList<>();
      defaultGames.add(new Game(LocalDate.parse("2014-02-10"), "Lieth"));
      defaultGames.add(new Game(LocalDate.parse("2014-04-06"), "Holm"));
      return defaultGames;
   }
}
