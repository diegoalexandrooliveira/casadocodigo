package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.cupom.dominio.Cupom;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Estado;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Compra {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private UUID id;
  @Email
  @NotEmpty
  @Getter
  private String email;
  @NotEmpty
  @Getter
  private String nome;
  @NotEmpty
  @Getter
  private String sobrenome;
  @NotEmpty
  @Getter
  private String documento;
  @NotEmpty
  @Getter
  private String endereco;
  @NotEmpty
  @Getter
  private String complemento;
  @NotEmpty
  @Getter
  private String cidade;
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pais_id")
  private Pais pais;
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "estado_id")
  private Estado estado;
  @NotEmpty
  @Getter
  private String telefone;
  @NotEmpty
  @Getter
  private String cep;
  @NotNull
  @Positive
  @Getter
  private BigDecimal total;
  @NotNull
  @Positive
  @Getter
  private BigDecimal totalSemDesconto;
  @NotNull
  @Enumerated(EnumType.STRING)
  @Getter
  private Status status;
  @Embedded
  private CupomAplicado cupomAplicado;


  @NotNull
  @Size(min = 1)
  @OneToMany(mappedBy = "compra", orphanRemoval = true, cascade = CascadeType.ALL)
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

  public String getNomePais() {
    return pais.getNome();
  }

  public String getNomeEstado() {
    return Objects.isNull(estado) ? "" : estado.getNome();
  }

  public boolean existeCupom() {
    return Objects.nonNull(cupomAplicado);
  }

  public BigDecimal valorCupom(){
    return totalSemDesconto.subtract(total);
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
    private Cupom cupom;


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

    public CompraBuilder cupom(Cupom cupom) {
      this.cupom = cupom;
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
      compra.totalSemDesconto = this.total;
      compra.documento = this.documento;
      compra.email = this.email;
      compra.cidade = this.cidade;
      itens.forEach(compra::adicionaItem);
      compra.status = Status.INICIADA;
      if(Objects.nonNull(this.cupom)){
        Assert.isTrue(this.cupom.valido(), "Cupom não é válido.");
        compra.cupomAplicado = new CupomAplicado(this.cupom);
        compra.total = total.subtract(cupom.getPercentual().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN).multiply(total));
      }
      return compra;
    }
  }
}
