package twop;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
public class Series {
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  @NotBlank
  private String name;

}
