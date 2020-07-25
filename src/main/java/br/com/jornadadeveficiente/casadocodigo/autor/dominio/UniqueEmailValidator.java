package br.com.jornadadeveficiente.casadocodigo.autor.dominio;

import br.com.jornadadeveficiente.casadocodigo.autor.api.NovoAutorRequest;
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
    return NovoAutorRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {

    if (errors.hasErrors()) {
      return;
    }

    NovoAutorRequest novoAutorRequest = (NovoAutorRequest) o;

    Optional<Autor> possivelAutor = autorRepository.findByEmail(novoAutorRequest.getEmail());
    if (possivelAutor.isPresent()) {
      errors.rejectValue("email", "EmailDuplicado", new Object[]{novoAutorRequest.getEmail()}, null);
    }
  }
}
