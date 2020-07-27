package br.com.jornadadeveficiente.casadocodigo.autor.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "autores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank(message = "Nome do autor não pode estar em branco.")
  private String nome;

  @Size(max = 400, message = "Descrição não pode ser maior que 400 caracteres.")
  @NotBlank(message = "Descrição não pode ser em branco.")
  private String descricao;

  @Email(message = "Email inválido.")
  @NotBlank(message = "Email não pode ser em branco.")
  private String email;

  @NotNull(message = "Data da criação não pode ser nula.")
  private LocalDateTime dataDaCriacao = LocalDateTime.now();


  public Autor(@NotBlank(message = "Nome do autor não pode estar em branco.") String nome, @Size(max = 400, message = "Descrição não pode ser maior que 400 caracteres.") @NotBlank(message = "Descrição não pode ser em branco.") String descricao, @Email(message = "Email inválido.") @NotBlank(message = "Email não pode ser em branco.") String email) {
    this.nome = nome;
    this.descricao = descricao;
    this.email = email;
  }

  public Autor(UUID id){
    this.id = id;
  }
}
