package br.com.jornadadeveficiente.casadocodigo.categoria.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CategoriaValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaCategoria {

  String message() default "{ValidaCategoria}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
