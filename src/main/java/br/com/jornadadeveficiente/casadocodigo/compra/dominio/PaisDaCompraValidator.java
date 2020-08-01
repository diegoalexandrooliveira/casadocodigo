package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.compra.api.NovaCompraRequest;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.EstadoRepository;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.Pais;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PaisDaCompraValidator implements Validator {

  private EstadoRepository estadoRepository;
  private PaisRepository paisRepository;

  @Autowired
  public PaisDaCompraValidator(EstadoRepository estadoRepository, PaisRepository paisRepository) {
    this.estadoRepository = estadoRepository;
    this.paisRepository = paisRepository;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.isAssignableFrom(NovaCompraRequest.class);
  }

  @Override
  public void validate(Object o, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;

    validaPais(errors, novaCompraRequest.getPais());

    validaEstado(errors, novaCompraRequest.getEstado(), novaCompraRequest.getPais());

  }

  private void validaPais(Errors errors, String nomePais) {
    Optional<Pais> optionalPais = paisRepository.findByNome(nomePais);
    if (optionalPais.isEmpty()) {
      errors.rejectValue("pais", "PaisNaoCadastrado");
    }
  }

  private void validaEstado(Errors errors, String nomeEstado, String pais) {
    if (errors.hasErrors()) {
      return;
    }

    if (estadoRepository.existsByPaisNome(pais)) {
      if (estadoEstaVazio(nomeEstado)) {
        errors.rejectValue("estado", "EstadoDeveSerInformado");
      } else if (!estadoRepository.existsByNome(nomeEstado)) {
        errors.rejectValue("estado", "EstadoNaoEncontrado");
      }
    }
  }

  private boolean estadoEstaVazio(String nomeEstado) {
    return nomeEstado == null || nomeEstado.trim().equals("");
  }
}
