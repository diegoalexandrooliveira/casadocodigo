package br.com.jornadadeveficiente.casadocodigo.categoria.api;


import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/categorias")
public class CategoriaController {


  private CategoriaRepository categoriaRepository;

  @Autowired
  public CategoriaController(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }


  @PostMapping
  public ResponseEntity<CategoriaResponse> salvar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
    Categoria categoria = novaCategoriaRequest.toModel();
    categoriaRepository.save(categoria);
    return ResponseEntity.ok(CategoriaResponse.fromModel(categoria));
  }
}
