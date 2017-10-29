package com.ip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ip.entity.MyCard;

@Repository
public interface MyCardRepository extends JpaRepository<MyCard, Integer> {
	
}