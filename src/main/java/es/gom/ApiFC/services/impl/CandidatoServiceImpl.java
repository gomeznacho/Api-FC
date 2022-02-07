package es.gom.ApiFC.services.impl;

import es.gom.ApiFC.entities.Candidato;
import es.gom.ApiFC.entities.Presencialidad;
import es.gom.ApiFC.repositories.CandidatoRepository;
import es.gom.ApiFC.services.CandidatoService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepository candidatoRepository;

    public CandidatoServiceImpl(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @Override
    public Optional<Candidato> findById(Long id) {
        if(id ==null || id<= 0)
            return Optional.empty();

        return candidatoRepository.findById(id);
    }

    @Override
    public List<Candidato> findAllByNombre(String nombre) {
        if(nombre == null || nombre.isEmpty())
            return new ArrayList<>();

       return candidatoRepository.findByNombreCompletoContainingIgnoreCase(nombre);
    }

    @Override
    public List<Candidato> findAll() {
        return candidatoRepository.findAll();
    }


    @Override
    public List<Candidato> findAllByEtiquetaId(Long id) {
        return candidatoRepository.findAllByEtiquetasId(id);
    }

    @Override
    public List<Candidato> findAllByUsuarioId(Long id) {
        return candidatoRepository.findByUsuarioId(id);

    }


    @Override
    public Candidato save(Candidato candidato) {
        if(candidato == null || !isValid(candidato))
            throw new IllegalArgumentException("Formato del candidato incorrecto");

        return candidatoRepository.save(candidato);
    }

    @Override
    public List<Candidato> saveAll(List<Candidato> candidatos) {
        if(candidatos.isEmpty())
            return new ArrayList<>();

        return candidatoRepository.saveAll(candidatos);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !candidatoRepository.existsById(id))
            return false;

        candidatoRepository.deleteById(id);

        return true;
    }

    @Override
    public boolean deleteAll() {
        candidatoRepository.deleteAll();
        return true;
    }

    @Override
    public List<Candidato> findByAll(String pais, String ciudad, Presencialidad presencialidad, Long etiquetasId, Boolean movilidad) {
        if(movilidad == null) {
            if ((etiquetasId == null) && (ciudad != null && pais != null && presencialidad != null))
                return candidatoRepository.findByPaisIgnoreCaseAndCiudadIgnoreCaseAndPresencialidad(pais, ciudad, presencialidad);

            if (pais == null && ciudad == null && presencialidad == null && etiquetasId == null)
                return candidatoRepository.findAll();

            if ((ciudad != null && presencialidad != null) && (etiquetasId == null && pais == null))
                return candidatoRepository.findByCiudadIgnoreCaseAndPresencialidad(ciudad, presencialidad);

            if ((presencialidad == null && etiquetasId == null) && (ciudad != null && pais != null))
                return candidatoRepository.findByPaisIgnoreCaseAndCiudadIgnoreCase(pais, ciudad);

            if ((pais != null && presencialidad != null) && (ciudad == null && etiquetasId == null))
                return candidatoRepository.findByPaisIgnoreCaseAndPresencialidad(pais, presencialidad);

            if (pais == null) {
                if (ciudad == null) {
                    if (etiquetasId == null) {
                        return candidatoRepository.findByPresencialidad(presencialidad);
                    }
                    return candidatoRepository.findByPresencialidadAndEtiquetasId(presencialidad, etiquetasId);
                }
                return candidatoRepository.findByCiudadIgnoreCaseAndPresencialidadAndEtiquetasId(ciudad, presencialidad, etiquetasId);
            }

            if (ciudad == null) {
                if (presencialidad == null) {
                    if (etiquetasId == null) {
                        return candidatoRepository.findByPaisIgnoreCase(pais);
                    }
                    return candidatoRepository.findByPaisIgnoreCaseAndEtiquetasId(pais, etiquetasId);
                }
                return candidatoRepository.findByPaisIgnoreCaseAndPresencialidadAndEtiquetasId(pais, presencialidad, etiquetasId);
            }
            if (presencialidad == null) {
                if (pais == null) {
                    if (etiquetasId == null) {
                        return candidatoRepository.findByCiudadIgnoreCase(ciudad);
                    }
                    return candidatoRepository.findByCiudadIgnoreCaseAndEtiquetasId(ciudad, etiquetasId);
                }
                return candidatoRepository.findByPaisIgnoreCaseAndCiudadIgnoreCaseAndEtiquetasId(pais, ciudad, etiquetasId);
            }
            return candidatoRepository.findByPaisIgnoreCaseAndCiudadIgnoreCaseAndPresencialidadAndEtiquetasId(pais, ciudad, presencialidad, etiquetasId);
        }
        if(pais==null && ciudad==null && etiquetasId==null && presencialidad!=null)
            return candidatoRepository.findByPresencialidadAndMovilidad(presencialidad, movilidad);

        if(pais==null && ciudad==null && etiquetasId==null && presencialidad==null)
            return candidatoRepository.findByMovilidad(movilidad);
        return candidatoRepository.findByPaisIgnoreCaseAndCiudadIgnoreCaseAndPresencialidadAndEtiquetasIdAndMovilidad(pais, ciudad, presencialidad, etiquetasId, movilidad);
    }


    private boolean isValid(Candidato candidato){
        return !candidato.getNombreCompleto().isEmpty() && !candidato.getEmail().isEmpty() &&
                !candidato.getCiudad().isEmpty() && !candidato.getPais().isEmpty() && !candidato.getTelefono().isEmpty();
    }
}
