package br.com.jornadadeveficiente.casadocodigo.cupom.api;

import br.com.jornadadeveficiente.casadocodigo.cupom.dominio.Cupom;
import br.com.jornadadeveficiente.casadocodigo.cupom.dominio.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/cupons")
@Validated
public class CupomController {


  @Autowired
  private CupomRepository cupomRepository;

  @PostMapping
  public ResponseEntity<CupomDTO> salvar(@Valid @RequestBody CupomRequest cupomRequest){
    Cupom cupom = cupomRequest.entidade();
    cupomRepository.save(cupom);
    return ResponseEntity.ok(CupomDTO.of(cupom));
  }
}
