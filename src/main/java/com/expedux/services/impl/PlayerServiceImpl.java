package com.expedux.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expedux.dtos.Player;
import com.expedux.models.Players;
import com.expedux.repos.PlayersRepository;
import com.expedux.services.PlayerService;


@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayersRepository repo;


	@Override
	public List<Player> playersList() {
		List<Players> list = repo.findAll();
		return list.stream().map(this::mapToPlayerDto).collect(Collectors.toList());
	}

	@Override
	public void savePlayer(Player pl) {
		Players pls=mapToPlayer( pl);

		repo.save(pls);
	}

	private Player mapToPlayerDto(Players pls) {
		return  Player.builder()
				.playerId(pls.getPlayerId())
				.playerName(pls.getPlayerName())
				.playerPhotoUrl(pls.getPlayerPhotoUrl())
				.createdDate(pls.getCreatedDate())
				.updatedDate(pls.getUpdatedDate()).build();
	}
	
	private Players mapToPlayer(Player pl) {
		return  Players.builder()
				.playerName(pl.getPlayerName())
				.playerPhotoUrl(pl.getPlayerPhotoUrl()).build();
	}


}
