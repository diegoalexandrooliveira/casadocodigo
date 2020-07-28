package br.com.jornadadeveficiente.casadocodigo.livro.api;

import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

  @GetMapping
  public ResponseEntity<List<LivroDTO>> recuperaTodos() {
    List<LivroDTO> todosLivros = livroRepository.findAll()
      .stream()
      .map(LivroDTO::fromModel)
      .collect(Collectors.toList());
    return ResponseEntity.ok(todosLivros);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<LivroDTO> recuperaPorId(@PathVariable("id") UUID id) {
    return livroRepository.findById(id)
      .map(livro -> ResponseEntity.ok(LivroDTO.fromModel(livro)))
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
