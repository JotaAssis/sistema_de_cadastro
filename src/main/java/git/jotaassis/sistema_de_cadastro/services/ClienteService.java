package git.jotaassis.sistema_de_cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import git.jotaassis.sistema_de_cadastro.dtos.ClienteDTO;
import git.jotaassis.sistema_de_cadastro.models.Cliente;
import git.jotaassis.sistema_de_cadastro.repository.ClienteRepository;
import git.jotaassis.sistema_de_cadastro.util.Response;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Response rm;

    @Autowired
    private EnderecoApiViaCep enderecoViaCep;

    public ResponseEntity<Cliente> cadastrar(ClienteDTO clienteDTO){
        var endereco = enderecoViaCep.buscarCep(clienteDTO.getCep());

        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCep(clienteDTO.getCep());
        cliente.setEndereco(endereco.get("logradouro"));
        cliente.setBairro(endereco.get("bairro"));
        cliente.setCidade(endereco.get("localidade"));
        cliente.setEstado(endereco.get("uf"));

        clienteRepository.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
}