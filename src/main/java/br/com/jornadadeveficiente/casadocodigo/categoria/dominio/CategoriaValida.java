package br.com.jornadadeveficiente.casadocodigo.categoria.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CategoriaValidaValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoriaValida {

  String message() default "{RegistroNaoEncontrado}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
