package br.com.jornadadeveficiente.casadocodigo.compra.api;


import br.com.jornadadeveficiente.casadocodigo.compra.dominio.*;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.EstadoRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.PaisRepository;
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
  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @InitBinder
  public void init(WebDataBinder dataBinder) {
    dataBinder.addValidators(estadoDaCompraValidator, totalDaCompraValidator);
  }


  @PostMapping
  public ResponseEntity<Void> salvar(@Valid @RequestBody NovaCompraRequest novaCompraRequest) {
    Compra compra = novaCompraRequest.entidade(estadoRepository, paisRepository, livroRepository);
    compraRepository.save(compra);
    compra.getItens().forEach(itemPedidoRepository::save);
    return ResponseEntity.ok().build();
  }


}
