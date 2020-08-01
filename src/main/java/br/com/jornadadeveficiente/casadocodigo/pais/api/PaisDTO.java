package br.com.jornadadeveficiente.casadocodigo.pais.api;


import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaisDTO {

  private UUID id;
  private String nome;

  public static PaisDTO fromModel(Pais pais) {
    return new PaisDTO(pais.getId(), pais.getNome());
  }


}
