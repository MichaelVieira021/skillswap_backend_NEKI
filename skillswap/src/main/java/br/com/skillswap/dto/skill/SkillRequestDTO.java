package br.com.skillswap.dto.skill;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição de Habilidade")
public class SkillRequestDTO {
    
    @Schema(description = "Nome da habilidade", example = "Ataque Poderoso")
    private String nome;

    @Schema(description = "Amplificação técnica", example = "5")
    private int tecAmp;

    @Schema(description = "Ataque adicional", example = "10")
    private int atkAdicional;

    @Schema(description = "Duração da habilidade em segundos", example = "30.0")
    private double duracao;

    @Schema(description = "Tempo de resfriamento da habilidade em segundos", example = "60.0")
    private double resfriamento;

    @Schema(description = "URL da foto da habilidade", example = "https://example.com/habilidade.jpg")
    private String foto;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTecAmp() {
		return tecAmp;
	}
	public void setTecAmp(int tecAmp) {
		this.tecAmp = tecAmp;
	}
	public int getAtkAdicional() {
		return atkAdicional;
	}
	public void setAtkAdicional(int atkAdicional) {
		this.atkAdicional = atkAdicional;
	}

	public double getDuracao() {
		return duracao;
	}
	
	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}
	
	public double getResfriamento() {
		return resfriamento;
	}
	
	public void setResfriamento(double resfriamento) {
		this.resfriamento = resfriamento;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	} 
}
