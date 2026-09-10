package dev.java.GestaoDePedidos.infrastructure.repository;

import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import dev.java.GestaoDePedidos.infrastructure.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);

    Optional<ProdutoEntity> findById(Long id);

}
