package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EntidadeValidaValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntidadeValida {

  String message() default "{IdValido}";

  Class entity();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
