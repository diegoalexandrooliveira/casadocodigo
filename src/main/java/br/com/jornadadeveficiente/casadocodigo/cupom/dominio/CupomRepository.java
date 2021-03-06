package br.com.jornadadeveficiente.casadocodigo.cupom.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, UUID> {

  Cupom getByCodigo(String codigo);
}
