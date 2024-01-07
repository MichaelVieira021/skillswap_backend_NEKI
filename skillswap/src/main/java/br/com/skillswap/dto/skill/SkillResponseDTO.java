package br.com.skillswap.dto.skill;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de Habilidade")
public class SkillResponseDTO extends SkillRequestDTO {
    
    @Schema(description = "ID da habilidade", example = "1")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}