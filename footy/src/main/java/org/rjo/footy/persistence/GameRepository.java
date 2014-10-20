package org.rjo.footy.persistence;

import org.rjo.footy.core.domain.Game;

public interface GameRepository {

	// boolean save(Game game);

	Game[] list();

	// boolean dumptoFile(String filename) throws IOException;
	//
	// boolean loadFromFile(String filename) throws IOException;
}
