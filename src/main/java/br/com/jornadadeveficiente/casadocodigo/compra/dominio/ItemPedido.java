package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ItemPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "livro_id")
  private Livro livro;

  @NotNull
  @Min(1)
  private Long quantidade;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "compra_id")
  @Setter
  private Compra compra;

  public ItemPedido(Livro livro, Long quantidade){
    this.livro = livro;
    this.quantidade = quantidade;
  }

}
