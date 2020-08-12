package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class EntidadeValidaValidator implements ConstraintValidator<EntidadeValida, Object> {


  private Class entity;
  private String attribute;

  @Autowired
  private EntityManager manager;

  @Override
  public void initialize(EntidadeValida constraintAnnotation) {
    entity = constraintAnnotation.entity();
    attribute = constraintAnnotation.attribute();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    if (Objects.isNull(value)) {
      return true;
    }
    Query query = manager.createQuery(String.format("select count(*) from %s e where e.%s = ?1", entity.getName(), attribute));
    query.setParameter(1, value);
    return Long.parseLong(query.getSingleResult().toString()) != 0;
  }
}
