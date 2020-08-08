package br.com.jornadadeveficiente.casadocodigo.cupom.dominio;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Entity
public class Cupom {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @NotBlank
  private String codigo;
  @Positive
  @NotNull
  private BigDecimal percentual;
  @NotNull
  @Future
  private LocalDateTime validade;

  public Cupom(@NotBlank String codigo, @Positive @NotNull BigDecimal percentual, @NotNull @Future LocalDateTime validade) {
    this.codigo = codigo;
    this.percentual = percentual;
    this.validade = validade;
  }
}
