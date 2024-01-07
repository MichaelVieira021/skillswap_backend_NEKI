package br.com.skillswap.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição de Login do Usuário")
public class UsuarioLoginRequestDTO {
	
	@Schema(description = "Login do usuário", example = "MichaelVieira")
    private String login;
	
	@Schema(description = "Senha do usuário", example = "1234")
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
