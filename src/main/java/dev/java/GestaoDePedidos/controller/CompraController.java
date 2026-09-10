package dev.java.GestaoDePedidos.controller;

import dev.java.GestaoDePedidos.business.DTO.CompraDTO;
import dev.java.GestaoDePedidos.business.Service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    @PostMapping("/{produtoId}")
    public ResponseEntity<CompraDTO> comprarProduto(@PathVariable Long produtoId,
                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(compraService.comprarProduto(produtoId, token));

    }


    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> buscarPorId(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(compraService.buscarPorId(id, token));
    }


}
