package br.com.acbueno.data.dto;

import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.acbueno.data.entity.PokemonTrainer;
import lombok.Data;

@Data
public class PokemonTrainerResponseDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  public static PokemonTrainerResponseDTO convert(PokemonTrainer entity) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(entity, PokemonTrainerResponseDTO.class);
  }

}
