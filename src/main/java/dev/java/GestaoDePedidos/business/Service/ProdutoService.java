package dev.java.GestaoDePedidos.business.Service;

import dev.java.GestaoDePedidos.business.DTO.ProdutoDTO;
import dev.java.GestaoDePedidos.business.DTO.ProdutoResumoDTO;
import dev.java.GestaoDePedidos.business.DTO.UsuarioDTO;
import dev.java.GestaoDePedidos.business.converter.ProdutoConverter;
import dev.java.GestaoDePedidos.infrastructure.Client.UsuarioClient;
import dev.java.GestaoDePedidos.infrastructure.Exceptions.ConflictException;
import dev.java.GestaoDePedidos.infrastructure.Exceptions.ResourceNotFoundException;
import dev.java.GestaoDePedidos.infrastructure.entity.ProdutoEntity;
import dev.java.GestaoDePedidos.infrastructure.enums.Categoria;
import dev.java.GestaoDePedidos.infrastructure.enums.Status;
import dev.java.GestaoDePedidos.infrastructure.repository.ProdutoRepository;
import dev.java.GestaoDePedidos.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoConverter produtoConverter;
    private final JwtUtil jwtUtil;
    private final UsuarioClient usuarioClient;

    public ProdutoDTO salvarProduto(String token, ProdutoDTO dto) {
        // verifica antes de qualquer coisa se o usuario cadastrando é ADMIN
        compararRole(token);

        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setStatus(Status.DISPONIVEL);

        // chamada do FeingCliente para validar o usuario na API de usuarios
        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioPorEmail(email, token);

        // Lógica de salvar usuário
        dto.setEmailDoUsuario(email);
        ProdutoEntity entity = produtoConverter.paraProdutoEntity(dto);

        return produtoConverter.paraProdutoDTO(
                produtoRepository.save(entity)
        );
    }


    public void compararRole(String token) {
        try {
            // busca o ROLE do usuario através do token
            String role = jwtUtil.extractRole(token.substring(7));
            // verifica se a Role é ADMIN
            if (!role.equals("ADMIN")) {
                throw new ConflictException(
                        "É necessário ser admin para acessar essa função | role atual: Customer"
                );
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    "É necessário ser admin para acessar essa função | role atual: Customer"
            );
        }
    }

    // busca todos os produtos da mesma categoria passando somente o Id de cada produto e o nome do produto
    public List<ProdutoResumoDTO> buscarPorCategoria(Categoria categoria) {
        // lista os produtos com a mesma categoria
        List<ProdutoEntity> entities = produtoRepository.findByCategoria(categoria);
        // retorna a lista de produtos convertendo para o ResumoDTO
        return entities.stream()
                .map(produtoConverter::paraProdutoResumoDTO)
                .toList();
    }

    // lista com mais detalhe o produto requisitado
    public ProdutoDTO buscarPorID(Long id) {
        ProdutoEntity entity = temProdutoDb(id);
        return produtoConverter.paraProdutoDTO(entity);
    }

    public void deletarProdutoPorId(Long id, String token) {
        // Verifica se o usuário tentando deletar é um admin
        compararRole(token);
        // Verifica se tem o produto no banco de dados através do Id
        ProdutoEntity entity = temProdutoDb(id);
        produtoRepository.delete(entity);
    }

    // busca o produto no banco de dados pelo Id e lança uma exceção se não encontrar
    public ProdutoEntity temProdutoDb (Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Produto não encontrado")
                );
    }


    public ProdutoDTO atualizarDadosProduto(String token, ProdutoDTO produtoDTO, Long id) {

        // busca id do produto no banco de dados
        ProdutoEntity produtoEntity = temProdutoDb(id);

        // Aqui chamaremos o método para verificar se é o admin que esta fazendo essa operação
        compararRole(token);

        // mescla os dados recebidos na requisição DTO com os dados do banco de dados
        ProdutoEntity produto = produtoConverter.updateProduto(produtoDTO, produtoEntity);

        // salva dados do usuario convertido e depois pega o retorno e converte para ProdutoDTO
        return produtoConverter.paraProdutoDTO(produtoRepository.save(produto));
    }

    public List<ProdutoDTO> listarTodos(String token) {
        compararRole(token);
        List<ProdutoEntity> entities = produtoRepository.findAll();
        return entities.stream()
                .map(produtoConverter::paraProdutoDTO)
                .toList();
    }


}
