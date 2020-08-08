package br.com.jornadadeveficiente.casadocodigo.cupom.api;

import br.com.jornadadeveficiente.casadocodigo.cupom.dominio.Cupom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CupomDTO {

  private UUID id;
  private String codigo;
  private BigDecimal percentual;
  private LocalDateTime validade;

  public static CupomDTO of(Cupom cupom) {
    return new CupomDTO(cupom.getId(), cupom.getCodigo(), cupom.getPercentual(), cupom.getValidade());
  }
}
