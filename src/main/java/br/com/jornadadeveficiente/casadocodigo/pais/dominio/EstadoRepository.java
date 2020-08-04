package br.com.jornadadeveficiente.casadocodigo.pais.dominio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, UUID> {

  boolean existsByPaisId(UUID id);

}
