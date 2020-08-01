package br.com.jornadadeveficiente.casadocodigo.compra.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NovaCompraRequest {

  @Email
  @NotEmpty
  private String email;
  @NotEmpty
  private String nome;
  @NotEmpty
  private String sobrenome;
  @NotEmpty
  @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[\\.]?[-]?[0-9]{2})")
  private String documento;
  @NotEmpty
  private String endereco;
  @NotEmpty
  private String complemento;
  @NotEmpty
  private String cidade;
  @NotEmpty
  private String pais;

  private String estado;

  @NotEmpty
  private String telefone;
  @NotEmpty
  private String cep;
}
