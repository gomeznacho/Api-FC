package es.gom.ApiFC.repositories;

import es.gom.ApiFC.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
    Usuario findByCandidatosId(Long id);
    Usuario findByEmail(String email);
}
