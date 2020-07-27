package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DataFuturaValidator implements ConstraintValidator<DataFutura, Object> {

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    if (o instanceof LocalDate) {
      return ((LocalDate) o).isAfter(LocalDate.now());
    } else {
      return false;
    }

  }
}
