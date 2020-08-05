package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Estado;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Compra {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Email
  @NotEmpty
  private String email;
  @NotEmpty
  private String nome;
  @NotEmpty
  private String sobrenome;
  @NotEmpty
  private String documento;
  @NotEmpty
  private String endereco;
  @NotEmpty
  private String complemento;
  @NotEmpty
  private String cidade;
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pais_id")
  private Pais pais;
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "estado_id")
  private Estado estado;
  @NotEmpty
  private String telefone;
  @NotEmpty
  private String cep;
  @NotNull
  @Min(1)
  private BigDecimal total;

  @NotNull
  @Size(min = 1)
  @OneToMany(mappedBy = "compra")
  private List<ItemPedido> itens = new ArrayList<>();

  public void adicionaItem(ItemPedido item) {
    item.setCompra(this);
    itens.add(item);
  }


  public static CompraBuilder builder() {
    return new CompraBuilder();
  }

  public List<ItemPedido> getItens() {
    return Collections.unmodifiableList(this.itens);
  }

  public static final class CompraBuilder {
    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private Pais pais;
    private Estado estado;
    private String telefone;
    private String cep;
    private BigDecimal total;
    private List<ItemPedido> itens = new ArrayList<>();


    public CompraBuilder email(String email) {
      this.email = email;
      return this;
    }

    public CompraBuilder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public CompraBuilder sobrenome(String sobrenome) {
      this.sobrenome = sobrenome;
      return this;
    }

    public CompraBuilder documento(String documento) {
      this.documento = documento;
      return this;
    }

    public CompraBuilder endereco(String endereco) {
      this.endereco = endereco;
      return this;
    }

    public CompraBuilder complemento(String complemento) {
      this.complemento = complemento;
      return this;
    }

    public CompraBuilder cidade(String cidade) {
      this.cidade = cidade;
      return this;
    }

    public CompraBuilder pais(Pais pais) {
      this.pais = pais;
      return this;
    }

    public CompraBuilder estado(Estado estado) {
      this.estado = estado;
      return this;
    }

    public CompraBuilder telefone(String telefone) {
      this.telefone = telefone;
      return this;
    }

    public CompraBuilder cep(String cep) {
      this.cep = cep;
      return this;
    }

    public CompraBuilder total(BigDecimal total) {
      this.total = total;
      return this;
    }

    public CompraBuilder itens(@NonNull List<ItemPedido> itens) {
      this.itens = itens;
      return this;
    }

    public Compra build() {
      Compra compra = new Compra();
      compra.nome = this.nome;
      compra.telefone = this.telefone;
      compra.pais = this.pais;
      compra.estado = this.estado;
      compra.sobrenome = this.sobrenome;
      compra.complemento = this.complemento;
      compra.endereco = this.endereco;
      compra.cep = this.cep;
      compra.total = this.total;
      compra.documento = this.documento;
      compra.email = this.email;
      compra.cidade = this.cidade;
      itens.forEach(compra::adicionaItem);
      return compra;
    }
  }
}
