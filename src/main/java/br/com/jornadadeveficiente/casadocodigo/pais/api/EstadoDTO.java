package br.com.jornadadeveficiente.casadocodigo.pais.api;

import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Estado;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EstadoDTO {

  private UUID id;
  private String nome;
  private PaisDTO pais;

  public static EstadoDTO fromModel(Estado estado) {
    return new EstadoDTO(estado.getId(), estado.getNome(), PaisDTO.fromModel(estado.getPais()));
  }

}
