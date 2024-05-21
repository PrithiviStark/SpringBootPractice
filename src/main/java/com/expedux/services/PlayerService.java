package com.expedux.services;

import java.util.List;

import com.expedux.dtos.Player;

public interface PlayerService {	

	List<Player> playersList();

	void savePlayer(Player pl);
}
