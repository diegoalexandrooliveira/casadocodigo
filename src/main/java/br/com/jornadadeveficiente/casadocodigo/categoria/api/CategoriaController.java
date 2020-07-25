package br.com.jornadadeveficiente.casadocodigo.categoria.api;


import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.CategoriaRepository;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.UniqueCategoriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/categorias")
public class CategoriaController {


  private CategoriaRepository categoriaRepository;
  private UniqueCategoriaValidator uniqueCategoriaValidator;

  @Autowired
  public CategoriaController(CategoriaRepository categoriaRepository, UniqueCategoriaValidator uniqueCategoriaValidator) {
    this.categoriaRepository = categoriaRepository;
    this.uniqueCategoriaValidator = uniqueCategoriaValidator;
  }

  @InitBinder
  public void init(WebDataBinder binder) {
    binder.addValidators(uniqueCategoriaValidator);
  }

  @PostMapping
  public ResponseEntity<CategoriaResponse> salvar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
    Categoria categoria = novaCategoriaRequest.toModel();
    categoriaRepository.save(categoria);
    return ResponseEntity.ok(CategoriaResponse.fromModel(categoria));
  }
}
