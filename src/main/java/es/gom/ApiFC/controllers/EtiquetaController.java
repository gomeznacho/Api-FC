package es.gom.ApiFC.controllers;

import es.gom.ApiFC.entities.Etiqueta;
import es.gom.ApiFC.services.EtiquetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {
    private final EtiquetaService etiquetaService;

    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @GetMapping("")
    public List<Etiqueta> findAll(){
        return etiquetaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> findById(@PathVariable Long id){
        Optional<Etiqueta> etiquetaOpt = etiquetaService.findById(id);
        if(etiquetaOpt.isPresent())
            return ResponseEntity.ok(etiquetaOpt.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<Etiqueta> create(@RequestBody Etiqueta etiqueta){
        if(etiqueta.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(etiquetaService.save(etiqueta));
    }

    @PutMapping("/")
    public ResponseEntity<Etiqueta> update(@RequestBody Etiqueta etiqueta){
        if(etiqueta.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(etiquetaService.save(etiqueta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Etiqueta> deleteById(@PathVariable Long id){
        if(id == null || id <= 0)
            return ResponseEntity.badRequest().build();
        if(etiquetaService.deleteById(id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
