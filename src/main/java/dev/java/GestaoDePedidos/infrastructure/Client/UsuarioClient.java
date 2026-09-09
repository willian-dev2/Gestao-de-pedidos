package dev.java.GestaoDePedidos.infrastructure.Client;

import dev.java.GestaoDePedidos.business.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuarioPedido", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/auth")
    UsuarioDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestParam("Authorization") String token);

}
