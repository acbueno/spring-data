package br.com.acbueno.data.dto;

import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.acbueno.data.entity.PokeBall;
import lombok.Data;

@Data
public class PokeBallRequestDTO {

  @JsonProperty("id_pokenon_trainner")
  private Long idPokemonTrainer;

  @JsonProperty("id_pokemon")
  private Long idPokemon;

  public static PokeBallRequestDTO convert(PokeBall entity) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(entity, PokeBallRequestDTO.class);
  }

}
