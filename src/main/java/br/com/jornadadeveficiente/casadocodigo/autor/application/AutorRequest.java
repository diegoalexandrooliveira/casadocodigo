package br.com.jornadadeveficiente.casadocodigo.autor.application;

import br.com.jornadadeveficiente.casadocodigo.autor.domain.Autor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class AutorRequest {

  @NotBlank
  private String nome;

  @Size
  @NotBlank
  private String descricao;

  @Email
  @NotBlank
  private String email;

  public AutorRequest(@NotBlank String nome, @Size @NotBlank String descricao, @Email @NotBlank String email) {
    this.nome = nome;
    this.descricao = descricao;
    this.email = email;
  }

  public Autor toModel() {
    return new Autor(nome, descricao, email);
  }


}
