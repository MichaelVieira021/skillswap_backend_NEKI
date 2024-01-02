package br.com.skillswap.dto.userSkill;

import br.com.skillswap.dto.skill.SkillResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioResponseDTO;

public class UserSkillRequestDTO {
    
	private UsuarioResponseDTO usuario;
	private SkillResponseDTO skill;
	private Long level;
	
	public UsuarioResponseDTO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioResponseDTO usuario) {
		this.usuario = usuario;
	}
	
	public SkillResponseDTO getSkill() {
		return skill;
	}
	public void setSkill(SkillResponseDTO skill) {
		this.skill = skill;
	}
	
	public Long getLevel() {
		return level;
	}
	
	public void setLevel(Long level) {
		this.level = level;
	}
}
