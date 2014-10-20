package org.rjo.footy.web.controller;

import org.rjo.footy.core.services.GameService;
import org.rjo.footy.events.menu.AllGamesEvent;
import org.rjo.footy.events.menu.GameDetails;
import org.rjo.footy.events.menu.RequestAllGamesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class SiteController {

	private static final Logger LOG = LoggerFactory
			.getLogger(SiteController.class);

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getGames() {
		LOG.debug("loading games directly to ResponseBody");
		return prettyPrint(gameService
				.requestAllGames(new RequestAllGamesEvent()));
	}

	private String prettyPrint(AllGamesEvent requestAllGames) {
		StringBuffer sb = new StringBuffer();
		String delim = "";
		for (GameDetails gameDetails : requestAllGames.getGameDetails()) {
			sb.append(delim).append(gameDetails.getOpponent());
			delim = ",";
		}

		return sb.toString();
	}

}
