package br.com.jornadadeveficiente.casadocodigo.compra.api;

import br.com.jornadadeveficiente.casadocodigo.compra.dominio.Compra;
import br.com.jornadadeveficiente.casadocodigo.compra.dominio.ItemPedido;
import br.com.jornadadeveficiente.casadocodigo.comum.dominio.EntidadeValida;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Estado;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.EstadoRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.PaisRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NovaCompraRequest {

  @Email
  @NotEmpty
  private String email;
  @NotEmpty
  private String nome;
  @NotEmpty
  private String sobrenome;
  @NotEmpty
  @Pattern(regexp = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[\\.]?[-]?[0-9]{2})")
  private String documento;
  @NotEmpty
  private String endereco;
  @NotEmpty
  private String complemento;
  @NotEmpty
  private String cidade;
  @NotNull
  @EntidadeValida(message = "{PaisNaoEncontrado}", entity = Pais.class)
  private UUID pais;

  private UUID estado;

  @NotEmpty
  private String telefone;
  @NotEmpty
  private String cep;

  @NotNull
  @Positive
  private BigDecimal total;

  @NotNull
  @Size(min = 1)
  @Valid
  private List<ItemPedidoRequest> itens;

  public Set<UUID> getIdLivros() {
    return itens.stream()
      .map(ItemPedidoRequest::getIdLivro)
      .collect(Collectors.toSet());
  }

  public List<ItemPedidoRequest> getItens() {
    return Collections.unmodifiableList(itens);
  }

  public Compra entidade(EstadoRepository estadoRepository, PaisRepository paisRepository, LivroRepository livroRepository) {
    Pais pais = paisRepository.findById(this.pais).orElseThrow();

    Estado estado = Objects.isNull(this.estado) ? null :
      estadoRepository.findById(this.estado).orElseThrow();

    List<ItemPedido> itensPedido = itens.stream()
      .map(item -> {
        Livro livro = livroRepository.findById(item.getIdLivro()).orElseThrow();
        return new ItemPedido(livro, item.getQuantidade());
      }).collect(Collectors.toList());

    return Compra.builder()
      .email(email)
      .nome(nome)
      .sobrenome(sobrenome)
      .endereco(endereco)
      .pais(pais)
      .estado(estado)
      .documento(documento)
      .cep(cep)
      .cidade(cidade)
      .telefone(telefone)
      .total(total)
      .complemento(complemento)
      .itens(itensPedido)
      .build();
  }

}
