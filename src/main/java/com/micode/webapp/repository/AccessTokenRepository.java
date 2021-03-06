package com.micode.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micode.webapp.entity.AccessToken;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {
	
}
