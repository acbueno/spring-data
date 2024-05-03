package br.com.acbueno.data.entity.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
  //@formatter:off
  NORMAL(1, "normal"), 
  FIRE(2, "Fire"), 
  FIGHTING(3, "Fighting"), 
  WATER(4, "Water"), 
  FLYING(5,"Flying"), 
  GRASS(6, "Grass"), 
  POISON(7, "Poison"), 
  ELECTRIC(8, "Electric"), 
  GROUND(9,"Ground"), 
  PSYCHIC(10, "Psychic"), 
  ROCK(11, "Rock"), 
  ICE(12, "Ice"), 
  BUG(13,"Bug"), 
  DRAGON(14, "Dragon"), 
  GHOST(15, "Ghost"), 
  DARK(16, "Dark"), 
  STEEL(17, "Steel"), 
  FAIRY(18, "Fairy"), 
  STELLAR(19, "Stellar");
  //@formatter:on

  private long id;

  private String name;



}
