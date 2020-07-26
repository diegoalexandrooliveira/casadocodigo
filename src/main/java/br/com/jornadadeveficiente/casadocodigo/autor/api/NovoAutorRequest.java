package br.com.jornadadeveficiente.casadocodigo.autor.api;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class NovoAutorRequest {

  @NotBlank
  private String nome;

  @NotBlank
  private String descricao;

  @Email
  @NotBlank
  @UniqueValue(fieldName = "email", entity = "autores")
  private String email;

  public NovoAutorRequest(@NotBlank String nome, @Size @NotBlank String descricao, @Email @NotBlank String email) {
    this.nome = nome;
    this.descricao = descricao;
    this.email = email;
  }

  public Autor toModel() {
    return new Autor(nome, descricao, email);
  }
}
