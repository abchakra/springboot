package com.example.forex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.forex.entity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}