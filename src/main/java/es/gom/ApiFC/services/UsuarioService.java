package es.gom.ApiFC.services;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {
    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();

    Optional<Usuario> findByCandidatoId(Long id);

    Usuario save(Usuario usuario);

    boolean deleteById(Long id);

    boolean deleteAll();
}
