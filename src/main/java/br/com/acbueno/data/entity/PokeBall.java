package br.com.acbueno.data.entity;

import org.modelmapper.ModelMapper;
import br.com.acbueno.data.dto.PokeBallRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "poke_ball")
@Data
public class PokeBall {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ManyToOne
  @JoinColumn(name = "trainer_id", referencedColumnName = "id")
  private PokemonTrainer pokemonTrainer;

  @ManyToOne
  @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
  private Pokemon pokemon;

  public static PokeBall convert(PokeBallRequestDTO dto) {
    ModelMapper mapper = new ModelMapper();
    return mapper.map(dto, PokeBall.class);
  }

}
