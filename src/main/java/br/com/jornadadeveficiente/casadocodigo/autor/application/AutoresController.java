package br.com.jornadadeveficiente.casadocodigo.autor.application;

import br.com.jornadadeveficiente.casadocodigo.autor.domain.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/autores")
public class AutoresController {

  private AutorRepository autorRepository;

  @Autowired
  public AutoresController(AutorRepository autorRepository) {
    this.autorRepository = autorRepository;
  }

  @PostMapping
  public ResponseEntity<AutorRequest> criaAutor(@Valid @RequestBody AutorRequest autor) {
    autorRepository.save(autor.toModel());
    return ResponseEntity.ok(autor);
  }

}
