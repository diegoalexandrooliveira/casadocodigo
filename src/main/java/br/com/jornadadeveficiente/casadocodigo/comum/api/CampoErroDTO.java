package br.com.jornadadeveficiente.casadocodigo.comum.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CampoErroDTO {

  private String campo;

  private String mensagem;
}
