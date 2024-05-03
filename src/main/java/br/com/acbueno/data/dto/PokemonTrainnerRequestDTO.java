package br.com.acbueno.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PokemonTrainnerRequestDTO {

  @JsonProperty("name")
  private String name;

}
