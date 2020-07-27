package br.com.jornadadeveficiente.casadocodigo.autor.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {AutorValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaAutor {

  String message() default "{ValidaAutor}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
