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

    public ResponseEntity<?> cadastrar(ClienteDTO clienteDTO){

        //Validação do nome
        if (clienteDTO.getNome().equals("") || clienteDTO.getNome().trim().isEmpty()
                || clienteDTO.getNome().matches("\\d+")) {
            rm.setMensagem("O nome não pode ser vazio ou nulo e deve conter apenas letras e espaços");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        //Validando email
        if (clienteDTO.getEmail().equals("") || clienteDTO.getEmail().matches("\\d+")) {
            rm.setMensagem("O email não pode ser vazio ou nulo");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        } else if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            rm.setMensagem("Email já cadastrado");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        //Validação do CEP
        if (clienteDTO.getCep().equals("")) {
            rm.setMensagem("O CEP não pode ser vazio ou nulo");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        } else if (clienteDTO.getCep().length() != 8) {
            rm.setMensagem("CEP inválido! o cep deve ter 8 digitos");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

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