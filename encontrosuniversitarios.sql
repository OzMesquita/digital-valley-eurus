-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: encontrosuniversitarios | type: DATABASE --
-- -- DROP DATABASE IF EXISTS encontrosuniversitarios;
-- CREATE DATABASE encontrosuniversitarios
-- 	ENCODING = 'UTF8'
-- 	LC_COLLATE = 'pt_BR.UTF-8'
-- 	LC_CTYPE = 'pt_BR.UTF-8'
-- 	TABLESPACE = pg_default
-- 	OWNER = postgres;
-- -- ddl-end --
-- 

-- object: public.atividade_id_atividade_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_id_atividade_seq CASCADE;
CREATE SEQUENCE public.atividade_id_atividade_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_id_atividade_seq OWNER TO postgres;
-- ddl-end --

-- object: public.trabalho_id_trabalho_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.trabalho_id_trabalho_seq CASCADE;
CREATE SEQUENCE public.trabalho_id_trabalho_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.trabalho_id_trabalho_seq OWNER TO postgres;
-- ddl-end --

-- object: public.frequencia_id_frequencia_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.frequencia_id_frequencia_seq CASCADE;
CREATE SEQUENCE public.frequencia_id_frequencia_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.frequencia_id_frequencia_seq OWNER TO postgres;
-- ddl-end --

-- object: public.sala_id_sala_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sala_id_sala_seq CASCADE;
CREATE SEQUENCE public.sala_id_sala_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.sala_id_sala_seq OWNER TO postgres;
-- ddl-end --

-- object: public.atividades_sala_atividade_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividades_sala_atividade_fk_seq CASCADE;
CREATE SEQUENCE public.atividades_sala_atividade_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividades_sala_atividade_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.atividades_sala_sala_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividades_sala_sala_fk_seq CASCADE;
CREATE SEQUENCE public.atividades_sala_sala_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividades_sala_sala_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.atividades_sala | type: TABLE --
-- DROP TABLE IF EXISTS public.atividades_sala CASCADE;
CREATE TABLE public.atividades_sala(
	atividade_fk integer NOT NULL DEFAULT nextval('public.atividades_sala_atividade_fk_seq'::regclass),
	sala_fk integer NOT NULL DEFAULT nextval('public.atividades_sala_sala_fk_seq'::regclass)
);
-- ddl-end --
ALTER TABLE public.atividades_sala OWNER TO postgres;
-- ddl-end --

-- object: public.atividades_frequencia_atividade_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividades_frequencia_atividade_fk_seq CASCADE;
CREATE SEQUENCE public.atividades_frequencia_atividade_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividades_frequencia_atividade_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.atividades_frequencia_frequencia_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividades_frequencia_frequencia_fk_seq CASCADE;
CREATE SEQUENCE public.atividades_frequencia_frequencia_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividades_frequencia_frequencia_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.atividades_frequencia | type: TABLE --
-- DROP TABLE IF EXISTS public.atividades_frequencia CASCADE;
CREATE TABLE public.atividades_frequencia(
	atividade_fk integer NOT NULL DEFAULT nextval('public.atividades_frequencia_atividade_fk_seq'::regclass),
	frequencia_fk integer NOT NULL DEFAULT nextval('public.atividades_frequencia_frequencia_fk_seq'::regclass)
);
-- ddl-end --
ALTER TABLE public.atividades_frequencia OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_categoria_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_categoria_fk_seq CASCADE;
CREATE SEQUENCE public.atividade_categoria_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_categoria_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.frequencia_sala_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.frequencia_sala_fk_seq CASCADE;
CREATE SEQUENCE public.frequencia_sala_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.frequencia_sala_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.frequencia_id_frequencia_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.frequencia_id_frequencia_seq1 CASCADE;
CREATE SEQUENCE public.frequencia_id_frequencia_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.frequencia_id_frequencia_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.frequencia_usuario_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.frequencia_usuario_fk_seq CASCADE;
CREATE SEQUENCE public.frequencia_usuario_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.frequencia_usuario_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.frequencia | type: TABLE --
-- DROP TABLE IF EXISTS public.frequencia CASCADE;
CREATE TABLE public.frequencia(
	sala_fk integer NOT NULL DEFAULT nextval('public.frequencia_sala_fk_seq'::regclass),
	check_in timestamp,
	check_out timestamp,
	id_frequencia integer NOT NULL DEFAULT nextval('public.frequencia_id_frequencia_seq1'::regclass),
	usuario_fk integer NOT NULL DEFAULT nextval('public.frequencia_usuario_fk_seq'::regclass),
	CONSTRAINT frequencia_pkey PRIMARY KEY (id_frequencia)

);
-- ddl-end --
ALTER TABLE public.frequencia OWNER TO postgres;
-- ddl-end --

