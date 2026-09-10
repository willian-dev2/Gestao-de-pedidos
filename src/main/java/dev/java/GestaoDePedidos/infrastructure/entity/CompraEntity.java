package dev.java.GestaoDePedidos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compras")
@Builder
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Produto_id")
    private ProdutoEntity produto;

    @Column(name = "Email_Comprador", length = 100)
    private String emailComprador;

    @CreationTimestamp
    @Column(name = "Data_Compra", length = 100)
    private LocalDateTime dataCompra;


}
