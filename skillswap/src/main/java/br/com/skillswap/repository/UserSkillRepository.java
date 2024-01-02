package br.com.skillswap.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.skillswap.modal.UserSkill;
import br.com.skillswap.modal.Usuario;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
	List<UserSkill> findByUsuarioOrderById(Usuario usuario);
}
