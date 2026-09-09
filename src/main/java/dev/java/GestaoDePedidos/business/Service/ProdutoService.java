package dev.java.GestaoDePedidos.business.Service;

import dev.java.GestaoDePedidos.business.DTO.ProdutoDTO;
import dev.java.GestaoDePedidos.business.mapper.ProdutoConverter;
import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import dev.java.GestaoDePedidos.infrastructure.enums.Status;
import dev.java.GestaoDePedidos.infrastructure.repository.ProdutoRepository;
import dev.java.GestaoDePedidos.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoConverter produtoConverter;
    private final JwtUtil jwtUtil;

    public ProdutoDTO salvarProduto(String token,ProdutoDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setStatus(Status.DISPONIVEL);
        dto.setEmailDoUsuario(email);
        ProdutoEntity entity = produtoConverter.paraProdutoEntity(dto);

        return produtoConverter.paraProdutoDTO(
                produtoRepository.save(entity)
        );
    }

    public void



}
