package dev.java.GestaoDePedidos.infrastructure.entity;

import dev.java.GestaoDePedidos.infrastructure.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos")
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Nome Do Produto")
    private String nomeProduto;
    @Column(name = "Descrição")
    private String descricao;
    @Column(name = "Preço")
    private double preco;
    @Column(name = "email")
    private String emailDoUsuario;
    @Column(name = "Data Entrada")
    @CreationTimestamp
    private LocalDate dataCriacao;
    @Column(name = "Status")
    private Status status;

}
