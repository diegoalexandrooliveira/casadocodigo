package br.com.jornadadeveficiente.casadocodigo.livro;


import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.dominio.AutorRepository;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.CategoriaRepository;
import br.com.jornadadeveficiente.casadocodigo.livro.api.LivroDTO;
import br.com.jornadadeveficiente.casadocodigo.livro.api.NovoLivroRequest;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LivroControllerIT {


  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private LivroRepository livroRepository;
  @Autowired
  private CategoriaRepository categoriaRepository;
  @Autowired
  private AutorRepository autorRepository;

  @Autowired
  private ObjectMapper objectMapper;

  private Categoria categoria;
  private Autor autor;

  @BeforeEach
  public void setup(){
    livroRepository.deleteAll();
    autorRepository.deleteAll();
    categoriaRepository.deleteAll();
    categoria = categoriaRepository.save(new Categoria("Categoria de testes"));
    autor = autorRepository.save(new Autor("Teste", "Descrição teste", "teste@teste.com"));

  }

  @DisplayName("Cria um livro")
  @Test
  public void cria() throws Exception {
    NovoLivroRequest novoLivroRequest = NovoLivroRequest.builder().titulo("Livro para testes")
      .resumo("Resumo")
      .sumario("Sumário")
      .preco(BigDecimal.valueOf(30))
      .paginas(101L)
      .isbn("isbn teste")
      .dataPublicacao(LocalDate.now().plusDays(5))
      .idCategoria(categoria.getId()).idAutor(autor.getId())
      .build();

    MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/livros")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(novoLivroRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
      .andReturn();

    LivroDTO livroDTO = objectMapper.readValue(requestResult.getResponse().getContentAsByteArray(), LivroDTO.class);

    assertNotNull(livroDTO);
    assertNotNull(livroDTO.getId());

    Livro livro = livroRepository.findById(livroDTO.getId()).orElseThrow();

    assertEquals(novoLivroRequest.getTitulo(), livro.getTitulo());
    assertEquals(novoLivroRequest.getResumo(), livro.getResumo());
    assertEquals(novoLivroRequest.getDataPublicacao(), DateTimeFormatter.ISO_LOCAL_DATE.format(livro.getDataPublicacao()));
    assertEquals(novoLivroRequest.getIsbn(), livro.getIsbn());
    assertEquals(novoLivroRequest.getPreco(), livro.getPreco());
    assertEquals(novoLivroRequest.getPaginas(), livro.getPaginas());
  }

  @DisplayName("Livro duplicado")
  @Test
  public void erro() throws Exception {
    NovoLivroRequest novoLivroRequest = NovoLivroRequest.builder().titulo("Livro para testes")
      .resumo("Resumo")
      .sumario("Sumário")
      .preco(BigDecimal.valueOf(30))
      .paginas(101L)
      .isbn("isbn teste")
      .dataPublicacao(LocalDate.now().plusDays(5))
      .idCategoria(categoria.getId()).idAutor(autor.getId())
      .build();

    Livro livro = novoLivroRequest.toModel();
    livroRepository.save(livro);

    MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/livros")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(novoLivroRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
      .andReturn();
  }

  @DisplayName("Autor inexistente")
  @Test
  public void erroAutor() throws Exception {
    NovoLivroRequest novoLivroRequest = NovoLivroRequest.builder().titulo("Livro para testes")
      .resumo("Resumo")
      .sumario("Sumário")
      .preco(BigDecimal.valueOf(30))
      .paginas(101L)
      .isbn("isbn teste")
      .dataPublicacao(LocalDate.now().plusDays(5))
      .idCategoria(categoria.getId())
      .idAutor(UUID.randomUUID())
      .build();

    MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/livros")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(novoLivroRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
      .andReturn();
  }

  @DisplayName("Categoria inexistente")
  @Test
  public void erroCategoria() throws Exception {
    NovoLivroRequest novoLivroRequest = NovoLivroRequest.builder().titulo("Livro para testes")
      .resumo("Resumo")
      .sumario("Sumário")
      .preco(BigDecimal.valueOf(30))
      .paginas(101L)
      .isbn("isbn teste")
      .dataPublicacao(LocalDate.now().plusDays(5))
      .idCategoria(UUID.randomUUID())
      .idAutor(autor.getId())
      .build();

    MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/livros")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(novoLivroRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
      .andReturn();
  }

  @DisplayName("Data de publicação no passado")
  @Test
  public void erroData() throws Exception {
    NovoLivroRequest novoLivroRequest = NovoLivroRequest.builder().titulo("Livro para testes")
      .resumo("Resumo")
      .sumario("Sumário")
      .preco(BigDecimal.valueOf(30))
      .paginas(101L)
      .isbn("isbn teste")
      .dataPublicacao(LocalDate.now().minusDays(5))
      .idCategoria(categoria.getId())
      .idAutor(autor.getId())
      .build();

    MvcResult requestResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/livros")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(novoLivroRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
      .andReturn();
  }
}
