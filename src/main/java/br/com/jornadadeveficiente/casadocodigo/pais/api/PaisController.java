package br.com.jornadadeveficiente.casadocodigo.pais.api;

import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/paises")
@Validated
public class PaisController {

  @Autowired
  private PaisRepository paisRepository;


  @PostMapping
  public ResponseEntity<PaisDTO> salvar(@Valid @RequestBody PaisRequest paisRequest) {
    Pais pais = paisRequest.toModel();
    paisRepository.save(pais);
    return ResponseEntity.ok(PaisDTO.fromModel(pais));
  }


}
