package br.com.jornadadeveficiente.casadocodigo.categoria.dominio;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.UUID;

public class CategoriaValidaValidator implements ConstraintValidator<CategoriaValida, Object> {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    if (Objects.isNull(o)) {
      return false;
    }
    return categoriaRepository.findById(((UUID) o)).isPresent();
  }
}
