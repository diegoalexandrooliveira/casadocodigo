package br.com.jornadadeveficiente.casadocodigo.autor.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.UUID;

public class AutorValidoValidator implements ConstraintValidator<AutorValido, Object> {

  @Autowired
  private AutorRepository autorRepository;

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    if (Objects.isNull(o)) {
      return false;
    }
    return autorRepository.findById(((UUID) o)).isPresent();
  }
}
