package br.com.acbueno.data.dto;

import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.acbueno.data.entity.PokeBall;
import lombok.Data;

@Data
public class PokeBallResponseDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("id-pokemon-trainer")
  private Long idPokemonTrainer;

  @JsonProperty("id-pokemon")
  private Long idPokemon;

  public static PokeBallResponseDTO convert(PokeBall entity) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(entity, PokeBallResponseDTO.class);
  }

}
