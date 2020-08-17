package br.com.jornadadeveficiente.casadocodigo.livro.dominio;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank
  private String titulo;

  @NotBlank
  @Size(max = 500)
  private String resumo;

  @NotBlank
  private String sumario;

  @Min(20)
  private BigDecimal preco;

  @Min(100)
  private Long paginas;

  @NotBlank
  private String isbn;

  @NotNull
  private LocalDate dataPublicacao;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Categoria categoria;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Autor autor;

  public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario, @Min(20) BigDecimal preco, @Min(100) Long paginas, @NotBlank String isbn, @NotNull LocalDate dataPublicacao, @NotNull Categoria categoria, @NotNull Autor autor) {
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.preco = preco;
    this.paginas = paginas;
    this.isbn = isbn;
    this.dataPublicacao = dataPublicacao;
    this.categoria = categoria;
    this.autor = autor;
  }

  public String getNomeAutor() {
    return autor.getNome();
  }
}
