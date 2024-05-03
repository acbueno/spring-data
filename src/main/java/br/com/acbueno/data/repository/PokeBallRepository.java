package br.com.acbueno.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.data.entity.PokeBall;
import br.com.acbueno.data.entity.Pokemon;
import br.com.acbueno.data.entity.PokemonTrainer;

@Repository
public interface PokeBallRepository extends JpaRepository<PokeBall, Long> {

  public PokeBall findByPokemon(Pokemon pokemon);

  public PokeBall findBypokemonTrainer(PokemonTrainer trainer);

}
