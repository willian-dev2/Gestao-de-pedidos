package dev.java.GestaoDePedidos.business.DTO;

import dev.java.GestaoDePedidos.infrastructure.enums.Categoria;
import dev.java.GestaoDePedidos.infrastructure.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nomeProduto;

    @NotBlank(message = "A descrição do produto é obrigatória")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;

    private String emailDoUsuario;

    private LocalDate dataCriacao;

    private Status status;

    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

}
