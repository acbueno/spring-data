package br.com.acbueno.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.data.entity.Pokemon;
import br.com.acbueno.data.entity.enumerator.Type;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

  public Pokemon findByName(String name);

  public Pokemon findByType(Type type);

}
