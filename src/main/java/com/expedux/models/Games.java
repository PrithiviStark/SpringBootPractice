package com.expedux.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="games")
public class Games {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int gameId;
	
	private String gameName;
	
	private int requiredPlayersCount;

}
