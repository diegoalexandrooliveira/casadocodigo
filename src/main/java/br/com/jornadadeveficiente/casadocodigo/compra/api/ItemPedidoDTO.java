package br.com.jornadadeveficiente.casadocodigo.compra.api;

import br.com.jornadadeveficiente.casadocodigo.compra.dominio.ItemPedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ItemPedidoDTO {
  private String nomeLivro;
  private String nomeAutor;
  private Long quantidade;
  private BigDecimal preco;

  public static ItemPedidoDTO from(ItemPedido item) {
    return new ItemPedidoDTO(item.getTituloLivro(), item.getNomeAutorLivro(), item.getQuantidade(), item.getPreco());
  }
}
