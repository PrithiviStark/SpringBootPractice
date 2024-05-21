package com.expedux.services;

import com.expedux.models.Users;

public interface LoginService {
         Users findCredentials(String userName);
}
