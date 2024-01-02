package br.com.skillswap.dto.userSkill;

public class UserSkillResponseDTO extends UserSkillRequestDTO {
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}