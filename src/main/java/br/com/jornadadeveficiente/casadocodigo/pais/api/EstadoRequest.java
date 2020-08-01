package br.com.jornadadeveficiente.casadocodigo.pais.api;

import br.com.jornadadeveficiente.casadocodigo.comum.dominio.UniqueValue;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Estado;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.PaisRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class EstadoRequest {

  @UniqueValue(entity = "Estado", fieldName = "nome")
  @NotEmpty
  private String nome;

  @NotNull
  private UUID idPais;

  public Estado toModel(PaisRepository paisRepository) {
    Pais pais = paisRepository.findById(idPais).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "País não encontrado"));
    return new Estado(null, pais, nome);
  }


}
