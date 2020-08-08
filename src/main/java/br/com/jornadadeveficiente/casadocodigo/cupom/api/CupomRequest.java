package br.com.jornadadeveficiente.casadocodigo.cupom.api;

import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import br.com.jornadadeveficiente.casadocodigo.cupom.dominio.Cupom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CupomRequest {

  @NotBlank
  @UniqueValue(entity = "Cupom", fieldName = "codigo")
  private String codigo;
  @Positive
  @NotNull
  private BigDecimal percentual;
  @NotNull
  @Future
  private LocalDateTime validade;

  public Cupom entidade(){
    return new Cupom(codigo, percentual, validade);
  }
}
