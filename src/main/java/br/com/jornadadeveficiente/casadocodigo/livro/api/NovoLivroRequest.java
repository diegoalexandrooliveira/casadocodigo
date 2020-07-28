package br.com.jornadadeveficiente.casadocodigo.livro.api;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.dominio.ValidaAutor;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.ValidaCategoria;
import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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
  @Future
//  @JsonFormat(pattern = "yyyy-mm-dd", shape = JsonFormat.Shape.STRING)
  private LocalDate dataPublicacao;

  @NotNull
  @ValidaCategoria
  private UUID idCategoria;

  @NotNull
  @ValidaAutor
  private UUID idAutor;

  @JsonGetter
  public String getDataPublicacao() {
    return DateTimeFormatter.ISO_LOCAL_DATE.format(dataPublicacao);
  }

  @JsonSetter
  public void setDataPublicacao(String dataPublicacao) {
    this.dataPublicacao = LocalDate.parse(dataPublicacao, DateTimeFormatter.ISO_LOCAL_DATE);
  }

  public Livro toModel() {
    Categoria categoria = new Categoria(idCategoria);
    Autor autor = new Autor(idAutor);
    return new Livro(titulo, resumo, sumario, preco, paginas, isbn, dataPublicacao, categoria, autor);
  }
}
