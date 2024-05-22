package com.expedux.services;

import com.expedux.dtos.UserDetailsDto;
import com.expedux.models.Users;

public interface LoginService {
         Users findCredentials(String userName);
         void saveRegistration(UserDetailsDto ud);
}
