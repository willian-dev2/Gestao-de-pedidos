package dev.java.GestaoDePedidos.business.DTO;

import dev.java.GestaoDePedidos.infrastructure.enums.Categoria;
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

    private String nomeProduto;
    private String descricao;
    private Double preco;
    private String emailDoUsuario;
    private LocalDate dataCriacao;
    private Status status;
    private Categoria categoria;

}
