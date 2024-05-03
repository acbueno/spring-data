package br.com.acbueno.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.data.dto.PokemonRequestDTO;
import br.com.acbueno.data.dto.PokemonResponseDTO;
import br.com.acbueno.data.entity.Pokemon;
import br.com.acbueno.data.repository.PokemonRepository;

@Service
public class PokemonService {

  @Autowired
  private PokemonRepository repository;

  public PokemonResponseDTO getById(Long id) {
    return PokemonResponseDTO.convert(repository.findById(id).get());
  }

  public PokemonResponseDTO getPokemonByName(String name) {
    return PokemonResponseDTO.convert(repository.findByName(name));
  }

  public PokemonResponseDTO save(PokemonRequestDTO dto) {
    Pokemon entity = repository.save(Pokemon.convert(dto));
    return PokemonResponseDTO.convert(entity);
  }

  public PokemonResponseDTO update(Long id, PokemonRequestDTO dto) {
    Optional<Pokemon> optPokemon = repository.findById(id);

    if (optPokemon.isPresent()) {
      Pokemon entity = optPokemon.get();
      entity.setName(dto.getName());
      entity.setType(entity.getType());

      return PokemonResponseDTO.convert(repository.save(entity));
    } else {
      throw new RuntimeException(String.format("Pokemon not found with: id %s", id));
    }
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public List<PokemonResponseDTO> getAllPokemons() {
    List<PokemonResponseDTO> listDTO = new ArrayList<>();
    repository.findAll().forEach(it -> {
      listDTO.add(PokemonResponseDTO.convert(it));
    });;
    return listDTO;
  }

}
