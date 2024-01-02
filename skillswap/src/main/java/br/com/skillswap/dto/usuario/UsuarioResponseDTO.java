package br.com.skillswap.dto.usuario;

public class UsuarioResponseDTO extends UsuarioBaseDTO {
    
    private Long id;
    private String dtCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}