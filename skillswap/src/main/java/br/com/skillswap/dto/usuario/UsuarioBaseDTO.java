package br.com.skillswap.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição/Resposta do Usuário")
public abstract class UsuarioBaseDTO {
    
	@Schema(description = "Login do usuário base", example = "MichaelVieira")
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

