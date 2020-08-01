package br.com.jornadadeveficiente.casadocodigo.pais.api;


import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;

import javax.validation.constraints.NotEmpty;

public class PaisRequest {

  @NotEmpty
  @UniqueValue(fieldName = "nome", entity = "Pais")
  private String nome;

  public String getNome() {
    return nome;
  }

  public Pais toModel() {
    return new Pais(null, nome);
  }


}
