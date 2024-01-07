package br.com.skillswap.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.skillswap.modal.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	Optional<Skill> findByNome(String nome);
}
