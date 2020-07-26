package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {


  private String attribute;
  private String entityName;
  @PersistenceContext
  private EntityManager manager;

  @Override
  public void initialize(UniqueValue constraintAnnotation) {
    attribute = constraintAnnotation.fieldName();
    entityName = constraintAnnotation.entity();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
    Query query = manager.createQuery(String.format("select 1 from %s where %s = :value", entityName, attribute));
    query.setParameter("value", o);
    List<?> resultList = query.getResultList();
    Assert.state(resultList.size() <= 1, "Foi encontrado mais de um " + entityName + " com o atributo " + attribute + " igual a " + o.toString());
    return resultList.isEmpty();
  }
}
