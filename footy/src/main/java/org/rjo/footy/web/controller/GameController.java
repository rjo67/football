package org.rjo.footy.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.Valid;

import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.game.CreateGameEvent;
import org.rjo.footy.events.game.CreatedGameEvent;
import org.rjo.footy.events.game.GameDetails;
import org.rjo.footy.web.domain.GameInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addgame")
public class GameController {

   private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

   @Autowired
   private GameService gameService;

   @RequestMapping(method = RequestMethod.GET)
   public String addGame() {
      return "/addgame";
   }

   @RequestMapping(method = RequestMethod.POST)
   public String addGameSubmit(@Valid @ModelAttribute("gameInfo") GameInfo gameInfo, BindingResult bindingResult) {
      // TODO should do this with javax.validation
      try {
         LocalDate.parse(gameInfo.getDate());
      } catch (DateTimeParseException x) {
         bindingResult.addError(new FieldError("gameInfo", "date", "could not parse date"));
      }
      if (bindingResult.hasErrors()) {
         return "/addgame";
      }
      // call business logic
      GameDetails gameDetails = new GameDetails();
      BeanUtils.copyProperties(gameInfo, gameDetails);
      CreatedGameEvent event = gameService.add(new CreateGameEvent(gameDetails));

      // everything ok
      LOG.debug("game added ok; redirecting");
      return "redirect:/game/" + event.getGameKey().toString();
   }

   /**
    * From the Spring-Doku:
    * 
    * The ModelAttribute on the POST method above and this method declares the
    * GameInfo class to be a Command Object. When the page is rendered for the
    * first time on a GET /addgame, the method getGameInfo is called to generate
    * the 'gameInfo' property in the model. This property is then available in
    * the model for the View to use during rendering.
    * 
    * Using GameInfo as a parameter means that Spring will perform Binding of
    * the request parameters against it. If the binding did not complete
    * successfully, then the result is stored in the BindingResult parameter.
    * 
    * The @Valid annotation on the CustomerInfo indicates to Spring that this
    * instance should be validated. This will use the annotations on the fields.
    * If the fields all pass the validation rules, then the bean is deemed to be
    * valid, if not then it is invalid and the binding will fail.
    * 
    * @return a GameInfo for the form
    */
   @ModelAttribute("gameInfo")
   private GameInfo getGameInfo() {
      return new GameInfo();
   }
}
