package br.com.acbueno.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.data.dto.PokemonTrainerResponseDTO;
import br.com.acbueno.data.dto.PokemonTrainnerRequestDTO;
import br.com.acbueno.data.entity.PokemonTrainer;
import br.com.acbueno.data.repository.PokemonTrainerRespository;
import jakarta.transaction.Transactional;

@Service
public class PokemonTrainerService {

  @Autowired
  private PokemonTrainerRespository respository;

  public PokemonTrainerResponseDTO findById(Long id) {
    return PokemonTrainerResponseDTO.convert(respository.findById(id).get());
  }

  public PokemonTrainerResponseDTO findByName(String name) {
    return PokemonTrainerResponseDTO.convert(respository.findByName(name));
  }

  @Transactional
  public PokemonTrainerResponseDTO save(PokemonTrainnerRequestDTO dto) {
    return PokemonTrainerResponseDTO.convert(respository.save(PokemonTrainer.convert(dto)));
  }

  @Transactional
  public PokemonTrainerResponseDTO update(Long id, PokemonTrainnerRequestDTO dto) {
    Optional<PokemonTrainer> optPokemonTrainner = respository.findById(id);

    if (optPokemonTrainner.isPresent()) {
      PokemonTrainer entity = optPokemonTrainner.get();
      entity.setName(dto.getName());
      return PokemonTrainerResponseDTO.convert(respository.save(entity));
    } else {
      throw new RuntimeException(String.format("PokemonTrainner not found with: id %s", id));
    }
  }

  public List<PokemonTrainerResponseDTO> findAllTrainer() {
    List<PokemonTrainerResponseDTO> list = new ArrayList<>();
    respository.findAll().forEach(it -> {
      list.add(PokemonTrainerResponseDTO.convert(it));
    });;

    return list;
  }

  @Transactional
  public void delete(Long id) {
    respository.deleteById(id);
  }

}
