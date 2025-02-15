package com.example.hotelreservations.controller;

import com.example.hotelreservations.exception.EntityNotFoundException;
import com.example.hotelreservations.model.Hotel;
import com.example.hotelreservations.repository.HotelRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Operation(
        summary = "Listar todos os hotéis",
        description = "Retorna uma lista com todos os hotéis cadastrados no sistema."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Hotéis listados com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class))
    )
    @GetMapping
    public List<Hotel> listAllHotels() {
        return hotelRepository.findAll();
    }

    @Operation(
        summary = "Criar um hotel",
        description = "Cria um novo hotel no sistema com os dados informados."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Hotel criado com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class))
    )
    @PostMapping
    public Hotel createHotel(
        @RequestBody(
            description = "Dados do hotel a ser criado",
            required = true,
            content = @Content(schema = @Schema(implementation = Hotel.class))
        )
        @org.springframework.web.bind.annotation.RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Operation(
        summary = "Buscar um hotel por ID",
        description = "Retorna o hotel correspondente ao ID informado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Hotel encontrado com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Hotel não encontrado.",
        content = @Content
    )
    @GetMapping("/{id}")
    public Hotel getHotelById(
        @Parameter(description = "ID do hotel a ser buscado", required = true)
        @PathVariable Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel", id));
    }

    @Operation(
        summary = "Atualizar um hotel",
        description = "Atualiza os dados do hotel existente com base no ID informado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Hotel atualizado com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Hotel.class))
    )
    @PutMapping("/{id}")
    public Hotel updateHotel(
        @Parameter(description = "ID do hotel a ser atualizado", required = true)
        @PathVariable Long id,
        @RequestBody(
            description = "Dados atualizados do hotel",
            required = true,
            content = @Content(schema = @Schema(implementation = Hotel.class))
        )
        @org.springframework.web.bind.annotation.RequestBody Hotel hotel) {
        Hotel hotelToUpdate = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel", id));
        hotelToUpdate.setNome(hotel.getNome());
        hotelToUpdate.setLocalizacao(hotel.getLocalizacao());
        hotelToUpdate.setCapacidade(hotel.getCapacidade());
        return hotelRepository.save(hotelToUpdate);
    }

    @Operation(
        summary = "Deletar um hotel",
        description = "Remove o hotel correspondente ao ID informado do sistema."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Hotel removido com sucesso."
    )
    @DeleteMapping("/{id}")
    public void deleteHotel(
        @Parameter(description = "ID do hotel a ser removido", required = true)
        @PathVariable Long id) {
        Hotel hotelToDelete = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel", id));
        hotelRepository.delete(hotelToDelete);
    }
}
