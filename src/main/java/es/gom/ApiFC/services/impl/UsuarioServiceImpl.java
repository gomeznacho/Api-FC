package es.gom.ApiFC.services.impl;

import es.gom.ApiFC.config.MyUserDetails;
import es.gom.ApiFC.dto.UsuarioDto;
import es.gom.ApiFC.entities.Usuario;
import es.gom.ApiFC.repositories.UsuarioRepository;
import es.gom.ApiFC.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value="usuarioService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null)
            throw new UsernameNotFoundException("username o password incorrectos");
        return new MyUserDetails(usuario.getUsername(), usuario.getPassword());
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
    public Usuario save(UsuarioDto usuario) {
        Usuario usuarioTemp = usuario.getUserFromDto();

        if(usuarioRepository.existsByEmail(usuarioTemp.getEmail()) ||
                usuarioRepository.existsByUsername(usuarioTemp.getUsername()))
            throw new IllegalArgumentException("email o username ya registrados");

        usuarioTemp.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuarioTemp);
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
