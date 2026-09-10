package dev.java.GestaoDePedidos.controller;

import dev.java.GestaoDePedidos.business.DTO.ProdutoDTO;
import dev.java.GestaoDePedidos.business.DTO.ProdutoResumoDTO;
import dev.java.GestaoDePedidos.business.Service.ProdutoService;
import dev.java.GestaoDePedidos.infrastructure.enums.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/registro")
    public ResponseEntity<ProdutoDTO> gravarProduto(@RequestBody ProdutoDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(produtoService.salvarProduto(token ,dto));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResumoDTO>> buscarProduto(@PathVariable Categoria categoria) {

        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(produtoService.buscarPorID(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String token) {
        produtoService.deletarProdutoPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<ProdutoDTO> atualizarDadosProduto(@RequestHeader("Authorization") String token,
                                                            @RequestBody ProdutoDTO produtoDTO,
                                                            @RequestParam("id") Long id) {

        return ResponseEntity.ok(produtoService.atualizarDadosProduto(token, produtoDTO, id));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(produtoService.listarTodos(token));
    }

}
