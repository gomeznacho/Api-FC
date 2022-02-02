package es.gom.ApiFC.repositories;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Presencialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findByNombreCompletoContainingIgnoreCase(String nombre);
    List<Candidato> findByCiudadIgnoreCase(String ciudad);
    List<Candidato> findByPaisIgnoreCase(String pais);
    List<Candidato> findByMovilidad(Boolean movilidad);
    List<Candidato> findByEtiquetasId(Long id);
    List<Candidato> findByUsuarioId(Long id);
    List<Candidato> findByPresencialidad(Presencialidad presencialidad);

    List<Candidato> findByPresencialidadAndMovilidad(Presencialidad presencialidad, Boolean movilidad);

    List<Candidato> findByPaisIgnoreCaseAndCiudadIgnoreCaseAndPresencialidadAndEtiquetasId(String pais, String ciudad, Presencialidad presencialidad, Long id);

    List<Candidato> findByPaisIgnoreCaseAndPresencialidad(String pais, Presencialidad presencialidad);

    List<Candidato> findByCiudadIgnoreCaseAndPresencialidadAndEtiquetasId(String ciudad, Presencialidad presencialidad, Long etiquetasId);

    List<Candidato> findByPresencialidadAndEtiquetasId(Presencialidad presencialidad, Long etiquetasId);

    List<Candidato> findByCiudadIgnoreCaseAndPresencialidad(String ciudad, Presencialidad presencialidad);

    List<Candidato> findByCiudadIgnoreCaseAndEtiquetasId(String ciudad, Long etiquetasId);

    List<Candidato> findByPaisIgnoreCaseAndEtiquetasId(String ciudad, Long etiquetasId);

    List<Candidato> findByPaisIgnoreCaseAndCiudadIgnoreCase(String pais, String ciudad);

    List<Candidato> findByPaisIgnoreCaseAndPresencialidadAndEtiquetasId(String pais, Presencialidad presencialidad, Long etiquetasId);

    List<Candidato> findByPaisIgnoreCaseAndCiudadIgnoreCaseAndEtiquetasId(String pais, String ciudad, Long etiquetasId);

    List<Candidato> findByPaisIgnoreCaseAndCiudadIgnoreCaseAndPresencialidad(String pais, String ciudad, Presencialidad presencialidad);

    List<Candidato> findByPaisIgnoreCaseAndCiudadIgnoreCaseAndPresencialidadAndEtiquetasIdAndMovilidad(String pais, String ciudad, Presencialidad presencialidad, Long etiquetasId, Boolean movilidad);
}