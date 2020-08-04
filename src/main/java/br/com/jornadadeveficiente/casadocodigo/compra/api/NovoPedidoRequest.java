package br.com.jornadadeveficiente.casadocodigo.compra.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NovoPedidoRequest {

  @NotNull
  @Min(1)
  private BigDecimal total;

  @NotNull
  @Size(min = 1)
  @Valid
  private List<ItemPedidoRequest> itens;


}
