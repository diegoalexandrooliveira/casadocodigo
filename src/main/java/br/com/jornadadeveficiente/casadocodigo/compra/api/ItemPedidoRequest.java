package br.com.jornadadeveficiente.casadocodigo.compra.api;

import br.com.jornadadeveficiente.casadocodigo.comum.dominio.EntidadeValida;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ItemPedidoRequest {

  @NotNull
  @EntidadeValida(entity = Livro.class, message = "{LivroNaoEncontrado}")
  private UUID idLivro;

  @NotNull
  @Min(1)
  private Long quantidade;
}
