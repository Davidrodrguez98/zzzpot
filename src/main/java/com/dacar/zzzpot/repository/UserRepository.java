package com.dacar.zzzpot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dacar.zzzpot.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}


