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
import br.com.acbueno.data.dto.PokemonRequestDTO;
import br.com.acbueno.data.dto.PokemonResponseDTO;
import br.com.acbueno.data.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

  @Autowired
  private PokemonService pokemonService;

  @Operation(description = "Find pokemon by ID")
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<PokemonResponseDTO>> getPokemonById(
      @PathVariable("id") Long id) {
    PokemonResponseDTO pokeBallResponseDTO = pokemonService.getById(id);
    if (pokeBallResponseDTO == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found");
    }
    Link selfLink = linkTo(methodOn(PokemonController.class).getPokemonById(id)).withSelfRel();
    EntityModel<PokemonResponseDTO> model = EntityModel.of(pokeBallResponseDTO, selfLink);

    return ResponseEntity.ok(model);
  }

  @Operation(description = "Find all Pokemon")
  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<PokemonResponseDTO>>> getAllPokemon() {
    //@formatter:off
    List<EntityModel<PokemonResponseDTO>> pokemons = pokemonService.getAllPokemons().stream()
        .map(it -> EntityModel.of(it,linkTo(methodOn(PokemonController.class).getPokemonById(it.getId())).withSelfRel(),
         linkTo(methodOn(PokemonController.class).getAllPokemon()).withRel("pokemons")))
        .collect(Collectors.toList());
    CollectionModel<EntityModel<PokemonResponseDTO>> collectionModel = CollectionModel.of(pokemons,
        linkTo(methodOn(PokemonController.class).getAllPokemon()).withSelfRel());
    //@formatter:on
    return ResponseEntity.ok(collectionModel);
  }

  @Operation(description = "Delete Pokemon by ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    pokemonService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(description = "Update Pokemon by ID")
  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<PokemonResponseDTO>> updatePokemon(@PathVariable("id") Long id,
      @RequestBody PokemonRequestDTO requestDTO) {
    //@formatter:on
    PokemonResponseDTO pokemonResponseDTO = pokemonService.update(id, requestDTO);
    EntityModel<PokemonResponseDTO> entitModel = EntityModel.of(pokemonResponseDTO,
        linkTo(methodOn(PokemonController.class).getAllPokemon()).withSelfRel(),
        linkTo(methodOn(PokemonController.class).getAllPokemon()).withRel("pokemons"));
    //@formatter:off
    return ResponseEntity.ok(entitModel);
  }
  
  @Operation(description = "Create Pokemon")
  @PostMapping
  public ResponseEntity<EntityModel<PokemonResponseDTO>> createPokemon(@RequestBody PokemonRequestDTO requestDTO) {
    //@fomatter:off
    PokemonResponseDTO responseDTO = pokemonService.save(requestDTO);
    EntityModel<PokemonResponseDTO> entityModel = EntityModel.of(responseDTO,
        linkTo(methodOn(PokemonController.class).getPokemonById(responseDTO.getId())).withSelfRel(),
        linkTo(methodOn(PokemonController.class).getAllPokemon()).withRel("pokemons"));
    
    return ResponseEntity
        .created(linkTo(methodOn(PokemonController.class)
        .getPokemonById(responseDTO.getId()))
         .toUri())
        .body(entityModel);
    //@formatter:off
  }

}
