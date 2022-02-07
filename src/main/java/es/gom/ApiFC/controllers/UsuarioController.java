package es.gom.ApiFC.controllers;

import es.gom.ApiFC.config.TokenProvider;
import es.gom.ApiFC.dto.AuthToken;
import es.gom.ApiFC.dto.LoginUsuario;
import es.gom.ApiFC.dto.UsuarioDto;
import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Usuario;
import es.gom.ApiFC.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private AuthenticationManager authenticationManager;
    private UsuarioService usuarioService;
    private TokenProvider tokenUtil;

    public UsuarioController(AuthenticationManager authenticationManager, TokenProvider tokenUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenUtil = tokenUtil;
    }


   @PostMapping("/registro")
   public Usuario register(@RequestBody UsuarioDto usuario){

        return usuarioService.save(usuario);
   }

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.getUsername(),
                        loginUsuario.getPassword()
                )
        );
       SecurityContextHolder.getContext().setAuthentication(authentication);
       final String token = tokenUtil.generateToken(authentication);
       return ResponseEntity.ok(new AuthToken(token));
   }

    @GetMapping("/candidatos/{id}")
    public ResponseEntity<Usuario> findByCandidatosId(@PathVariable Long id){
        if(id==null || id<=0)
            return ResponseEntity.badRequest().build();

        Optional<Usuario> usuarioOpt = usuarioService.findByCandidatoId(id);
        if(usuarioOpt.isPresent())
            return ResponseEntity.ok(usuarioOpt.get());

        return ResponseEntity.notFound().build();
    }

  /*  @PutMapping("")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        if(usuario.getId()==null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(usuarioService.save(usuario));

    }*/


    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteById(@PathVariable Long id){
        if(id==null || id<=0)
            return ResponseEntity.badRequest().build();
        if(usuarioService.deleteById(id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Candidato> deleteAll(){
        usuarioService.deleteAll();

        return ResponseEntity.noContent().build();
    }
    /*
    @GetMapping("")
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);
        if(usuarioOpt.isPresent())
            return ResponseEntity.ok(usuarioOpt.get());

        return ResponseEntity.notFound().build();
    }
*/

}
