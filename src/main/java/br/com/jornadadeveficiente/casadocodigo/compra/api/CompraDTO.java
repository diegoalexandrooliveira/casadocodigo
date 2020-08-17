package br.com.jornadadeveficiente.casadocodigo.compra.api;

import br.com.jornadadeveficiente.casadocodigo.compra.dominio.Compra;
import br.com.jornadadeveficiente.casadocodigo.compra.dominio.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class CompraDTO {

  private UUID id;
  private String email;
  private String nome;
  private String sobrenome;
  private String documento;
  private String endereco;
  private String complemento;
  private String cidade;
  private String nomePais;
  private String nomeEstado;
  private String telefone;
  private String cep;
  private BigDecimal total;
  private Status status;
  private boolean existeCupom;
  private BigDecimal valorCupom;
  private List<ItemPedidoDTO> itens;

  public static CompraDTO from(Compra compra) {

    List<ItemPedidoDTO> itens = compra.getItens()
      .stream()
      .map(ItemPedidoDTO::from)
      .collect(Collectors.toList());

    return CompraDTO.builder()
      .id(compra.getId())
      .email(compra.getEmail())
      .nome(compra.getNome())
      .sobrenome(compra.getSobrenome())
      .documento(compra.getDocumento())
      .endereco(compra.getEndereco())
      .complemento(compra.getComplemento())
      .cidade(compra.getCidade())
      .nomePais(compra.getNomePais())
      .nomeEstado(compra.getNomeEstado())
      .telefone(compra.getTelefone())
      .cep(compra.getCep())
      .status(compra.getStatus())
      .total(compra.getTotal())
      .existeCupom(compra.existeCupom())
      .valorCupom(compra.valorCupom())
      .itens(itens)
      .build();
  }
}
