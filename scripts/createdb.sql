-- Schema Setup
create schema if not exists profile authorization pcs;
create schema if not exists hardware authorization pcs;
create schema if not exists game authorization pcs;

-- User
create table if not exists profile.user (
	id bigserial primary key,
	username varchar(64) not null,
	fullname varchar(128),
	bio varchar(256),
	created_timestamp timestamptz not null default current_timestamp,
	modified_timestamp timestamptz not null default current_timestamp,
	deleted boolean not null default false
) without oids;

create unique index on profile.user using btree (username);

create table if not exists profile.authentication (
	id bigserial primary key,
	user_id bigint not null references profile.user(id) on update cascade on delete cascade,
	ciphertext varchar(256) not null,
	last_login_timestamp timestamptz not null default current_timestamp,
	expiration_timestamp timestamptz not null default current_timestamp + interval '365 days'
) without oids;

create unique index on profile.authentication using btree (user_id);

-- Hardware
create table if not exists hardware.component (
	id bigserial primary key,
	name varchar(512) not null
) without oids;

create table if not exists hardware.setting (
	id bigserial primary key,
	user_id bigint not null references profile.user(id) on update cascade on delete cascade,
	component_id bigint not null references hardware.component(id) on update cascade on delete cascade,
	name varchar(256) not null,
	notes varchar(512)
) without oids;

create index on hardware.setting using btree (user_id, component_id);
create index on hardware.setting using btree (component_id);

-- Game
create table if not exists game.title (
	id bigserial primary key,
	name varchar(512) not null
) without oids;

create table if not exists game.profile_game (
	id bigserial primary key,
	user_id bigint not null references profile.user(id) on update cascade on delete cascade,
	title_id bigint not null references game.title(id) on update cascade on delete cascade,
	average_fps integer 
) without oids;

create unique index on game.profile_game using btree (user_id, title_id);
create index on game.profile_game using btree (title_id);

create table if not exists game.category (
	id bigserial primary key,
	name varchar(64) not null
) without oids;

create table if not exists game.setting (
	id bigserial primary key,
	profile_game_id bigint not null references game.profile_game(id) on update cascade on delete cascade,
	category_id bigint not null references game.category(id) on update cascade on delete cascade,
	setting varchar(128) not null,
	value varchar(128) not null
) without oids;

create index on game.setting using btree (profile_game_id, category_id);
