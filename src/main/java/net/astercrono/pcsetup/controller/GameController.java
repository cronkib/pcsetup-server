package net.astercrono.pcsetup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.astercrono.pcsetup.dataaccess.GameDao;
import net.astercrono.pcsetup.domain.Game;

@RestController
public class GameController extends PCSetupController {
	@Autowired
	private GameDao gameDao;
	
	@GetMapping("/games")
	public List<Game> getGames() {
		return gameDao.getGames();
	}
}
