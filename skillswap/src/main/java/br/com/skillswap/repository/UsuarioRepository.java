package br.com.skillswap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.skillswap.modal.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
