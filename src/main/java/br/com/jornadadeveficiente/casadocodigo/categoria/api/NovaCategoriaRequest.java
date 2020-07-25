package br.com.jornadadeveficiente.casadocodigo.categoria.api;

import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class NovaCategoriaRequest {

  @NotBlank
  private String nome;

  public NovaCategoriaRequest(@NotBlank String nome) {
    this.nome = nome;
  }

  public Categoria toModel() {
    return new Categoria(nome);
  }

}
