package br.com.jornadadeveficiente.casadocodigo.autor;

import br.com.jornadadeveficiente.casadocodigo.autor.application.AutorRequest;
import br.com.jornadadeveficiente.casadocodigo.autor.application.AutorResponse;
import br.com.jornadadeveficiente.casadocodigo.autor.domain.Autor;
import br.com.jornadadeveficiente.casadocodigo.autor.domain.AutorRepository;
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
class AutorControllerTests {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private AutorRepository autorRepository;

  private ObjectMapper objectMapper = new ObjectMapper();

  @DisplayName("Deve criar um autor")
  @Test
  void criaAutor() throws Exception {
    autorRepository.deleteAll();
    AutorRequest autor = new AutorRequest("Diego", "Bom autor", "teste@teste.com");

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/autores")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(autor)))
      .andExpect(MockMvcResultMatchers.status().is(200))
      .andReturn();

    AutorResponse autorResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), AutorResponse.class);

    assertNotNull(autorResponse);
    assertNotNull(autorResponse.getId());
    assertEquals(autor.getNome(), autorResponse.getNome());
    assertEquals(autor.getDescricao(), autorResponse.getDescricao());
    assertEquals(autor.getEmail(), autorResponse.getEmail());

    Autor autorRepository = this.autorRepository.findById(autorResponse.getId()).orElseThrow();
    assertEquals(autor.getNome(), autorRepository.getNome());
    assertEquals(autor.getDescricao(), autorRepository.getDescricao());
    assertEquals(autor.getEmail(), autorRepository.getEmail());
  }

  @DisplayName("Não deve criar um autor porque email já existe")
  @Test
  void emailDuplicado() throws Exception {
    autorRepository.deleteAll();
    Autor autor = new Autor("Diego", "Bom autor", "teste@teste.com");
    autorRepository.save(autor);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/api/autores")
      .contentType("application/json")
      .content(objectMapper.writeValueAsString(autor)))
      .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
      .andReturn();
  }

}
