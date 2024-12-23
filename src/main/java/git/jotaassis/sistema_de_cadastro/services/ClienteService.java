package git.jotaassis.sistema_de_cadastro.services;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> cadastrar(ClienteDTO clienteDTO) {

        // Validação do nome
        if (clienteDTO.getNome().equals("") || clienteDTO.getNome().trim().isEmpty()
                || clienteDTO.getNome().matches("\\d+")) {
            rm.setMensagem("O nome não pode ser vazio ou nulo e deve conter apenas letras e espaços");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        // Validando email
        if (clienteDTO.getEmail().equals("") || clienteDTO.getEmail().matches("\\d+")) {
            rm.setMensagem("O email não pode ser vazio ou nulo");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        } else if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            rm.setMensagem("Email já cadastrado");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        // Validação do CEP
        if (enderecoViaCep.validarCep(clienteDTO.getCep()) != null) {
            rm.setMensagem(enderecoViaCep.validarCep(clienteDTO.getCep()));
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
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

    // Listar clientes
    public ResponseEntity<?> listar() {

        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            rm.setMensagem("Não existe clientes cadastrados no sistema!");
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);

    }

    // Buscar cliente por ID
    public ResponseEntity<?> buscarPorID(Long id) {

        if (id == null || id <= 0) {
            rm.setMensagem("ID invalido!");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            rm.setMensagem("Cliente não encontrado! Este ID foi removido ou não está cadastrado");
            return new ResponseEntity<Response>(rm, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);

    }

    // Remover cliente
    public ResponseEntity<Response> deletar(Long id) {

        // Validação do ID
        if (id == null || id <= 0) {
            rm.setMensagem("ID invalido!");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        Optional<Cliente> clienteTemp = clienteRepository.findById(id);

        if (clienteTemp.isEmpty()) {
            rm.setMensagem("Cliente não encontrado! Este ID foi removido ou não está cadastrado");
            return new ResponseEntity<Response>(rm, HttpStatus.NOT_FOUND);
        }

        clienteRepository.deleteById(id);
        rm.setMensagem("Cliente removido");
        return new ResponseEntity<Response>(rm, HttpStatus.OK);
    }

    // Alterar dados do cliente
    public ResponseEntity<Response> atualizar(Long id, ClienteDTO clienteDTO) {

        // Validação do ID
        if (id <= 0 || id == null) {
            rm.setMensagem("ID invalido!");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isEmpty()) {
            rm.setMensagem("Cliente não encontrado com ID: " + id);
            return new ResponseEntity<Response>(rm, HttpStatus.NOT_FOUND);
        }

        // Verificação do nome
        if (clienteDTO.getNome().equals("") || clienteDTO.getNome().trim().isEmpty()
                || clienteDTO.getNome().matches("\\d+")) {
            rm.setMensagem("O nome não pode ser vazio ou nulo e deve conter apenas letras e espaços");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        // Validação do CEP
        if (enderecoViaCep.validarCep(clienteDTO.getCep()) != null) {
            rm.setMensagem(enderecoViaCep.validarCep(clienteDTO.getCep()));
            return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
        }

        // Verificação do email
        if (clienteDTO.getEmail().equals("") || clienteDTO.getEmail().matches("\\d+")) {
            rm.setMensagem("O email não pode ser vazio ou nulo");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        } else if (!clienteDTO.getEmail().equals(clienteExistente.get().getEmail())
                && clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            rm.setMensagem("Email já cadastrado");
            return new ResponseEntity<Response>(rm, HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = clienteExistente.get();

        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCep(clienteDTO.getCep());

        var endereco = enderecoViaCep.buscarCep(clienteDTO.getCep());

        cliente.setEndereco(endereco.get("logradouro"));
        cliente.setBairro(endereco.get("bairro"));
        cliente.setCidade(endereco.get("localidade"));
        cliente.setEstado(endereco.get("uf"));

        clienteRepository.save(cliente);

        rm.setMensagem("Cliente Atualizado!");
        return new ResponseEntity<Response>(rm, HttpStatus.OK);
    }
}