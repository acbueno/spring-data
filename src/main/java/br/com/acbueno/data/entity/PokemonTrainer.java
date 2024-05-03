package br.com.acbueno.data.entity;

import org.modelmapper.ModelMapper;
import br.com.acbueno.data.dto.PokemonTrainnerRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "pokemon_trainer")
@Data
public class PokemonTrainer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false)
  private String name;

  public static PokemonTrainer convert(PokemonTrainnerRequestDTO dto) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(dto, PokemonTrainer.class);
  }

}