-- object: public.trabalho_modalidade_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.trabalho_modalidade_seq CASCADE;
CREATE SEQUENCE public.trabalho_modalidade_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.trabalho_modalidade_seq OWNER TO postgres;
-- ddl-end --

-- object: public.categoria_id_categoria_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.categoria_id_categoria_seq CASCADE;
CREATE SEQUENCE public.categoria_id_categoria_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.categoria_id_categoria_seq OWNER TO postgres;
-- ddl-end --

-- object: public.categoria_id_categoria_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.categoria_id_categoria_seq1 CASCADE;
CREATE SEQUENCE public.categoria_id_categoria_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.categoria_id_categoria_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.categoria | type: TABLE --
-- DROP TABLE IF EXISTS public.categoria CASCADE;
CREATE TABLE public.categoria(
	id_categoria integer NOT NULL DEFAULT nextval('public.categoria_id_categoria_seq1'::regclass),
	nome_categoria text,
	descricao text,
	CONSTRAINT categoria_pkey PRIMARY KEY (id_categoria)

);
-- ddl-end --
ALTER TABLE public.categoria OWNER TO postgres;
-- ddl-end --

-- object: public.sala_id_sala_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.sala_id_sala_seq1 CASCADE;
CREATE SEQUENCE public.sala_id_sala_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.sala_id_sala_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.sala | type: TABLE --
-- DROP TABLE IF EXISTS public.sala CASCADE;
CREATE TABLE public.sala(
	numero integer,
	nome_sala text,
	id_sala integer NOT NULL DEFAULT nextval('public.sala_id_sala_seq1'::regclass),
	CONSTRAINT sala_pkey PRIMARY KEY (id_sala)

);
-- ddl-end --
ALTER TABLE public.sala OWNER TO postgres;
-- ddl-end --

-- object: public.coautores_trabalho_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.coautores_trabalho_fk_seq CASCADE;
CREATE SEQUENCE public.coautores_trabalho_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.coautores_trabalho_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.coautores_trabalho_fk_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.coautores_trabalho_fk_seq1 CASCADE;
CREATE SEQUENCE public.coautores_trabalho_fk_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.coautores_trabalho_fk_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.coautores_usuario_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.coautores_usuario_fk_seq CASCADE;
CREATE SEQUENCE public.coautores_usuario_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.coautores_usuario_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.coautores | type: TABLE --
-- DROP TABLE IF EXISTS public.coautores CASCADE;
CREATE TABLE public.coautores(
	trabalho_fk integer NOT NULL DEFAULT nextval('public.coautores_trabalho_fk_seq1'::regclass),
	usuario_fk integer NOT NULL DEFAULT nextval('public.coautores_usuario_fk_seq'::regclass)
);
-- ddl-end --
ALTER TABLE public.coautores OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_local_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_local_fk_seq CASCADE;
CREATE SEQUENCE public.atividade_local_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_local_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.local_id_local_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.local_id_local_seq CASCADE;
CREATE SEQUENCE public.local_id_local_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.local_id_local_seq OWNER TO postgres;
-- ddl-end --

-- object: public.local_sala_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.local_sala_fk_seq CASCADE;
CREATE SEQUENCE public.local_sala_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.local_sala_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.local_id_local_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.local_id_local_seq1 CASCADE;
CREATE SEQUENCE public.local_id_local_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.local_id_local_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.local_sala_fk_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.local_sala_fk_seq1 CASCADE;
CREATE SEQUENCE public.local_sala_fk_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.local_sala_fk_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.local | type: TABLE --
-- DROP TABLE IF EXISTS public.local CASCADE;
CREATE TABLE public.local(
	id_local integer NOT NULL DEFAULT nextval('public.local_id_local_seq1'::regclass),
	nome_local text,
	ponto_referencia_local text,
	andar_local text,
	sala_fk integer NOT NULL DEFAULT nextval('public.local_sala_fk_seq1'::regclass),
	CONSTRAINT local_pkey PRIMARY KEY (id_local)

);
-- ddl-end --
ALTER TABLE public.local OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_trabalho_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_trabalho_fk_seq CASCADE;
CREATE SEQUENCE public.atividade_trabalho_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_trabalho_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_id_atividade_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_id_atividade_seq1 CASCADE;
CREATE SEQUENCE public.atividade_id_atividade_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_id_atividade_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_categoria_fk_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_categoria_fk_seq1 CASCADE;
CREATE SEQUENCE public.atividade_categoria_fk_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_categoria_fk_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_local_fk_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_local_fk_seq1 CASCADE;
CREATE SEQUENCE public.atividade_local_fk_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_local_fk_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.atividade_apresentador_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.atividade_apresentador_fk_seq CASCADE;
CREATE SEQUENCE public.atividade_apresentador_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.atividade_apresentador_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.usuario_id_usuario_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.usuario_id_usuario_seq CASCADE;
CREATE SEQUENCE public.usuario_id_usuario_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.usuario_id_usuario_seq OWNER TO postgres;
-- ddl-end --

