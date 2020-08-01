package br.com.jornadadeveficiente.casadocodigo.pais.dominio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaisRepository extends CrudRepository<Pais, UUID> {

  Optional<Pais> findByNome(String nome);

}
