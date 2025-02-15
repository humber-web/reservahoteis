package com.example.hotelreservations.repository;

import com.example.hotelreservations.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
