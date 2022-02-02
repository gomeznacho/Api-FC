package es.gom.ApiFC.repositories;

import es.gom.ApiFC.entities.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
   /* List<Etiqueta> findByCandidatosId(Long id);*/

}
