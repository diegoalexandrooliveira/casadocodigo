package br.com.jornadadeveficiente.casadocodigo.pais.api;

import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Estado;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.EstadoRepository;
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
@RequestMapping(path = "/api/estados")
@Validated
public class EstadoController {


  @Autowired
  private EstadoRepository estadoRepository;
  @Autowired
  private PaisRepository paisRepository;


  @PostMapping
  public ResponseEntity<EstadoDTO> salvar(@Valid @RequestBody EstadoRequest estadoRequest) {
    Estado estado = estadoRequest.toModel(paisRepository);
    estadoRepository.save(estado);
    return ResponseEntity.ok(EstadoDTO.fromModel(estado));
  }


}
