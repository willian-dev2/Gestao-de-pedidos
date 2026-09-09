package dev.java.GestaoDePedidos.business.mapper;

import dev.java.GestaoDePedidos.business.DTO.ProdutoDTO;
import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoConverter {

    ProdutoEntity paraProdutoEntity(ProdutoDTO dto);

    ProdutoDTO paraProdutoDTO(ProdutoEntity entity);

}
