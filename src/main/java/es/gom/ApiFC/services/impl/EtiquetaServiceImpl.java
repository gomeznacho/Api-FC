package es.gom.ApiFC.services.impl;

import es.gom.ApiFC.entities.Etiqueta;
import es.gom.ApiFC.repositories.EtiquetaRepository;
import es.gom.ApiFC.services.EtiquetaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {
    private final EtiquetaRepository etiquetaRepository;

    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository) {
        this.etiquetaRepository = etiquetaRepository;
    }

    @Override
    public Etiqueta save(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    @Override
    public Optional<Etiqueta> findById(Long id) {
        if(id == null || id<= 0)
            return Optional.empty();
        return etiquetaRepository.findById(id);
    }

    @Override
    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || id<= 0)
            return false;

        etiquetaRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        etiquetaRepository.deleteAll();
        return true;
    }
}
