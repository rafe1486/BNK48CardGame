package com.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ip.entity.TradeCard;

@Repository
public interface TradeCardRepository extends JpaRepository<TradeCard, Integer> {
	
}