package br.com.acbueno.data.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokeBallReport {

  @JsonProperty("trainer-name")
  private String trainerName;

  @JsonProperty("pokemons")
  private List<PokemonResponseDTO> listPokemon;

}
