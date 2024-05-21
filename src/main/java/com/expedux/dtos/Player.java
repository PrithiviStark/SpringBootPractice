package com.expedux.dtos;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	@Nullable
	private int playerId;
	@NotEmpty(message = "Player Name field cannot be empty")
	private String playerName;
	@NotEmpty(message = "Player Photo URL field cannot be empty")
	private String playerPhotoUrl;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
