package br.com.jornadadeveficiente.casadocodigo.categoria.dominio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank
  private String nome;

  public Categoria(@NotBlank String nome) {
    Assert.hasText(nome, "Nome deve estar preenchido.");
    this.nome = nome;
  }
}
