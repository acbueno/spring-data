package br.com.acbueno.data.dto;

import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.acbueno.data.entity.Pokemon;
import br.com.acbueno.data.entity.enumerator.Type;
import lombok.Data;

@Data
public class PokemonRequestDTO {

  @JsonProperty("name")
  private String name;

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Type type;

  public static PokeBallRequestDTO convert(Pokemon entity) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(entity, PokeBallRequestDTO.class);
  }

}
