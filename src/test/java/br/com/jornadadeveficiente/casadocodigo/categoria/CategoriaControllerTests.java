package br.com.jornadadeveficiente.casadocodigo.categoria;


import br.com.jornadadeveficiente.casadocodigo.categoria.api.CategoriaResponse;
import br.com.jornadadeveficiente.casadocodigo.categoria.api.NovaCategoriaRequest;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.Categoria;
import br.com.jornadadeveficiente.casadocodigo.categoria.dominio.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoriaControllerTests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private CategoriaRepository categoriaRepository;


  private ObjectMapper objectMapper = new ObjectMapper();

  @DisplayName("Deve criar uma categoria")
  @Test
  public void criaCategoria() throws Exception {
    categoriaRepository.deleteAll();

    NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Ação");

    MvcResult mvcResult = mockMvc.perform(
      MockMvcRequestBuilders
        .post("/api/categorias")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(novaCategoriaRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
      .andReturn();

    CategoriaResponse categoriaResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), CategoriaResponse.class);

    assertNotNull(categoriaResponse);
    assertNotNull(categoriaResponse.getId());
    assertEquals(novaCategoriaRequest.getNome(), categoriaResponse.getNome());

    Categoria categoria = categoriaRepository.findById(categoriaResponse.getId()).orElseThrow();

    assertEquals(novaCategoriaRequest.getNome(), categoria.getNome());
  }

  @DisplayName("Não deve criar uma categoria por causa do nome estar duplicado")
  @Test
  public void nomeDuplicado() throws Exception {
    categoriaRepository.deleteAll();
    NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Ação");
    Categoria categoria = new Categoria(novaCategoriaRequest.getNome());
    categoriaRepository.save(categoria);

    mockMvc.perform(
      MockMvcRequestBuilders
        .post("/api/categorias")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(novaCategoriaRequest)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
  }
}
