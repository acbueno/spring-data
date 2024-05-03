package br.com.acbueno.data.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.acbueno.data.dto.PokemonTrainerResponseDTO;
import br.com.acbueno.data.dto.PokemonTrainnerRequestDTO;
import br.com.acbueno.data.service.PokemonTrainerService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/trainers")
public class PokemonTrainerController {

  @Autowired
  private PokemonTrainerService pokemonTrainerService;

  @Operation(description = "Find trainer by ID")
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<PokemonTrainerResponseDTO>> findTrainerById(
      @PathVariable("id") Long id) {
    PokemonTrainerResponseDTO responseDTO = pokemonTrainerService.findById(id);

    if (responseDTO == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found");
    }
    Link selfLink = linkTo(methodOn(PokemonController.class).getPokemonById(id)).withSelfRel();
    EntityModel<PokemonTrainerResponseDTO> model = EntityModel.of(responseDTO, selfLink);

    return ResponseEntity.ok(model);
  }

  @Operation(description = "Find all trainer")
  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<PokemonTrainerResponseDTO>>> findAllTrainer() {
    //@formatter:off
    List<EntityModel<PokemonTrainerResponseDTO>> pokemons = pokemonTrainerService.findAllTrainer()
        .stream()
        .map(pokemon -> EntityModel.of(pokemon,
            linkTo(methodOn(PokemonTrainerController.class).findTrainerById(pokemon.getId())).withSelfRel(),
            linkTo(methodOn(PokemonTrainerController.class).findAllTrainer()).withRel("pokemons")))
        .collect(Collectors.toList());

    CollectionModel<EntityModel<PokemonTrainerResponseDTO>> collectionModel = CollectionModel.of(
        pokemons, linkTo(methodOn(PokemonTrainerController.class).findAllTrainer()).withSelfRel());
    //@formatter:on
    return ResponseEntity.ok(collectionModel);
  }

  @Operation(description = "Create Pokemon Trainer")
  @PostMapping
  public ResponseEntity<EntityModel<PokemonTrainerResponseDTO>> createPokemonTrainer(
      @RequestBody PokemonTrainnerRequestDTO newPokeminTrainner) {
    //@formatter:off
    PokemonTrainerResponseDTO pokemonTrainerResponseDTO = pokemonTrainerService.save(newPokeminTrainner);
    EntityModel<PokemonTrainerResponseDTO> entityModel = EntityModel.of(pokemonTrainerResponseDTO,
        linkTo(methodOn(PokemonTrainerController.class).findTrainerById(pokemonTrainerResponseDTO.getId())).withSelfRel(),
        linkTo(methodOn(PokemonTrainerController.class).findAllTrainer()).withRel("pokemons"));

    return ResponseEntity.created(linkTo(methodOn(PokemonTrainerController.class)
        .findTrainerById(pokemonTrainerResponseDTO.getId()))
        .toUri())
        .body(entityModel);
    //@formatter:on
  }

  @Operation(description = "Update trainer by ID")
  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<PokemonTrainerResponseDTO>> updatePokemonTrainner(
      @PathVariable("id") Long id, @RequestBody PokemonTrainnerRequestDTO dto) {
    PokemonTrainerResponseDTO responseDTO = pokemonTrainerService.update(id, dto);
    //@formatter:off
      EntityModel<PokemonTrainerResponseDTO> entityModel = EntityModel.of(responseDTO, 
          linkTo(methodOn(PokemonTrainerController.class).findTrainerById(id)).withSelfRel(),
          linkTo(methodOn(PokemonTrainerController.class).findAllTrainer()).withRel("pokemons"));
      //@formatter:on
    return ResponseEntity.ok(entityModel);
  }

  @Operation(description = "Update trainer by ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePokemonTrainnerById(@PathVariable("id") Long id) {
    pokemonTrainerService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
