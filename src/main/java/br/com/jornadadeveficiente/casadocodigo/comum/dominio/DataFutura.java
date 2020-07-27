package br.com.jornadadeveficiente.casadocodigo.comum.dominio;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DataFuturaValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataFutura {

  String message() default "{DataFutura}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
