package com.example.hotelreservations.controller;

import com.example.hotelreservations.exception.EntityNotFoundException;
import com.example.hotelreservations.model.Cliente;
import com.example.hotelreservations.repository.ClienteRepository;
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
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Operation(
        summary = "Listar todos os clientes",
        description = "Retorna uma lista com todos os clientes cadastrados no sistema."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Clientes listados com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
    )
    @GetMapping
    public List<Cliente> listAllClientes() {
        return clienteRepository.findAll();
    }

    @Operation(
        summary = "Criar um cliente",
        description = "Cria um novo cliente no sistema com os dados informados."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Cliente criado com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
    )
    @PostMapping
    public Cliente createCliente(
        @RequestBody(
            description = "Dados do cliente a ser criado",
            required = true,
            content = @Content(schema = @Schema(implementation = Cliente.class))
        )
        @org.springframework.web.bind.annotation.RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Operation(
        summary = "Buscar um cliente por ID",
        description = "Retorna o cliente correspondente ao ID informado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Cliente encontrado com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Cliente não encontrado.",
        content = @Content
    )
    @GetMapping("/{id}")
    public Cliente getCliente(
        @Parameter(description = "ID do cliente a ser buscado", required = true)
        @PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));
    }

    @Operation(
        summary = "Atualizar um cliente",
        description = "Atualiza os dados do cliente existente com base no ID informado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Cliente atualizado com sucesso.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
    )
    @PutMapping("/{id}")
    public Cliente updateCliente(
        @Parameter(description = "ID do cliente a ser atualizado", required = true)
        @PathVariable Long id,
        @RequestBody(
            description = "Dados atualizados do cliente",
            required = true,
            content = @Content(schema = @Schema(implementation = Cliente.class))
        )
        @org.springframework.web.bind.annotation.RequestBody Cliente cliente) {
        Cliente clienteToUpdate = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));
        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setEmail(cliente.getEmail());
        clienteToUpdate.setTelefone(cliente.getTelefone());
        return clienteRepository.save(clienteToUpdate);
    }

    @Operation(
        summary = "Deletar um cliente",
        description = "Remove o cliente correspondente ao ID informado do sistema."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Cliente removido com sucesso."
    )
    @DeleteMapping("/{id}")
    public void deleteCliente(
        @Parameter(description = "ID do cliente a ser removido", required = true)
        @PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente", id));
        clienteRepository.delete(cliente);
    }
}
