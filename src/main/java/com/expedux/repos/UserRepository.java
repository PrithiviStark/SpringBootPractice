package com.expedux.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedux.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByUserName(String userName);

}
