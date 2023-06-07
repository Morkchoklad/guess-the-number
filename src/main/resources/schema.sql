DROP DATABASE IF EXISTS guessNumber;
CREATE DATABASE guessNumber;
USE guessNumber;


create table game (
	GameId int not null auto_increment,
    word Varchar(4) not null,
    inProgress boolean not null default true,
	Primary key (GameId)
) ;

create table round(
	RoundId int Primary key not null auto_increment,
    guess Varchar(4) not null,
    roundTime datetime not null,
    GameId int not null,
    partialGuess int not null,
    exactGuess int not null,
    Foreign key (GameId) references game(GameId)
);