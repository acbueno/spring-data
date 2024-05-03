package br.com.acbueno.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.data.dto.PokeBallReport;
import br.com.acbueno.data.dto.PokeBallRequestDTO;
import br.com.acbueno.data.dto.PokeBallResponseDTO;
import br.com.acbueno.data.dto.PokemonResponseDTO;
import br.com.acbueno.data.entity.PokeBall;
import br.com.acbueno.data.entity.Pokemon;
import br.com.acbueno.data.entity.PokemonTrainer;
import br.com.acbueno.data.repository.PokeBallRepository;
import br.com.acbueno.data.repository.PokemonRepository;
import br.com.acbueno.data.repository.PokemonTrainerRespository;
import jakarta.transaction.Transactional;

@Service
public class PokeBallService {

  @Autowired
  private PokeBallRepository repositoryPokeBall;

  @Autowired
  private PokemonRepository repositoryPokemon;

  @Autowired
  private PokemonTrainerRespository respositoryPokemonTrainer;

  @Transactional
  public PokeBallResponseDTO save(PokeBallRequestDTO dto) {
    PokeBall convert = PokeBall.convert(dto);
    repositoryPokeBall.save(PokeBall.convert(dto));
    Optional<Pokemon> pokemon = repositoryPokemon.findById(dto.getIdPokemon());
    return PokeBallResponseDTO.convert(repositoryPokeBall.findByPokemon(pokemon.get()));
  }

  public List<PokeBallResponseDTO> getAll() {
    List<PokeBallResponseDTO> list = new ArrayList<>();
    repositoryPokeBall.findAll().forEach(it -> {
      list.add(PokeBallResponseDTO.convert(it));
    });;
    return list;
  }

  public PokeBallResponseDTO getPokeBallById(Long id) {
    return PokeBallResponseDTO.convert(repositoryPokeBall.findById(id).get());
  }

  @Transactional
  public void deleteByid(Long id) {
    repositoryPokeBall.deleteById(id);
  }

  public PokeBallResponseDTO getPokeBallByIdPokemon(Pokemon pokemon) {
    return PokeBallResponseDTO.convert(repositoryPokeBall.findByPokemon(pokemon));
  }

  public PokeBallResponseDTO getPokeBallByIdPokemonTrainner(PokemonTrainer pokemonTrainer) {
    return PokeBallResponseDTO.convert(repositoryPokeBall.findBypokemonTrainer(pokemonTrainer));
  }

  @Transactional
  public PokeBallResponseDTO update(Long id, PokeBallRequestDTO dto) {
    Optional<PokeBall> optPokeBall = repositoryPokeBall.findById(id);

    if (optPokeBall.isPresent()) {
      PokeBall pokeBall = optPokeBall.get();
      Optional<Pokemon> optPokemon = repositoryPokemon.findById(id);
      Optional<PokemonTrainer> optPokemonTrainner =
          respositoryPokemonTrainer.findById(dto.getIdPokemonTrainer());
      pokeBall.setPokemon(optPokemon.get());
      pokeBall.setPokemonTrainer(optPokemonTrainner.get());
      return PokeBallResponseDTO.convert(repositoryPokeBall.save(pokeBall));
    } else {
      throw new RuntimeException(String.format("PokeBall Not found with: id %s ", id));
    }
  }

  public PokeBallReport getPokemonsByTrainerName(String name) {
    List<PokemonResponseDTO> listPokemonResponseDTOs = new ArrayList<>();
    PokemonTrainer trainer = respositoryPokemonTrainer.findByName(name);
    PokeBallReport report = new PokeBallReport();
    repositoryPokeBall.findAll().forEach(it -> {
      if (it.getPokemonTrainer().getId() == trainer.getId()) {
        Pokemon pokemon = repositoryPokemon.findById(it.getPokemon().getId()).get();
        listPokemonResponseDTOs.add(PokemonResponseDTO.convert(pokemon));
      }
    });
    report.setTrainerName(name);
    report.setListPokemon(listPokemonResponseDTOs);
    return report;
  }
}
