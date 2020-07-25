package br.com.jornadadeveficiente.casadocodigo.autor.api;

import br.com.jornadadeveficiente.casadocodigo.autor.dominio.Autor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AutorResponse {

  private UUID id;
  private String nome;
  private String descricao;
  private String email;


  public static AutorResponse fromModel(Autor autor) {
    return new AutorResponse(autor.getId(), autor.getNome(), autor.getDescricao(), autor.getEmail());
  }

}
