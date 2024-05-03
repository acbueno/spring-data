create table poke_ball (id bigint not null auto_increment, pokemon_id bigint, trainer_id bigint, primary key (id)) 
engine=InnoDB;

create table pokemon (id bigint not null auto_increment, name varchar(255), pokemon_type enum ('NORMAL','FIRE','FIGHTING','WATER','FLYING','GRASS','POISON','ELECTRIC','GROUND','PSYCHIC','ROCK','ICE','BUG','DRAGON','GHOST','DARK','STEEL','FAIRY','STELLAR'), primary key (id)) 
engine=InnoDB;

create table pokemon_trainer (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) 
engine=InnoDB;

alter table poke_ball add constraint FKcj6sumipbmaa3vm7x04ysb5bv foreign key (pokemon_id) references pokemon (id);

alter table poke_ball add constraint FKddnaqen5rfxdnaosdys708cpm foreign key (trainer_id) references pokemon_trainer (id);

/*ALTER TABLE pokemon MODIFY COLUMN pokemon_type  ENUM('NORMAL','FIRE','FIGHTING','WATER','FLYING','GRASS','POISON','ELECTRIC','GROUND','PSYCHIC','ROCK','ICE','BUG','DRAGON','GHOST','DARK','STEEL','FAIRY','STELLAR') NOT NULL;*/
