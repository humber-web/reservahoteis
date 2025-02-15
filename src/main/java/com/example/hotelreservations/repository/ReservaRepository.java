package com.example.hotelreservations.repository;

import com.example.hotelreservations.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
