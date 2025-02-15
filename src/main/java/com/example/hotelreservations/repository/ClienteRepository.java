package com.example.hotelreservations.repository;

import com.example.hotelreservations.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
