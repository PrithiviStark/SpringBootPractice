package com.expedux.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {

	@Nullable
	private int userId;
	@NotEmpty(message = "username name required")
	private String userName;
	@NotEmpty(message = "password name required")
	private String userPassword;
	private String roles;

}
