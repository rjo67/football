package org.rjo.footy.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rjo.footy.config.CoreConfig;
import org.rjo.footy.config.PersistenceConfig;
import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.RequestAllGamesEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CoreConfig.class, PersistenceConfig.class })
public class CoreIntegrationTest {

	@Autowired
	GameService gameService;

	@Test
	public void thatAllGamesReturned() {
		assertEquals(2, gameService.requestAllGames(new RequestAllGamesEvent())
				.getGameDetails().size());
	}

}
