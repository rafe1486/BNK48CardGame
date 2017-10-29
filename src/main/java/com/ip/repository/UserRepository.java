package com.ip.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ip.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findOneByEmail(String email);
}