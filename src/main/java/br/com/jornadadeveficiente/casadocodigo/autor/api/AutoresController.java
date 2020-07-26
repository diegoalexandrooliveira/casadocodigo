package br.com.jornadadeveficiente.casadocodigo.autor.api;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.dominio.AutorRepository;
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
  public ResponseEntity<AutorResponse> criaAutor(@Valid @RequestBody NovoAutorRequest novoAutorRequest) {
    Autor autor = novoAutorRequest.toModel();
    autorRepository.save(autor);
    return ResponseEntity.ok(AutorResponse.fromModel(autor));
  }

}
