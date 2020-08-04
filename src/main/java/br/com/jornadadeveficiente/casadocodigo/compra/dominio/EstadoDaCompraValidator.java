package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.compra.api.NovaCompraRequest;
import br.com.jornadadeveficiente.casadocodigo.pais.dominio.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.UUID;

@Component
public class EstadoDaCompraValidator implements Validator {

  private EstadoRepository estadoRepository;

  @Autowired
  public EstadoDaCompraValidator(EstadoRepository estadoRepository) {
    this.estadoRepository = estadoRepository;
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

    UUID estado = novaCompraRequest.getEstado();
    UUID pais = novaCompraRequest.getPais();
    if (estadoRepository.existsByPaisId(pais)) {
      if (Objects.isNull(estado)) {
        errors.rejectValue("estado", "EstadoDeveSerInformado");
      } else if (!estadoRepository.existsById(estado)) {
        errors.rejectValue("estado", "EstadoNaoEncontrado");
      }
    }
  }
}
