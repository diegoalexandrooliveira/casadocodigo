package br.com.jornadadeveficiente.casadocodigo.autor.api;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.dominio.AutorRepository;
import br.com.jornadadeveficiente.casadocodigo.autor.dominio.UniqueEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/autores")
public class AutoresController {

  private AutorRepository autorRepository;
  private UniqueEmailValidator uniqueEmailValidator;

  @Autowired
  public AutoresController(AutorRepository autorRepository, UniqueEmailValidator uniqueEmailValidator) {
    this.autorRepository = autorRepository;
    this.uniqueEmailValidator = uniqueEmailValidator;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(uniqueEmailValidator);
  }

  @PostMapping
  public ResponseEntity<AutorResponse> criaAutor(@Valid @RequestBody NovoAutorRequest novoAutorRequest) {
    Autor autor = novoAutorRequest.toModel();
    autorRepository.save(autor);
    return ResponseEntity.ok(AutorResponse.fromModel(autor));
  }

}