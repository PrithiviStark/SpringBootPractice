package com.expedux.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedux.models.Players;

public interface PlayersRepository extends JpaRepository<Players, Integer> {

}
