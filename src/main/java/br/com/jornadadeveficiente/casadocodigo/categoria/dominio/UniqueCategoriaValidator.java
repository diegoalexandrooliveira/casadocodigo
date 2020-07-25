package br.com.jornadadeveficiente.casadocodigo.categoria.dominio;

import br.com.jornadadeveficiente.casadocodigo.categoria.api.NovaCategoriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UniqueCategoriaValidator implements Validator {

  private CategoriaRepository categoriaRepository;

  @Autowired
  public UniqueCategoriaValidator(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return NovaCategoriaRequest.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {

    if (errors.hasErrors()) {
      return;
    }

    NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) o;

    Optional<Categoria> possivelAutor = categoriaRepository.findByNome(novaCategoriaRequest.getNome());
    if (possivelAutor.isPresent()) {
      errors.rejectValue("nome", "CategoriaDuplicada", new Object[]{novaCategoriaRequest.getNome()}, null);
    }
  }
}
