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
import br.com.acbueno.data.dto.PokeBallReport;
import br.com.acbueno.data.dto.PokeBallRequestDTO;
import br.com.acbueno.data.dto.PokeBallResponseDTO;
import br.com.acbueno.data.service.PokeBallService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/pokeballs")
public class PokeBallController {

  @Autowired
  private PokeBallService pokeBallService;

  @Operation(description = "Find pokeball by ID")
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<PokeBallResponseDTO>> findPokeBallById(
      @PathVariable("id") Long id) {
    PokeBallResponseDTO pokeBallResponseDTO = pokeBallService.getPokeBallById(id);
    //@formatter:off
    if (pokeBallResponseDTO == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found");
    }

    Link selfLink = linkTo(methodOn(PokeBallController.class).findPokeBallById(id)).withSelfRel();
    EntityModel<PokeBallResponseDTO> model = EntityModel.of(pokeBallResponseDTO, selfLink);
    //@formatter:on
    return ResponseEntity.ok(model);
  }

  @Operation(description = "Find all PokeBall")
  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<PokeBallResponseDTO>>> findAllPokeBall() {
    //@formatter:off
    List<EntityModel<PokeBallResponseDTO>> pokemons = pokeBallService.getAll().stream()
        .map(pokemon -> EntityModel.of(pokemon,linkTo(methodOn(PokeBallController.class)
        .findPokeBallById(pokemon.getId())).withSelfRel(),linkTo(methodOn(PokeBallController.class)
        .findAllPokeBall()).withRel("pokemons")))
        .collect(Collectors.toList());

    CollectionModel<EntityModel<PokeBallResponseDTO>> collectionModel = CollectionModel.of(pokemons,linkTo(methodOn(PokeBallController.class)
        .findAllPokeBall())
        .withSelfRel());
    //@formatter:on
    return ResponseEntity.ok(collectionModel);
  }

  @Operation(description = "Create PokeBall")
  @PostMapping
  public ResponseEntity<EntityModel<PokeBallResponseDTO>> createPokeBall(
      @RequestBody PokeBallRequestDTO newPokeBall) {
    //@formatter:off
    PokeBallResponseDTO pokeBall = pokeBallService.save(newPokeBall);
    EntityModel<PokeBallResponseDTO> entityModel = EntityModel.of(pokeBall,
        linkTo(methodOn(PokeBallController.class)
        .findPokeBallById(pokeBall.getId()))
        .withSelfRel(),
        linkTo(methodOn(PokeBallController.class)
        .findAllPokeBall())
        .withRel("pokemons"));

    return ResponseEntity
        .created(linkTo(methodOn(PokeBallController.class)
        .findPokeBallById(pokeBall.getId()))
        .toUri())
        .body(entityModel);
    //@formatter:on
  }

  @Operation(description = "Update PokeBall by ID")
  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<PokeBallResponseDTO>> updatePokeBall(
      @PathVariable("id") Long id, @RequestBody PokeBallRequestDTO updatePokeBall) {
    PokeBallResponseDTO pokeBall = pokeBallService.update(id, updatePokeBall);
    EntityModel<PokeBallResponseDTO> entityModel = EntityModel.of(pokeBall,
        linkTo(methodOn(PokeBallController.class).findPokeBallById(id)).withSelfRel(),
        linkTo(methodOn(PokeBallController.class).findAllPokeBall()).withRel("pokemons"));

    return ResponseEntity.ok(entityModel);
  }

  @Operation(description = "Delete PokeBall by ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    pokeBallService.deleteByid(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/pokeball/report/treiner/{name}")
  public ResponseEntity<EntityModel<PokeBallReport>> findPokeBallReportByTrainnerName(
      @PathVariable String name) {
    PokeBallReport reportResponse = pokeBallService.getPokemonsByTrainerName(name);

    if (reportResponse == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found");
    }

    Link selfLik = linkTo(methodOn(PokeBallController.class).findPokeBallReportByTrainnerName(name))
        .withSelfRel();
    EntityModel<PokeBallReport> entityModel = EntityModel.of(reportResponse, selfLik);

    return ResponseEntity.ok(entityModel);
  }


}
