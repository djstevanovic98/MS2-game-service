drop table if exists play cascade;
drop table if exists player cascade;
drop table if exists game cascade;

create table game(
	id serial not null primary key,
	code varchar(20) not null unique,
	home_team varchar(20) not null,
	visitor_team varchar(20) not null,
	start_time time,
	end_time time,
	finished boolean

);

create table player(
    id serial not null primary key,
    full_name varchar(40) not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    game_id int references game,

    unique(game_id,id)
);

create table play(
	id serial not null primary key,
    game_number int not null,

    quarter int not null,
    quarter_time varchar(10) not null,
    game_time varchar(100),
    description varchar(200) not null,

    home_score int not null,
    visitor_score int not null,
    game_id int references game,

    unique (game_id,id)

);

-- create table play_player( napravi kod plej reference na game_id?
--     id serial not null primary key,
-- 	-- ...
-- );
