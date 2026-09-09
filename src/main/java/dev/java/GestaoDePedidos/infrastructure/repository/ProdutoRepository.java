package dev.java.GestaoDePedidos.infrastructure.repository;

import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {



}
