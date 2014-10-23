package org.rjo.footy.web.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.CreateGameEvent;
import org.rjo.footy.events.game.CreatedGameEvent;
import org.rjo.footy.events.game.GameDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AddGameIntegrationTest {

   private static final String MATCH_DATE = "2014-10-21";

   private static final String OPPONENT = "Lieth";

   private static final String ADDGAME_VIEW = "/WEB-INF/views/addgame.html";

   MockMvc mockMvc;

   @InjectMocks
   GameController controller;

   @Mock
   GameService gameService;

   @Before
   public void setup() {

      MockitoAnnotations.initMocks(this);
      mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver()).build();
   }

   private InternalResourceViewResolver viewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/views");
      viewResolver.setSuffix(".html");
      return viewResolver;
   }

   @Test
   public void thatGameIsPopulated() throws Exception {
      mockMvc.perform(get("/addgame")).andExpect(model().attributeExists("gameInfo"));
   }

   @Test
   public void thatAddGameViewIsCorrect() throws Exception {
      mockMvc.perform(get("/addgame")).andExpect(forwardedUrl(ADDGAME_VIEW));
   }

   @Test
   public void thatRedirectsToGameOnSuccess() throws Exception {
      UUID id = UUID.randomUUID();

      when(gameService.add(any(CreateGameEvent.class))).thenReturn(newGame(id));

      mockMvc.perform(post("/addgame").param("date", MATCH_DATE).param("opponent", OPPONENT))
            .andExpect(status().isMovedTemporarily()).andExpect(redirectedUrl("/game/" + id.toString()));
   }

   @Test
   public void thatSendsCorrectGameEventOnSuccess() throws Exception {
      UUID id = UUID.randomUUID();

      when(gameService.add(any(CreateGameEvent.class))).thenReturn(newGame(id));

      mockMvc.perform(post("/addgame").param("date", MATCH_DATE).param("opponent", OPPONENT)).andDo(print());

      verify(gameService).add(
            Matchers.<CreateGameEvent> argThat(allOf(org.hamcrest.Matchers.<CreateGameEvent> hasProperty("game",
                  hasProperty("opponent", equalTo(OPPONENT))),

            org.hamcrest.Matchers.<CreateGameEvent> hasProperty("game", hasProperty("date", equalTo(MATCH_DATE))))));
   }

   @Test
   public void thatReturnsToAddGameIfValidationFail() throws Exception {
      mockMvc.perform(post("/addgame").param("date", MATCH_DATE)).andExpect(forwardedUrl(ADDGAME_VIEW));
   }

   private CreatedGameEvent newGame(UUID id) {
      GameDetails game = new GameDetails();
      game.setDate(MATCH_DATE);
      game.setOpponent(OPPONENT);
      return new CreatedGameEvent(id, game);
   }
}
