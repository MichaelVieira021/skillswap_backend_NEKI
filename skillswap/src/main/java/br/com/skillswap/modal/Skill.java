package br.com.skillswap.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double tecAmp;

    @Column(nullable = false)
    private double atkAdicional;

    @Column(nullable = false)
    private double duracao;

    @Column(nullable = false)
    private double resfriamento;

    @Column(nullable = false)
    private String foto;

    public Skill() {
    }

    public Skill(String nome, double tecAmp, double atkAdicional, double resfriamento, double duracao, String foto) {
        this.nome = nome;
        this.tecAmp = tecAmp;
        this.atkAdicional = atkAdicional;
        this.resfriamento = resfriamento;
        this.duracao = duracao;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
