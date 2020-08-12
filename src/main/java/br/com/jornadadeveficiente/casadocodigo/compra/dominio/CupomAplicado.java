package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.cupom.dominio.Cupom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CupomAplicado {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cupom_id")
  private Cupom cupom;
  @Positive
  @NotNull
  private BigDecimal percentual;
  @NotNull
  @Future
  private LocalDateTime validade;

  public CupomAplicado(Cupom cupom) {
    Assert.notNull(cupom, "Cupom não pode ser nulo.");
    this.cupom = cupom;
    this.percentual = cupom.getPercentual();
    this.validade = cupom.getValidade();
  }
}
