package org.rjo.footy.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.AllGamesEvent;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.events.game.RequestAllGamesEvent;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SiteIntegrationTest {

   private static final String RESPONSE_BODY = "Lieth,Holm";

   MockMvc mockMvc;

   @InjectMocks
   SiteController controller;

   @Mock
   GameService gameService;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      mockMvc = standaloneSetup(controller).build();

      List<GameDetails> gd = new ArrayList<>();
      gd.add(new GameDetails(UUID.randomUUID().toString(), "2014-02-10", "Lieth"));
      gd.add(new GameDetails(UUID.randomUUID().toString(), "2014-04-06", "Holm"));
      AllGamesEvent allGamesEvent = new AllGamesEvent(gd);
      when(gameService.requestAllGames(any(RequestAllGamesEvent.class))).thenReturn(allGamesEvent);

   }

   @Test
   public void thatTextReturned() throws Exception {
      mockMvc.perform(get("/")).andDo(print()).andExpect(content().string(RESPONSE_BODY));

   }

}
