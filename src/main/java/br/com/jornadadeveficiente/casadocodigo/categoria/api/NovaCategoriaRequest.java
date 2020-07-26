package br.com.jornadadeveficiente.casadocodigo.categoria.api;

import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class NovaCategoriaRequest {

  @NotBlank
  @UniqueValue(entity = "Categoria", fieldName = "nome")
  private String nome;

  public NovaCategoriaRequest(@NotBlank String nome) {
    this.nome = nome;
  }

  public Categoria toModel() {
    return new Categoria(nome);
  }

}
