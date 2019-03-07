-- create schema
create schema if not exists pcsetup authorization pcs;

-- create table: hardware_setting
create table if not exists pcsetup.hardware_setting (
	id bigserial primary key,
	setting varchar(128) not null,
	value varchar(256) not null,
	note varchar(512)
) without oids;

create index on pcsetup.hardware_setting using btree (setting);

-- create table: create table game
create table if not exists pcsetup.game (
	id bigserial primary key,
	name varchar(256) not null,
	note varchar(512)
) without oids;

create index on pcsetup.game using btree (name);

-- create table: game_category
create table if not exists pcsetup.game_category (
	id bigserial primary key,
	game_id bigint not null references pcsetup.Game(id) on update cascade on delete cascade,
	name varchar (256)
) without oids;

create index on pcsetup.game_category using btree (game_id, name);

create index on pcsetup.game_category using btree (name);

-- create table: game_category_setting
create table if not exists pcsetup.game_category_setting (
	id bigserial primary key,
	game_category_id bigint not null references pcsetup.game_category(id) on update cascade on delete cascade,
	setting varchar(128) not null,
	value varchar(256) not null,
	note varchar(512)
) without oids;

create index on pcsetup.game_category_setting using btree (setting);

create index on pcsetup.game_category_setting using btree (game_category_id, setting);

