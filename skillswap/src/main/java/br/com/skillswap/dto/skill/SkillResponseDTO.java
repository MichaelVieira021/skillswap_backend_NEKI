package br.com.skillswap.dto.skill;

public class SkillResponseDTO extends SkillRequestDTO {
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}