package org.rjo.footy.web.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.events.game.RequestGameDetailsEvent;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class GameStatusIntegrationTest {

   private static final String GAME_VIEW = "/WEB-INF/views/game.html";

   private static UUID uuid;

   MockMvc mockMvc;

   @InjectMocks
   GameStatusController controller;

   @Mock
   GameService gameService;

   @Before
   public void setup() {
      MockitoAnnotations.initMocks(this);

      mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver()).build();
      uuid = UUID.randomUUID();
   }

   private InternalResourceViewResolver viewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/views");
      viewResolver.setSuffix(".html");
      return viewResolver;
   }

   @Test
   public void thatGameViewIsForwardedTo() throws Exception {

      when(gameService.requestGameDetails(any(RequestGameDetailsEvent.class))).thenReturn(gameDetailsEvent(uuid));

      mockMvc.perform(get("/game/" + uuid)).andExpect(status().isOk()).andExpect(forwardedUrl(GAME_VIEW));
   }

   private GameDetails gameDetailsEvent(UUID uuid) {
      GameDetails gd = new GameDetails(UUID.randomUUID().toString(), "2010-10-21", "Lieth");
      return gd;
   }

   @Test
   public void thatOrderStatusIsPutInModel() throws Exception {

      when(gameService.requestGameDetails(any(RequestGameDetailsEvent.class))).thenReturn(gameDetailsEvent(uuid));

      mockMvc.perform(get("/game/" + uuid)).andExpect(model().attributeExists("gameDetails"))
            .andExpect(model().attribute("gameDetails", hasProperty("date", equalTo("2010-10-21"))))
            .andExpect(model().attribute("gameDetails", hasProperty("opponent", equalTo("Lieth"))));

      verify(gameService).requestGameDetails(
            Matchers.<RequestGameDetailsEvent> argThat(org.hamcrest.Matchers.<RequestGameDetailsEvent> hasProperty(
                  "key", equalTo(uuid))));
   }

}
