package br.com.skillswap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.skillswap.modal.UserSkill;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
}
