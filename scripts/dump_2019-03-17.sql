--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE pcs;
ALTER ROLE pcs WITH NOSUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'md5955823537ef7f7343069ab324921f229';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md53175bce1d3201d16594cebf9d7eb3f9d';






--
-- Database creation
--

CREATE DATABASE pcsetup WITH TEMPLATE = template0 OWNER = pcs;
REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


\connect pcsetup

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.11
-- Dumped by pg_dump version 9.6.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: game; Type: SCHEMA; Schema: -; Owner: pcs
--

CREATE SCHEMA game;


ALTER SCHEMA game OWNER TO pcs;

--
-- Name: hardware; Type: SCHEMA; Schema: -; Owner: pcs
--

CREATE SCHEMA hardware;


ALTER SCHEMA hardware OWNER TO pcs;

--
-- Name: profile; Type: SCHEMA; Schema: -; Owner: pcs
--

CREATE SCHEMA profile;


ALTER SCHEMA profile OWNER TO pcs;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: category; Type: TABLE; Schema: game; Owner: pcs
--

CREATE TABLE game.category (
    id bigint NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE game.category OWNER TO pcs;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: game; Owner: pcs
--

CREATE SEQUENCE game.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE game.category_id_seq OWNER TO pcs;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: game; Owner: pcs
--

ALTER SEQUENCE game.category_id_seq OWNED BY game.category.id;


--
-- Name: profile_game; Type: TABLE; Schema: game; Owner: pcs
--

CREATE TABLE game.profile_game (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    title_id bigint NOT NULL,
    average_fps integer
);


ALTER TABLE game.profile_game OWNER TO pcs;

--
-- Name: profile_game_id_seq; Type: SEQUENCE; Schema: game; Owner: pcs
--

CREATE SEQUENCE game.profile_game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE game.profile_game_id_seq OWNER TO pcs;

--
-- Name: profile_game_id_seq; Type: SEQUENCE OWNED BY; Schema: game; Owner: pcs
--

ALTER SEQUENCE game.profile_game_id_seq OWNED BY game.profile_game.id;


--
-- Name: setting; Type: TABLE; Schema: game; Owner: pcs
--

CREATE TABLE game.setting (
    id bigint NOT NULL,
    profile_game_id bigint NOT NULL,
    category_id bigint NOT NULL,
    setting character varying(128) NOT NULL,
    value character varying(128) NOT NULL
);


ALTER TABLE game.setting OWNER TO pcs;

--
-- Name: setting_id_seq; Type: SEQUENCE; Schema: game; Owner: pcs
--

CREATE SEQUENCE game.setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE game.setting_id_seq OWNER TO pcs;

--
-- Name: setting_id_seq; Type: SEQUENCE OWNED BY; Schema: game; Owner: pcs
--

ALTER SEQUENCE game.setting_id_seq OWNED BY game.setting.id;


--
-- Name: title; Type: TABLE; Schema: game; Owner: pcs
--

CREATE TABLE game.title (
    id bigint NOT NULL,
    name character varying(512) NOT NULL
);


ALTER TABLE game.title OWNER TO pcs;

--
-- Name: title_id_seq; Type: SEQUENCE; Schema: game; Owner: pcs
--

CREATE SEQUENCE game.title_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE game.title_id_seq OWNER TO pcs;

--
-- Name: title_id_seq; Type: SEQUENCE OWNED BY; Schema: game; Owner: pcs
--

ALTER SEQUENCE game.title_id_seq OWNED BY game.title.id;


--
-- Name: component; Type: TABLE; Schema: hardware; Owner: pcs
--

CREATE TABLE hardware.component (
    id bigint NOT NULL,
    name character varying(512) NOT NULL
);


ALTER TABLE hardware.component OWNER TO pcs;

--
-- Name: component_id_seq; Type: SEQUENCE; Schema: hardware; Owner: pcs
--

CREATE SEQUENCE hardware.component_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hardware.component_id_seq OWNER TO pcs;

--
-- Name: component_id_seq; Type: SEQUENCE OWNED BY; Schema: hardware; Owner: pcs
--

ALTER SEQUENCE hardware.component_id_seq OWNED BY hardware.component.id;


--
-- Name: setting; Type: TABLE; Schema: hardware; Owner: pcs
--

CREATE TABLE hardware.setting (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    component_id bigint NOT NULL,
    name character varying(256) NOT NULL,
    notes character varying(512)
);


ALTER TABLE hardware.setting OWNER TO pcs;

--
-- Name: setting_id_seq; Type: SEQUENCE; Schema: hardware; Owner: pcs
--

CREATE SEQUENCE hardware.setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hardware.setting_id_seq OWNER TO pcs;

--
-- Name: setting_id_seq; Type: SEQUENCE OWNED BY; Schema: hardware; Owner: pcs
--

ALTER SEQUENCE hardware.setting_id_seq OWNED BY hardware.setting.id;


--
-- Name: authentication; Type: TABLE; Schema: profile; Owner: pcs
--

CREATE TABLE profile.authentication (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    ciphertext character varying(256) NOT NULL,
    last_login_timestamp timestamp with time zone DEFAULT now(),
    expiration_timestamp timestamp with time zone DEFAULT (now() + '365 days'::interval) NOT NULL
);


ALTER TABLE profile.authentication OWNER TO pcs;

--
-- Name: authentication_id_seq; Type: SEQUENCE; Schema: profile; Owner: pcs
--

CREATE SEQUENCE profile.authentication_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile.authentication_id_seq OWNER TO pcs;

--
-- Name: authentication_id_seq; Type: SEQUENCE OWNED BY; Schema: profile; Owner: pcs
--

ALTER SEQUENCE profile.authentication_id_seq OWNED BY profile.authentication.id;


--
-- Name: user; Type: TABLE; Schema: profile; Owner: pcs
--

CREATE TABLE profile."user" (
    id bigint NOT NULL,
    username character varying(64) NOT NULL,
    fullname character varying(128),
    bio character varying(256),
    created_timestamp timestamp with time zone DEFAULT now() NOT NULL,
    modified_timestamp timestamp with time zone DEFAULT now() NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    email character varying(256) NOT NULL
);


ALTER TABLE profile."user" OWNER TO pcs;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: profile; Owner: pcs
--

CREATE SEQUENCE profile.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile.user_id_seq OWNER TO pcs;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: profile; Owner: pcs
--

ALTER SEQUENCE profile.user_id_seq OWNED BY profile."user".id;


--
-- Name: category id; Type: DEFAULT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.category ALTER COLUMN id SET DEFAULT nextval('game.category_id_seq'::regclass);


--
-- Name: profile_game id; Type: DEFAULT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.profile_game ALTER COLUMN id SET DEFAULT nextval('game.profile_game_id_seq'::regclass);


--
-- Name: setting id; Type: DEFAULT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.setting ALTER COLUMN id SET DEFAULT nextval('game.setting_id_seq'::regclass);


--
-- Name: title id; Type: DEFAULT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.title ALTER COLUMN id SET DEFAULT nextval('game.title_id_seq'::regclass);


--
-- Name: component id; Type: DEFAULT; Schema: hardware; Owner: pcs
--

ALTER TABLE ONLY hardware.component ALTER COLUMN id SET DEFAULT nextval('hardware.component_id_seq'::regclass);


--
-- Name: setting id; Type: DEFAULT; Schema: hardware; Owner: pcs
--

ALTER TABLE ONLY hardware.setting ALTER COLUMN id SET DEFAULT nextval('hardware.setting_id_seq'::regclass);


--
-- Name: authentication id; Type: DEFAULT; Schema: profile; Owner: pcs
--

ALTER TABLE ONLY profile.authentication ALTER COLUMN id SET DEFAULT nextval('profile.authentication_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: profile; Owner: pcs
--

ALTER TABLE ONLY profile."user" ALTER COLUMN id SET DEFAULT nextval('profile.user_id_seq'::regclass);


--
-- Data for Name: category; Type: TABLE DATA; Schema: game; Owner: pcs
--

INSERT INTO game.category VALUES (1, 'Graphics');
INSERT INTO game.category VALUES (2, 'Control');
INSERT INTO game.category VALUES (3, 'Display');
INSERT INTO game.category VALUES (4, 'Input');
INSERT INTO game.category VALUES (5, 'Audio');


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: game; Owner: pcs
--

SELECT pg_catalog.setval('game.category_id_seq', 5, true);


--
-- Data for Name: profile_game; Type: TABLE DATA; Schema: game; Owner: pcs
--



--
-- Name: profile_game_id_seq; Type: SEQUENCE SET; Schema: game; Owner: pcs
--

SELECT pg_catalog.setval('game.profile_game_id_seq', 1, true);


--
-- Data for Name: setting; Type: TABLE DATA; Schema: game; Owner: pcs
--



--
-- Name: setting_id_seq; Type: SEQUENCE SET; Schema: game; Owner: pcs
--

SELECT pg_catalog.setval('game.setting_id_seq', 17, true);


--
-- Data for Name: title; Type: TABLE DATA; Schema: game; Owner: pcs
--

INSERT INTO game.title VALUES (1, 'Resident Evil 2');
INSERT INTO game.title VALUES (2, 'Apex Legends');
INSERT INTO game.title VALUES (3, 'Tom Clancy''s Rainbow Six Siege');
INSERT INTO game.title VALUES (4, 'Half-Life');
INSERT INTO game.title VALUES (5, 'Half-Life 2');


--
-- Name: title_id_seq; Type: SEQUENCE SET; Schema: game; Owner: pcs
--

SELECT pg_catalog.setval('game.title_id_seq', 5, true);


--
-- Data for Name: component; Type: TABLE DATA; Schema: hardware; Owner: pcs
--

INSERT INTO hardware.component VALUES (1, 'Processor');
INSERT INTO hardware.component VALUES (2, 'Motherboard');
INSERT INTO hardware.component VALUES (3, 'Memory');
INSERT INTO hardware.component VALUES (4, 'Video Card');
INSERT INTO hardware.component VALUES (5, 'Case');
INSERT INTO hardware.component VALUES (6, 'Keyboard');
INSERT INTO hardware.component VALUES (7, 'Mouse');
INSERT INTO hardware.component VALUES (8, 'Hard Drive');


--
-- Name: component_id_seq; Type: SEQUENCE SET; Schema: hardware; Owner: pcs
--

SELECT pg_catalog.setval('hardware.component_id_seq', 8, true);


--
-- Data for Name: setting; Type: TABLE DATA; Schema: hardware; Owner: pcs
--



--
-- Name: setting_id_seq; Type: SEQUENCE SET; Schema: hardware; Owner: pcs
--

SELECT pg_catalog.setval('hardware.setting_id_seq', 48, true);


--
-- Data for Name: authentication; Type: TABLE DATA; Schema: profile; Owner: pcs
--



--
-- Name: authentication_id_seq; Type: SEQUENCE SET; Schema: profile; Owner: pcs
--

SELECT pg_catalog.setval('profile.authentication_id_seq', 3, true);


--
-- Data for Name: user; Type: TABLE DATA; Schema: profile; Owner: pcs
--



--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: profile; Owner: pcs
--

SELECT pg_catalog.setval('profile.user_id_seq', 10, true);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: profile_game profile_game_pkey; Type: CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.profile_game
    ADD CONSTRAINT profile_game_pkey PRIMARY KEY (id);


--
-- Name: setting setting_pkey; Type: CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.setting
    ADD CONSTRAINT setting_pkey PRIMARY KEY (id);


--
-- Name: title title_pkey; Type: CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.title
    ADD CONSTRAINT title_pkey PRIMARY KEY (id);


--
-- Name: component component_pkey; Type: CONSTRAINT; Schema: hardware; Owner: pcs
--

ALTER TABLE ONLY hardware.component
    ADD CONSTRAINT component_pkey PRIMARY KEY (id);


--
-- Name: setting setting_pkey; Type: CONSTRAINT; Schema: hardware; Owner: pcs
--

ALTER TABLE ONLY hardware.setting
    ADD CONSTRAINT setting_pkey PRIMARY KEY (id);


--
-- Name: authentication authentication_pkey; Type: CONSTRAINT; Schema: profile; Owner: pcs
--

ALTER TABLE ONLY profile.authentication
    ADD CONSTRAINT authentication_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: profile; Owner: pcs
--

ALTER TABLE ONLY profile."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: category_name_idx; Type: INDEX; Schema: game; Owner: pcs
--

CREATE INDEX category_name_idx ON game.category USING btree (name);


--
-- Name: profile_game_title_id_idx; Type: INDEX; Schema: game; Owner: pcs
--

CREATE INDEX profile_game_title_id_idx ON game.profile_game USING btree (title_id);


--
-- Name: profile_game_user_id_title_id_idx; Type: INDEX; Schema: game; Owner: pcs
--

CREATE UNIQUE INDEX profile_game_user_id_title_id_idx ON game.profile_game USING btree (user_id, title_id);


--
-- Name: setting_profile_game_id_category_id_idx; Type: INDEX; Schema: game; Owner: pcs
--

CREATE INDEX setting_profile_game_id_category_id_idx ON game.setting USING btree (profile_game_id, category_id);


--
-- Name: title_name_idx; Type: INDEX; Schema: game; Owner: pcs
--

CREATE INDEX title_name_idx ON game.title USING btree (name);


--
-- Name: component_name_idx; Type: INDEX; Schema: hardware; Owner: pcs
--

CREATE INDEX component_name_idx ON hardware.component USING btree (name);


--
-- Name: setting_component_id_idx; Type: INDEX; Schema: hardware; Owner: pcs
--

CREATE INDEX setting_component_id_idx ON hardware.setting USING btree (component_id);


--
-- Name: setting_user_id_component_id_idx; Type: INDEX; Schema: hardware; Owner: pcs
--

CREATE INDEX setting_user_id_component_id_idx ON hardware.setting USING btree (user_id, component_id);


--
-- Name: authentication_user_id_idx; Type: INDEX; Schema: profile; Owner: pcs
--

CREATE UNIQUE INDEX authentication_user_id_idx ON profile.authentication USING btree (user_id);


--
-- Name: user_deleted_username_idx; Type: INDEX; Schema: profile; Owner: pcs
--

CREATE INDEX user_deleted_username_idx ON profile."user" USING btree (deleted, username);


--
-- Name: user_username_idx; Type: INDEX; Schema: profile; Owner: pcs
--

CREATE UNIQUE INDEX user_username_idx ON profile."user" USING btree (username);


--
-- Name: profile_game profile_game_title_id_fkey; Type: FK CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.profile_game
    ADD CONSTRAINT profile_game_title_id_fkey FOREIGN KEY (title_id) REFERENCES game.title(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: profile_game profile_game_user_id_fkey; Type: FK CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.profile_game
    ADD CONSTRAINT profile_game_user_id_fkey FOREIGN KEY (user_id) REFERENCES profile."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: setting setting_category_id_fkey; Type: FK CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.setting
    ADD CONSTRAINT setting_category_id_fkey FOREIGN KEY (category_id) REFERENCES game.category(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: setting setting_profile_game_id_fkey; Type: FK CONSTRAINT; Schema: game; Owner: pcs
--

ALTER TABLE ONLY game.setting
    ADD CONSTRAINT setting_profile_game_id_fkey FOREIGN KEY (profile_game_id) REFERENCES game.profile_game(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: setting setting_component_id_fkey; Type: FK CONSTRAINT; Schema: hardware; Owner: pcs
--

ALTER TABLE ONLY hardware.setting
    ADD CONSTRAINT setting_component_id_fkey FOREIGN KEY (component_id) REFERENCES hardware.component(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: setting setting_user_id_fkey; Type: FK CONSTRAINT; Schema: hardware; Owner: pcs
--

ALTER TABLE ONLY hardware.setting
    ADD CONSTRAINT setting_user_id_fkey FOREIGN KEY (user_id) REFERENCES profile."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: authentication authentication_user_id_fkey; Type: FK CONSTRAINT; Schema: profile; Owner: pcs
--

ALTER TABLE ONLY profile.authentication
    ADD CONSTRAINT authentication_user_id_fkey FOREIGN KEY (user_id) REFERENCES profile."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

\connect postgres

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.11
-- Dumped by pg_dump version 9.6.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- PostgreSQL database dump complete
--

\connect template1

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.11
-- Dumped by pg_dump version 9.6.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

