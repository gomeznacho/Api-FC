package es.gom.ApiFC.services;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Etiqueta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface EtiquetaService {
    Etiqueta save(Etiqueta etiqueta);

    Optional<Etiqueta> findById(Long id);

    List<Etiqueta> findAll();



    boolean deleteById(Long id);

    boolean deleteAll();
}
