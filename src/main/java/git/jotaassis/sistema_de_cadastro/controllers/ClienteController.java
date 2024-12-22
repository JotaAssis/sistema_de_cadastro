package git.jotaassis.sistema_de_cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import git.jotaassis.sistema_de_cadastro.dtos.ClienteDTO;
import git.jotaassis.sistema_de_cadastro.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ClienteDTO clienteDTO){
        return clienteService.cadastrar(clienteDTO);
    }
}