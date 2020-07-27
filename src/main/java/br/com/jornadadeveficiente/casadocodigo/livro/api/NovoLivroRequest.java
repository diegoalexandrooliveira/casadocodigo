package br.com.jornadadeveficiente.casadocodigo.livro.api;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.dominio.AutorValido;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.CategoriaValida;
import br.com.jornadadeveficiente.casadocodigo.comum.dominio.DataFutura;
import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class NovoLivroRequest {

  @NotBlank
  @UniqueValue(entity = "Livro", fieldName = "titulo")
  private String titulo;

  @NotBlank
  @Size(max = 500)
  private String resumo;

  @NotBlank
  private String sumario;

  @Min(20)
  @NotNull
  private BigDecimal preco;

  @Min(100)
  @NotNull
  private Long paginas;

  @NotBlank
  @UniqueValue(entity = "Livro", fieldName = "isbn")
  private String isbn;

  @NotNull
  @DataFutura
  private LocalDate dataPublicacao;

  @NotNull
  @CategoriaValida
  private UUID idCategoria;

  @NotNull
  @AutorValido
  private UUID idAutor;

  public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario, @Min(20) @NotNull BigDecimal preco, @Min(100) @NotNull Long paginas, @NotBlank String isbn, @NotNull LocalDate dataPublicacao, @NotNull UUID idCategoria, @NotNull UUID idAutor) {
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.preco = preco;
    this.paginas = paginas;
    this.isbn = isbn;
    this.dataPublicacao = dataPublicacao;
    this.idCategoria = idCategoria;
    this.idAutor = idAutor;
  }

  public Livro toModel() {
    Categoria categoria = new Categoria(idCategoria);
    Autor autor = new Autor(idAutor);
    return new Livro(titulo, resumo, sumario, preco, paginas, isbn, dataPublicacao, categoria, autor);
  }
}
