use vgdb;
drop table if exists videogame;
create table videogame (
  gameId int unsigned not null auto_increment,
  name varchar(24) not null,
  developer varchar(18) not null,
  console varchar(14) not null,
  rating varchar(14) not null,
  primary key(gameId)
);
