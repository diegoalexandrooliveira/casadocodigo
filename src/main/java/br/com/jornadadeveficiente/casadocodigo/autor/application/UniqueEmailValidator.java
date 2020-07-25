package br.com.jornadadeveficiente.casadocodigo.autor.application;

import br.com.jornadadeveficiente.casadocodigo.autor.domain.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.domain.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UniqueEmailValidator implements Validator {

  private AutorRepository autorRepository;

  @Autowired
  public UniqueEmailValidator(AutorRepository autorRepository) {
    this.autorRepository = autorRepository;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return AutorRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {

    if (errors.hasErrors()) {
      return;
    }

    AutorRequest autorRequest = (AutorRequest) o;

    Optional<Autor> possivelAutor = autorRepository.findByEmail(autorRequest.getEmail());
    if (possivelAutor.isPresent()) {
      errors.rejectValue("email", "EmailDuplicado", new Object[]{autorRequest.getEmail()}, null);
    }
  }
}
