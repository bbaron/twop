package twop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
class Account {
  @Id
  @GeneratedValue
  private Long id;

  @Email
  @Column(unique = true)
  @NotNull
  @Size(max = 50)
  private String email;

  @NotBlank
  private String name;

  private String favoriteShow;

  @Column(updatable = false)
  @NotNull
  private final LocalDateTime createdDate = LocalDateTime.now();

  Account(String email, String name, String favoriteShow) {
    this.email = email;
    this.name = name;
    this.favoriteShow = favoriteShow;
  }

  Account(String email, String name) {
    this(email, name, null);
  }
}
