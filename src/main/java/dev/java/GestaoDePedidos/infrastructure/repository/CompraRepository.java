package dev.java.GestaoDePedidos.infrastructure.repository;

import dev.java.GestaoDePedidos.infrastructure.entity.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {
}
