package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class EntidadeValidaValidator implements ConstraintValidator<EntidadeValida, Object> {


  private Class entity;
  @Autowired
  private EntityManager manager;

  @Override
  public void initialize(EntidadeValida constraintAnnotation) {
    entity = constraintAnnotation.entity();
  }

  @Override
  public boolean isValid(Object id, ConstraintValidatorContext constraintValidatorContext) {
    if (Objects.isNull(id)) {
      return true;
    }
    return Objects.nonNull(manager.find(entity, id));
  }
}
