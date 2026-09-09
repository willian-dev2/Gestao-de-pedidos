package dev.java.GestaoDePedidos.business.DTO;

import dev.java.GestaoDePedidos.infrastructure.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    private long id;
    private String nomeProduto;
    private String descricao;
    private double preco;
    private String emailDoUsuario;
    private LocalDate dataCriacao;
    private Status status;

}
