package br.com.jornadadeveficiente.casadocodigo.compra.api;


import br.com.jornadadeveficiente.casadocodigo.compra.dominio.Compra;
import br.com.jornadadeveficiente.casadocodigo.compra.dominio.CompraRepository;
import br.com.jornadadeveficiente.casadocodigo.compra.dominio.EstadoDaCompraValidator;
import br.com.jornadadeveficiente.casadocodigo.compra.dominio.TotalDaCompraValidator;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.EstadoRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/compras")
@Validated
public class CompraController {

  @Autowired
  private EstadoDaCompraValidator estadoDaCompraValidator;
  @Autowired
  private TotalDaCompraValidator totalDaCompraValidator;
  @Autowired
  private CompraRepository compraRepository;
  @Autowired
  private EstadoRepository estadoRepository;
  @Autowired
  private PaisRepository paisRepository;
  @Autowired
  private LivroRepository livroRepository;

  @InitBinder
  public void init(WebDataBinder dataBinder) {
    dataBinder.addValidators(estadoDaCompraValidator, totalDaCompraValidator);
  }


  @PostMapping
  public ResponseEntity<Void> salvar(@Valid @RequestBody NovaCompraRequest novaCompraRequest) {
    Compra compra = novaCompraRequest.entidade(estadoRepository, paisRepository, livroRepository);
    compraRepository.save(compra);
    return ResponseEntity.created(URI.create(String.format("/api/compras/%s", compra.getId()))).build();
  }


}