-- object: public.usuario | type: TABLE --
-- DROP TABLE IF EXISTS public.usuario CASCADE;
CREATE TABLE public.usuario(
	cpf text,
	matricula text,
	email text,
	senha text,
	nivel_acesso text,
	id_usuario integer NOT NULL DEFAULT nextval('public.usuario_id_usuario_seq'::regclass),
	nome text,
	CONSTRAINT uniq UNIQUE (cpf),
	CONSTRAINT uniq2 UNIQUE (matricula),
	CONSTRAINT usuario_pk PRIMARY KEY (id_usuario)

);
-- ddl-end --
ALTER TABLE public.usuario OWNER TO postgres;
-- ddl-end --

-- object: public.trabalho_id_trabalho_seq1 | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.trabalho_id_trabalho_seq1 CASCADE;
CREATE SEQUENCE public.trabalho_id_trabalho_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.trabalho_id_trabalho_seq1 OWNER TO postgres;
-- ddl-end --

-- object: public.trabalho_autor_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.trabalho_autor_fk_seq CASCADE;
CREATE SEQUENCE public.trabalho_autor_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.trabalho_autor_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.trabalho_modalidade_fk_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.trabalho_modalidade_fk_seq CASCADE;
CREATE SEQUENCE public.trabalho_modalidade_fk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
ALTER SEQUENCE public.trabalho_modalidade_fk_seq OWNER TO postgres;
-- ddl-end --

-- object: public.trabalho | type: TABLE --
-- DROP TABLE IF EXISTS public.trabalho CASCADE;
CREATE TABLE public.trabalho(
	titulo text,
	id_trabalho integer NOT NULL DEFAULT nextval('public.trabalho_id_trabalho_seq1'::regclass),
	orientador text,
	autor_fk integer NOT NULL DEFAULT nextval('public.trabalho_autor_fk_seq'::regclass),
	modalidade_fk integer NOT NULL DEFAULT nextval('public.trabalho_modalidade_fk_seq'::regclass),
	CONSTRAINT trabalho_pkey PRIMARY KEY (id_trabalho)

);
-- ddl-end --
ALTER TABLE public.trabalho OWNER TO postgres;
-- ddl-end --

-- object: public.atividade | type: TABLE --
-- DROP TABLE IF EXISTS public.atividade CASCADE;
CREATE TABLE public.atividade(
	horario_previsto timestamp,
	horario_inicial timestamp,
	horario_final timestamp,
	trabalho_fk integer NOT NULL DEFAULT nextval('public.atividade_trabalho_fk_seq'::regclass),
	id_atividade integer NOT NULL DEFAULT nextval('public.atividade_id_atividade_seq1'::regclass),
	descricao text,
	nome_atividade text,
	categoria_fk integer NOT NULL DEFAULT nextval('public.atividade_categoria_fk_seq1'::regclass),
	local_fk integer NOT NULL DEFAULT nextval('public.atividade_local_fk_seq1'::regclass),
	apresentador_fk integer NOT NULL DEFAULT nextval('public.atividade_apresentador_fk_seq'::regclass),
	CONSTRAINT atividade_pkey PRIMARY KEY (id_atividade)

);
-- ddl-end --
ALTER TABLE public.atividade OWNER TO postgres;
-- ddl-end --

