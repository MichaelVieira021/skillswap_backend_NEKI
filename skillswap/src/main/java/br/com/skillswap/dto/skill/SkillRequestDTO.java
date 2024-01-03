package br.com.skillswap.dto.skill;

public class SkillRequestDTO {
    
	private String nome;
    private double tecAmp;
    private double atkAdicional;
    private double duracao;
    private double resfriamento;
    private String foto;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getTecAmp() {
		return tecAmp;
	}
	public void setTecAmp(double tecAmp) {
		this.tecAmp = tecAmp;
	}
	public double getAtkAdicional() {
		return atkAdicional;
	}
	public void setAtkAdicional(double atkAdicional) {
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