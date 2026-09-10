package dev.java.GestaoDePedidos.business.DTO;

import java.time.LocalDateTime;

public record CompraDTO(

        Long id,
        Long produtoId,
        String nomeProduto,
        String emailComprador,
        LocalDateTime dataCompra

) {
}
