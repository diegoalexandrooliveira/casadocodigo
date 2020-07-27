package br.com.jornadadeveficiente.casadocodigo.livro.api;

import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/livros")
@Validated
public class LivroController {


  private LivroRepository livroRepository;

  @Autowired
  public LivroController(LivroRepository livroRepository) {
    this.livroRepository = livroRepository;
  }

  @PostMapping
  public ResponseEntity<LivroDTO> salva(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {
    Livro livro = novoLivroRequest.toModel();
    livroRepository.save(livro);
    return ResponseEntity.ok(LivroDTO.fromModel(livro));

  }
}
