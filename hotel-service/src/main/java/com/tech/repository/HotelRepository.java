package com.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.entites.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}