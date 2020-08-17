package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompraRepository extends JpaRepository<Compra, UUID> {

  @Query("select c from Compra c join fetch c.pais left join fetch c.estado left join fetch c.cupomAplicado.cupom join fetch c.itens i join fetch i.livro where c.id = :id")
  Compra findCompraById(UUID id);
}
