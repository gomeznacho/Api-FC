package es.gom.ApiFC.services.impl;

import es.gom.ApiFC.entities.Usuario;
import es.gom.ApiFC.repositories.UsuarioRepository;
import es.gom.ApiFC.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Optional<Usuario> findById(Long id) {
        if(id ==null || id<= 0)
            return Optional.empty();
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findByCandidatoId(Long id) {
        if(id ==null || id<= 0)
            return Optional.empty();
        Usuario usuarioOpt =  usuarioRepository.findByCandidatosId(id);

        return Optional.of(usuarioOpt);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
