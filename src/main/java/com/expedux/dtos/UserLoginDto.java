package com.expedux.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {

	@Nullable
	private int userSeq;
	@NotEmpty(message = "username name required")
	private String userPassword;
	@NotEmpty(message = "password name required")
	private String userName;

}
