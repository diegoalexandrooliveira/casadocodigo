package br.com.jornadadeveficiente.casadocodigo.categoria.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {


  Optional<Categoria> findByNome(String nome);
}
