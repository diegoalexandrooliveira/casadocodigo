package br.com.jornadadeveficiente.casadocodigo.comum.api;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ValidacaoDTO {

  private List<String> errorsGlobais = new ArrayList<>();
  private List<CampoErroDTO> camposComErro = new ArrayList<>();

  public void adicionaErro(String mensagem){
    errorsGlobais.add(mensagem);
  }

  public void adicionaCampoErro(String campo, String mensagem){
    camposComErro.add(new CampoErroDTO(campo, mensagem));
  }

  public List<String> getErrorsGlobais() {
    return List.copyOf(errorsGlobais);
  }

  public List<CampoErroDTO> getCamposComErro() {
    return List.copyOf(camposComErro);
  }
}
