package com.example.hotelreservations.controller;

import com.example.hotelreservations.exception.EntityNotFoundException;
import com.example.hotelreservations.model.Cliente;
import com.example.hotelreservations.model.Hotel;
import com.example.hotelreservations.model.Reserva;
import com.example.hotelreservations.repository.ClienteRepository;
import com.example.hotelreservations.repository.HotelRepository;
import com.example.hotelreservations.repository.ReservaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(
        summary = "Listar todas as reservas",
        description = "Retorna uma lista com todas as reservas efetuadas no sistema."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Reservas listadas com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class))
    )
    @GetMapping
    public List<Reserva> listAllReservas() {
        return reservaRepository.findAll();
    }

    @Operation(
        summary = "Criar uma reserva",
        description = "Cria uma nova reserva vinculando um hotel e um cliente, com as datas de check-in e check-out."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Reserva criada com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class))
    )
    @PostMapping
    public Reserva createReserva(
        @RequestBody(
            description = "Dados da reserva, incluindo hotelId, clienteId, dataCheckin e dataCheckout",
            required = true,
            content = @Content(schema = @Schema(implementation = ReservaDTO.class))
        )
        @org.springframework.web.bind.annotation.RequestBody ReservaDTO reservaDTO) {
        
        Optional<Hotel> hotelOpt = hotelRepository.findById(reservaDTO.getHotelId());
        Optional<Cliente> clienteOpt = clienteRepository.findById(reservaDTO.getClienteId());

        if (hotelOpt.isEmpty() || clienteOpt.isEmpty()) {
            throw new EntityNotFoundException("Hotel or Cliente not found", null);
        }

        Reserva reserva = new Reserva();
        reserva.setHotel(hotelOpt.get());
        reserva.setCliente(clienteOpt.get());
        reserva.setDataCheckin(reservaDTO.getDataCheckin());
        reserva.setDataCheckout(reservaDTO.getDataCheckout());

        return reservaRepository.save(reserva);
    }

    @Operation(
        summary = "Buscar uma reserva por ID",
        description = "Retorna a reserva correspondente ao ID informado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Reserva encontrada com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Reserva não encontrada.",
        content = @Content
    )
    @GetMapping("/{id}")
    public Reserva getReserva(
        @Parameter(description = "ID da reserva a ser buscada", required = true)
        @PathVariable Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva", id));
    }

    @Operation(
        summary = "Deletar uma reserva",
        description = "Remove a reserva correspondente ao ID informado do sistema."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Reserva removida com sucesso."
    )
    @DeleteMapping("/{id}")
    public void deleteReserva(
        @Parameter(description = "ID da reserva a ser removida", required = true)
        @PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva", id));
        reservaRepository.delete(reserva);
    }
}
