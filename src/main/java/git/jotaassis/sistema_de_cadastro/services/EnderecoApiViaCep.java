package git.jotaassis.sistema_de_cadastro.services;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoApiViaCep {
    
    private final String ViaCep_Url = "https://viacep.com.br/ws/";

    public Map<String, String> buscarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = ViaCep_Url + cep + "/json/";
        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, String>>() {
                });
        return response.getBody();
    }

    public String validarCep(String cep) {
        if (cep == null || cep.length() != 8) {
            return "CEP inválido. O CEP não pode ser nulo e deve ter 8 caracteres.";
        }
        return null;
    }

}