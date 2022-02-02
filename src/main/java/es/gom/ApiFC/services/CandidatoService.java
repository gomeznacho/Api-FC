package es.gom.ApiFC.services;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Presencialidad;

import java.util.List;
import java.util.Optional;

public interface CandidatoService {

    Optional<Candidato> findById(Long id);

    List<Candidato> findAllByNombre(String nombre);

    List<Candidato> findAll();

    /*List<Candidato> findByIds(List<Long> ids);*/


    List<Candidato> findAllByEtiquetaId(Long id);

    List<Candidato> findAllByUsuarioId(Long id);


    Candidato save(Candidato candidato);

    List<Candidato> saveAll(List<Candidato> candidatos);

    boolean deleteById(Long id);

    boolean deleteAll();

    /*boolean isValid(Candidato candidato);*/

    List<Candidato> findByAll(String pais, String ciudad, Presencialidad presencialidad, Long etiquetasId, Boolean movilidad);



}

