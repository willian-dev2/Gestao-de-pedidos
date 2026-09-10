package dev.java.GestaoDePedidos.business.converter;

import dev.java.GestaoDePedidos.business.DTO.ProdutoDTO;
import dev.java.GestaoDePedidos.business.DTO.ProdutoResumoDTO;
import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    // Conversão de dados do DTO para Entity
    public ProdutoEntity paraProdutoEntity(ProdutoDTO produtoDTO) {
        return ProdutoEntity.builder()
                .nomeProduto(produtoDTO.getNomeProduto())
                .descricao(produtoDTO.getDescricao())
                .preco(produtoDTO.getPreco())
                .emailDoUsuario(produtoDTO.getEmailDoUsuario())
                .dataCriacao(produtoDTO.getDataCriacao())
                .status(produtoDTO.getStatus())
                .categoria(produtoDTO.getCategoria())
                .build();
    }

    // Conversão de dados da Entity para DTO
    public ProdutoDTO paraProdutoDTO(ProdutoEntity produtoEntity) {
        return ProdutoDTO.builder()
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricao(produtoEntity.getDescricao())
                .preco(produtoEntity.getPreco())
                .emailDoUsuario(produtoEntity.getEmailDoUsuario())
                .dataCriacao(produtoEntity.getDataCriacao())
                .status(produtoEntity.getStatus())
                .categoria(produtoEntity.getCategoria())
                .build();
    }

    // Um converter para retornar apenas o Id do produto e o nome do produto
    public ProdutoResumoDTO paraProdutoResumoDTO(ProdutoEntity entity) {
        return new ProdutoResumoDTO(entity.getId(), entity.getNomeProduto());
    }

    public ProdutoEntity updateProduto(ProdutoDTO dto, ProdutoEntity entity) {
        return ProdutoEntity.builder()
                .id(entity.getId())
                .nomeProduto(dto.getNomeProduto() != null ? dto.getNomeProduto() : entity.getNomeProduto())
                .descricao(dto.getDescricao() != null ? dto.getDescricao() : entity.getDescricao())
                .preco(dto.getPreco() != null ? dto.getPreco() : entity.getPreco())
                .status(dto.getStatus() != null ? dto.getStatus() : entity.getStatus())
                .categoria(dto.getCategoria() != null ? dto.getCategoria() : entity.getCategoria())
                .dataCriacao(entity.getDataCriacao())
                .emailDoUsuario(dto.getEmailDoUsuario() != null ? dto.getEmailDoUsuario() : entity.getEmailDoUsuario())
                .build();
    }


}
