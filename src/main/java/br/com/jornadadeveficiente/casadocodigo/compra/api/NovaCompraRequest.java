package br.com.jornadadeveficiente.casadocodigo.compra.api;

import br.com.jornadadeveficiente.casadocodigo.comum.dominio.EntidadeValida;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

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
  @NotNull
  @EntidadeValida(message = "{PaisNaoEncontrado}", entity = Pais.class)
  private UUID pais;

  private UUID estado;

  @NotEmpty
  private String telefone;
  @NotEmpty
  private String cep;

  @NotNull
  @Valid
  private NovoPedidoRequest pedido;
}
