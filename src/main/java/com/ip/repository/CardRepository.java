package com.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ip.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	
}