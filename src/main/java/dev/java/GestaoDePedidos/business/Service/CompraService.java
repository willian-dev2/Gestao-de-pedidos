package dev.java.GestaoDePedidos.business.Service;

import dev.java.GestaoDePedidos.business.DTO.CompraDTO;
import dev.java.GestaoDePedidos.business.converter.CompraConverter;
import dev.java.GestaoDePedidos.infrastructure.Exceptions.ConflictException;
import dev.java.GestaoDePedidos.infrastructure.Exceptions.ResourceNotFoundException;
import dev.java.GestaoDePedidos.infrastructure.entity.CompraEntity;
import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import dev.java.GestaoDePedidos.infrastructure.enums.Status;
import dev.java.GestaoDePedidos.infrastructure.repository.CompraRepository;
import dev.java.GestaoDePedidos.infrastructure.repository.ProdutoRepository;
import dev.java.GestaoDePedidos.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;
    private final CompraConverter compraConverter;
    private final JwtUtil jwtUtil;

    public CompraDTO comprarProduto(Long id, String token) {
        // verifica se tem o produto no Banco de dados
        ProdutoEntity produto = produtoService.temProdutoDb(id);

        // Verifica se o produto está disponível para compra
        if (produto.getStatus() == Status.INDISPONIVEL) {
            throw new ResourceNotFoundException("Produto indisponível para compra");
        }

        // extrai o email do token JWT
        String email = jwtUtil.extractUsername(token.substring(7));

        // compra sendo realizada
        produto.setStatus(Status.INDISPONIVEL);
        produtoRepository.save(produto);

        // processo de conversão de dados e salvamento no banco de dados
        CompraEntity compra = compraConverter.paraCompraEntity(produto, email);
        CompraEntity salva = compraRepository.save(compra);

        // converte nossa entidade para o DTO
        return compraConverter.paraCompraDTO(salva);

    }


    public CompraDTO buscarPorId(Long id, String token) {
        CompraEntity entity = findById(id, token);
        return compraConverter.paraCompraDTO(entity);
    }



    public CompraEntity findById(Long id, String token) {
        CompraEntity entity = compraRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Compra não encontrado")
                );

        String emailToken = jwtUtil.extractUsername((token.substring(7)));

        // permite acesso se for o dono da compra OU se for admin
        if (!emailToken.equals(entity.getEmailComprador()) && !compararRole(token)) {
            throw new ResourceNotFoundException("É necessario um role ADMIN para acessar outro email " + emailToken);
        }

        return entity;
    }


    public boolean compararRole(String token) {
        // busca o ROLE do usuario através do token
        String role = jwtUtil.extractRole(token.substring(7));

        // método boolean ja direto no return, se for igual retorna true, se não, retorna false
        return role.equals("ADMIN");
    }

}
