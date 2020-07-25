package br.com.jornadadeveficiente.casadocodigo.autor.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

  Optional<Autor> findByEmail(String email);
}