package br.com.jornadadeveficiente.casadocodigo.autor.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {AutorValidoValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutorValido {

  String message() default "{RegistroNaoEncontrado}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
