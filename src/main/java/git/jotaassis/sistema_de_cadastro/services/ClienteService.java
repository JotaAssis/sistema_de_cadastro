package git.jotaassis.sistema_de_cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import git.jotaassis.sistema_de_cadastro.dtos.ClienteDTO;
import git.jotaassis.sistema_de_cadastro.repository.ClienteRepository;
import git.jotaassis.sistema_de_cadastro.util.Response;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Response rm;

    public ResponseEntity<?> cadastrar(ClienteDTO clienteDTO){
        return null;
    }
}