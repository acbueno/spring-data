package br.com.acbueno.data.entity;

import org.modelmapper.ModelMapper;
import br.com.acbueno.data.dto.PokemonRequestDTO;
import br.com.acbueno.data.entity.enumerator.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "pokemon")
@Data
public class Pokemon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(name = "name")
  private String name;

  @Column(name = "pokemon_type")
  @Enumerated(EnumType.STRING)
  private Type type;

  public static Pokemon convert(PokemonRequestDTO dto) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(dto, Pokemon.class);
  }

}
