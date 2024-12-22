package git.jotaassis.sistema_de_cadastro.util;

import org.springframework.stereotype.Component;

@Component
public class Response {

    public String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
}
