package com.expedux.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedux.models.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
