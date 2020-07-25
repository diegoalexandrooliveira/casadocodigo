package br.com.jornadadeveficiente.casadocodigo.categoria.api;

import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CategoriaResponse {

  private UUID id;
  private String nome;

  public static CategoriaResponse fromModel(Categoria categoria) {
    return new CategoriaResponse(categoria.getId(), categoria.getNome());
  }


}
