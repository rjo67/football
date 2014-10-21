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
import org.rjo.footy.web.domain.GameInfo;
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
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AddGameIntegrationTest {

   private static final String MATCH_DATE = "2014-10-21";

   private static final String OPPONENT = "Lieth";

   private static final String POST_CODE = "90210";

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

   // @Test
   // public void thatRedirectsToOrderOnSuccess() throws Exception {
   // UUID id = UUID.randomUUID();
   //
   // when(orderService.createOrder(any(CreateOrderEvent.class))).thenReturn(newOrder(id));
   //
   // mockMvc
   // .perform(
   // post("/checkout").param("name", CUSTOMER_NAME).param("address1", ADDRESS1)
   // .param("postcode", POST_CODE)).andExpect(status().isMovedTemporarily())
   // .andExpect(redirectedUrl("/order/" + id.toString()));
   // }
   //
   @Test
   public void thatSendsCorrectOrderEventOnSuccess() throws Exception {
      UUID id = UUID.randomUUID();

      when(gameService.add(any(CreateGameEvent.class))).thenReturn(newGame(id));

      mockMvc.perform(post("/addgame").param("date", MATCH_DATE).param("opponent", OPPONENT)).andDo(print());

      //@formatter:off
        verify(gameService).add(Matchers.<CreateGameEvent>argThat(
            allOf(
                org.hamcrest.Matchers.<CreateGameEvent>hasProperty("gameInfo",
                                                        hasProperty("opponent", equalTo(OPPONENT))),

                org.hamcrest.Matchers.<CreateGameEvent>hasProperty("gameInfo",
                                                        hasProperty("date", equalTo(MATCH_DATE)))
            )));
      //@formatter:on
   }

   @Test
   public void thatReturnsToAddGameIfValidationFail() throws Exception {
      mockMvc.perform(post("/addgame").param("date", MATCH_DATE)).andExpect(forwardedUrl(ADDGAME_VIEW));
   }

   private CreatedGameEvent newGame(UUID id) {
      GameInfo gi = new GameInfo();
      gi.setDate(MATCH_DATE);
      gi.setOpponent(OPPONENT);
      return new CreatedGameEvent(true, id, gi);
   }
}
