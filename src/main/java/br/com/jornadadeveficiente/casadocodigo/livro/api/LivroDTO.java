package br.com.jornadadeveficiente.casadocodigo.livro.api;

import br.com.jornadadeveficiente.casadocodigo.autor.api.AutorResponse;
import br.com.jornadadeveficiente.casadocodigo.categoria.api.CategoriaResponse;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LivroDTO {
  private UUID id;
  private String titulo;
  private String resumo;
  private String sumario;
  private BigDecimal preco;
  private Long paginas;
  private String isbn;
  private LocalDate dataPublicacao;
  private CategoriaResponse categoria;
  private AutorResponse autor;


  public static LivroDTO fromModel(Livro livro) {
    CategoriaResponse categoria = CategoriaResponse.fromModel(livro.getCategoria());
    AutorResponse autor = AutorResponse.fromModel(livro.getAutor());
    return new LivroDTO(livro.getId(), livro.getTitulo(), livro.getResumo(), livro.getSumario(), livro.getPreco(),
      livro.getPaginas(), livro.getIsbn(), livro.getDataPublicacao(), categoria, autor);
  }
}
