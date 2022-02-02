package es.gom.ApiFC.controllers;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Usuario;
import es.gom.ApiFC.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    //TODO: registro y login
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

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


    @GetMapping("/candidatos/{id}")
    public ResponseEntity<Usuario> findByCandidatosId(@PathVariable Long id){
        if(id==null || id<=0)
            return ResponseEntity.badRequest().build();

        Optional<Usuario> usuarioOpt = usuarioService.findByCandidatoId(id);
        if(usuarioOpt.isPresent())
            return ResponseEntity.ok(usuarioOpt.get());

        return ResponseEntity.notFound().build();
    }

    @PutMapping("")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        if(usuario.getId()==null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(usuarioService.save(usuario));

    }

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

}
