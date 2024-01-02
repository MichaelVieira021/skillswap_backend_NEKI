package br.com.skillswap.dto.usuario;

public abstract class UsuarioBaseDTO {
    
    private String nomeUsuario;
    private String email;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

