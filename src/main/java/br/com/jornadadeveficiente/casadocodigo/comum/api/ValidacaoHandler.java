package br.com.jornadadeveficiente.casadocodigo.comum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidacaoHandler {

  @Autowired
  private MessageSource messageSource;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ValidacaoDTO handleErro(MethodArgumentNotValidException erro) {
    ValidacaoDTO validacaoDTO = new ValidacaoDTO();

    erro.getBindingResult().getGlobalErrors().forEach(erroGlobal ->
      validacaoDTO.adicionaErro(messageSource.getMessage(erroGlobal, LocaleContextHolder.getLocale())));
    erro.getBindingResult().getFieldErrors().forEach(campoErro ->
      validacaoDTO.adicionaCampoErro(campoErro.getField(),
        messageSource.getMessage(campoErro, LocaleContextHolder.getLocale())));

    return validacaoDTO;
  }

}
