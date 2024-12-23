package git.jotaassis.sistema_de_cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import git.jotaassis.sistema_de_cadastro.dtos.ClienteDTO;
import git.jotaassis.sistema_de_cadastro.services.ClienteService;
import git.jotaassis.sistema_de_cadastro.util.Response;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    // Rota para cadastrar
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ClienteDTO clienteDTO){
        return clienteService.cadastrar(clienteDTO);
    }
    
    // Rota para listar clientes
    @GetMapping("/listar")
    public ResponseEntity<?> listar(){
        return clienteService.listar();
    }

    // Rota para buscar cliente por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return clienteService.buscarPorID(id);
    }

    // Rota para deletar
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Response> deletar(@PathVariable Long id){
        return clienteService.deletar(id);
    }

    // Rota para atualizar cliente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Response> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizar(id, clienteDTO);
    }
}