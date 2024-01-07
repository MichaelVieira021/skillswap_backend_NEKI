package br.com.skillswap.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição do Usuário")
public class UsuarioRequestDTO extends UsuarioBaseDTO {
    
	@Schema(description = "Senha do usuário", example = "1234")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
