package br.com.skillswap.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de Login do Usuário")
public class UsuarioLoginResponseDTO  {
  
	@Schema(description = "Token de autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
	
	@Schema(description = "Informações do usuário logado")
    private UsuarioResponseDTO usuario;

    public UsuarioLoginResponseDTO(String token, UsuarioResponseDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }
    
    public UsuarioLoginResponseDTO() { 
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
}
