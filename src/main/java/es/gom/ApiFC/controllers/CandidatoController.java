package es.gom.ApiFC.controllers;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Presencialidad;
import es.gom.ApiFC.services.CandidatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping("")
    public List<Candidato> findAll() {

        return candidatoService.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Candidato> findById(@RequestParam(required = false) Long id){

        Optional<Candidato> candidateOpt = candidatoService.findById(id);
        if(candidateOpt.isPresent())
            return ResponseEntity.ok(candidateOpt.get());

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public List<Candidato> findByAll(@RequestParam(required = false) String pais,
                                     @RequestParam(required = false) String ciudad,
                                     @RequestParam(required = false) Presencialidad presencialidad,
                                     @RequestParam(required = false) Long etiquetasId,
                                     @RequestParam(required = false) Boolean movilidad){

        return candidatoService.findByAll(pais, ciudad, presencialidad, etiquetasId, movilidad);
    }

    @GetMapping("/etiquetas")
    public List<Candidato> findAllByEtiquetaId(@RequestParam Long id){
        return candidatoService.findAllByEtiquetaId(id);
    }

    @GetMapping("/usuarios")
    public List<Candidato> findAllByUsuarioId(@RequestParam Long id){
        return candidatoService.findAllByUsuarioId(id);
    }


    @PostMapping("")
    public ResponseEntity<Candidato> create(@RequestBody Candidato candidato){

        return ResponseEntity.ok(candidatoService.save(candidato));
    }

    @PostMapping("/candidatos")
    public ResponseEntity<List<Candidato>> createAll(List<Candidato> candidatos){
        if (candidatos.isEmpty())
            return ResponseEntity.badRequest().build();
        candidatoService.saveAll(candidatos);
        return ResponseEntity.ok(candidatos);
    }

    @PutMapping("")
    public ResponseEntity<Candidato> update(@RequestBody Candidato candidato){
        if (candidato.getId() == null )
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(candidatoService.save(candidato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Candidato> deleteById(@PathVariable Long id){
        if(id==null || id <= 0)
            return ResponseEntity.badRequest().build();

        if(candidatoService.deleteById(id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Candidato> deleteAll(){
        candidatoService.deleteAll();

        return ResponseEntity.noContent().build();
    }

}
