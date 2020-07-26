package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

  String message() default "{UniqueValue}";

  String fieldName();

  String entity();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
