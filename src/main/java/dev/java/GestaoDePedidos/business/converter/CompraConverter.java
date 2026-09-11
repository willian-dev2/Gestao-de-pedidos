package dev.java.GestaoDePedidos.business.converter;

import dev.java.GestaoDePedidos.business.DTO.CompraDTO;
import dev.java.GestaoDePedidos.infrastructure.entity.CompraEntity;
import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class CompraConverter {

    public CompraDTO paraCompraDTO(CompraEntity entity) {
        return new CompraDTO(
                entity.getId(),
                entity.getProduto().getId(),
                entity.getValor(),
                entity.getProduto().getNomeProduto(),
                entity.getEmailComprador(),
                entity.getDataCompra()
        );
    }

    public CompraEntity paraCompraEntity(ProdutoEntity produto, String email) {
        return CompraEntity.builder()
                .produto(produto)
                .emailComprador(email)
                .valor(produto.getPreco())
                .build();
        // não precisa passar o dataCompra porque ele é gerado automaticamente
    }

}
