package br.com.skillswap.dto.userSkill;

import br.com.skillswap.dto.skill.SkillResponseDTO;
import br.com.skillswap.dto.usuario.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição de Habilidade do Usuário")
public class UserSkillRequestDTO {
    
	@Schema(description = "Informações do usuário")
	private UsuarioResponseDTO usuario;
	
	@Schema(description = "Informações da habilidade")
	private SkillResponseDTO skill;
	
	@Schema(description = "Nível de habilidade", example = "3")
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
