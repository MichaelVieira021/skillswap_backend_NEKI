package br.com.skillswap.dto.userSkill;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de Habilidade do Usuário")
public class UserSkillResponseDTO extends UserSkillRequestDTO {
    
	@Schema(description = "ID da habilidade do usuário", example = "1")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}