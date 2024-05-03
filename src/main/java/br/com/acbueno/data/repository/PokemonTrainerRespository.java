package br.com.acbueno.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.acbueno.data.entity.PokemonTrainer;

public interface PokemonTrainerRespository extends JpaRepository<PokemonTrainer, Long> {

  public PokemonTrainer findByName(String name);

}
