package dev.java.GestaoDePedidos.controller;

import dev.java.GestaoDePedidos.business.DTO.ProdutoDTO;
import dev.java.GestaoDePedidos.business.Service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/registro")
    public ResponseEntity<ProdutoDTO> gravarProduto(@RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(produtoService.salvarProduto(dto));
    }

}
