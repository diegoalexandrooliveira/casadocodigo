package br.com.jornadadeveficiente.casadocodigo.compra.dominio;

import br.com.jornadadeveficiente.casadocodigo.compra.api.NovaCompraRequest;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.Livro;
import br.com.jornadadeveficiente.casadocodigo.livro.dominio.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TotalDaCompraValidator implements Validator {

  private LivroRepository livroRepository;

  @Autowired
  public TotalDaCompraValidator(LivroRepository livroRepository) {
    this.livroRepository = livroRepository;
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

    Map<UUID, BigDecimal> precos = novaCompraRequest.getIdLivros()
      .stream()
      .map(idLivro -> livroRepository.findById(idLivro).orElseThrow())
      .collect(Collectors.toMap(Livro::getId, Livro::getPreco));

    BigDecimal total = novaCompraRequest.getItens()
      .stream()
      .map(item -> precos.get(item.getIdLivro()).multiply(BigDecimal.valueOf(item.getQuantidade())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    if (novaCompraRequest.getTotal().compareTo(total) != 0) {
      errors.rejectValue("total", "TotalDivergente");
    }
  }
}
