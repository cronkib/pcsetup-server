package net.astercrono.pcsetup.dataaccess;

import java.util.List;

import net.astercrono.pcsetup.domain.Game;

public interface GameDao {
	List<Game> getGames();
}