-- object: atividades_sala_fkey | type: CONSTRAINT --
-- ALTER TABLE public.atividades_sala DROP CONSTRAINT IF EXISTS atividades_sala_fkey CASCADE;
ALTER TABLE public.atividades_sala ADD CONSTRAINT atividades_sala_fkey FOREIGN KEY (atividade_fk)
REFERENCES public.atividade (id_atividade) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: atividades_sala_fkey2 | type: CONSTRAINT --
-- ALTER TABLE public.atividades_sala DROP CONSTRAINT IF EXISTS atividades_sala_fkey2 CASCADE;
ALTER TABLE public.atividades_sala ADD CONSTRAINT atividades_sala_fkey2 FOREIGN KEY (sala_fk)
REFERENCES public.sala (id_sala) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: atividades_freq2 | type: CONSTRAINT --
-- ALTER TABLE public.atividades_frequencia DROP CONSTRAINT IF EXISTS atividades_freq2 CASCADE;
ALTER TABLE public.atividades_frequencia ADD CONSTRAINT atividades_freq2 FOREIGN KEY (frequencia_fk)
REFERENCES public.frequencia (id_frequencia) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: atividades_freq | type: CONSTRAINT --
-- ALTER TABLE public.atividades_frequencia DROP CONSTRAINT IF EXISTS atividades_freq CASCADE;
ALTER TABLE public.atividades_frequencia ADD CONSTRAINT atividades_freq FOREIGN KEY (atividade_fk)
REFERENCES public.atividade (id_atividade) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: frequencia_fkey | type: CONSTRAINT --
-- ALTER TABLE public.frequencia DROP CONSTRAINT IF EXISTS frequencia_fkey CASCADE;
ALTER TABLE public.frequencia ADD CONSTRAINT frequencia_fkey FOREIGN KEY (sala_fk)
REFERENCES public.sala (id_sala) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: frequencia_fkey2 | type: CONSTRAINT --
-- ALTER TABLE public.frequencia DROP CONSTRAINT IF EXISTS frequencia_fkey2 CASCADE;
ALTER TABLE public.frequencia ADD CONSTRAINT frequencia_fkey2 FOREIGN KEY (usuario_fk)
REFERENCES public.usuario (id_usuario) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: coautores_fkey | type: CONSTRAINT --
-- ALTER TABLE public.coautores DROP CONSTRAINT IF EXISTS coautores_fkey CASCADE;
ALTER TABLE public.coautores ADD CONSTRAINT coautores_fkey FOREIGN KEY (trabalho_fk)
REFERENCES public.trabalho (id_trabalho) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: coautores_fkey2 | type: CONSTRAINT --
-- ALTER TABLE public.coautores DROP CONSTRAINT IF EXISTS coautores_fkey2 CASCADE;
ALTER TABLE public.coautores ADD CONSTRAINT coautores_fkey2 FOREIGN KEY (usuario_fk)
REFERENCES public.usuario (id_usuario) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: atividade_fkey | type: CONSTRAINT --
-- ALTER TABLE public.atividade DROP CONSTRAINT IF EXISTS atividade_fkey CASCADE;
ALTER TABLE public.atividade ADD CONSTRAINT atividade_fkey FOREIGN KEY (trabalho_fk)
REFERENCES public.trabalho (id_trabalho) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: atividade_fkey2 | type: CONSTRAINT --
-- ALTER TABLE public.atividade DROP CONSTRAINT IF EXISTS atividade_fkey2 CASCADE;
ALTER TABLE public.atividade ADD CONSTRAINT atividade_fkey2 FOREIGN KEY (categoria_fk)
REFERENCES public.categoria (id_categoria) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: atividade_fkey3 | type: CONSTRAINT --
-- ALTER TABLE public.atividade DROP CONSTRAINT IF EXISTS atividade_fkey3 CASCADE;
ALTER TABLE public.atividade ADD CONSTRAINT atividade_fkey3 FOREIGN KEY (local_fk)
REFERENCES public.local (id_local) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: trabalho_fkey4 | type: CONSTRAINT --
-- ALTER TABLE public.atividade DROP CONSTRAINT IF EXISTS trabalho_fkey4 CASCADE;
ALTER TABLE public.atividade ADD CONSTRAINT trabalho_fkey4 FOREIGN KEY (apresentador_fk)
REFERENCES public.usuario (id_usuario) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: trabalho_fkey | type: CONSTRAINT --
-- ALTER TABLE public.trabalho DROP CONSTRAINT IF EXISTS trabalho_fkey CASCADE;
ALTER TABLE public.trabalho ADD CONSTRAINT trabalho_fkey FOREIGN KEY (autor_fk)
REFERENCES public.usuario (id_usuario) MATCH SIMPLE
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: trabalho_fkey2 | type: CONSTRAINT --
-- ALTER TABLE public.trabalho DROP CONSTRAINT IF EXISTS trabalho_fkey2 CASCADE;
ALTER TABLE public.trabalho ADD CONSTRAINT trabalho_fkey2 FOREIGN KEY (modalidade_fk)
REFERENCES public.categoria (id_categoria) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


