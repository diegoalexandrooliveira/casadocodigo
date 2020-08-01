package br.com.jornadadeveficiente.casadocodigo.compra.api;


import br.com.jornadadeveficiente.casadocodigo.compra.dominio.PaisDaCompraValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/compras")
@Validated
public class CompraController {

  @Autowired
  private PaisDaCompraValidator paisDaCompraValidator;

  @InitBinder
  public void init(WebDataBinder dataBinder) {
    dataBinder.addValidators(paisDaCompraValidator);
  }


  @PostMapping
  public ResponseEntity<Void> salvar(@Valid @RequestBody NovaCompraRequest novaCompraRequest) {
    return ResponseEntity.ok().build();
  }


}
