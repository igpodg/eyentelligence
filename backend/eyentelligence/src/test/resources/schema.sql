-- Drop if exists

DROP TABLE IF EXISTS dashboard CASCADE;
DROP TABLE IF EXISTS dashboard_filter CASCADE;
DROP TABLE IF EXISTS data_source CASCADE;
DROP TABLE IF EXISTS data_source_dashboard CASCADE;
DROP TABLE IF EXISTS invitation CASCADE;
DROP TABLE IF EXISTS session CASCADE;
DROP TABLE IF EXISTS team CASCADE;
DROP TABLE IF EXISTS team_management CASCADE;
DROP TABLE IF EXISTS tile CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS visualization CASCADE;

-- Create

CREATE TABLE dashboard
(
	id serial NOT NULL,
	team_id integer NOT NULL,
	name varchar(255) NOT NULL,
	created_date_time timestamp without time zone NOT NULL,
	last_modified_date_time timestamp without time zone NOT NULL
);

CREATE TABLE dashboard_filter
(
	id serial NOT NULL,
	priority smallint NOT NULL,
	name varchar(255) NOT NULL,
	dashboard_id integer NOT NULL,
	settings jsonb NOT NULL
);

CREATE TABLE data_source
(
	id serial NOT NULL,
	type char(1) NOT NULL,
	name varchar(255) NOT NULL,
	settings jsonb NOT NULL
);

CREATE TABLE data_source_dashboard
(
	data_source_id integer NOT NULL,
	dashboard_id integer NOT NULL,
	is_active boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE invitation
(
	id serial NOT NULL,
	creator_user_id integer NOT NULL,
	target_team_id integer NOT NULL,
	created_date_time timestamp without time zone NOT NULL,
	expiration_date_time timestamp without time zone NULL
);

CREATE TABLE session
(
	id serial NOT NULL,
	user_id integer NOT NULL,
	issue_date_time timestamp without time zone NOT NULL,
	issue_clientua varchar(2048) NOT NULL,
	issue_clientip cidr NOT NULL,
	last_date_time timestamp without time zone NOT NULL,
	last_clientua varchar(2048) NOT NULL,
	last_clientip cidr NOT NULL
);

CREATE TABLE team
(
	id serial NOT NULL,
	name varchar(255) NOT NULL,
	type char(1) NOT NULL,
	parent_team_id integer NULL
);

CREATE TABLE team_management
(
	user_id integer NOT NULL,
	team_id integer NOT NULL,
	role char(1) NOT NULL
);

CREATE TABLE tile
(
	id serial NOT NULL,
	dashboard_id integer NOT NULL,
	width smallint NOT NULL,
	height smallint NOT NULL,
	positionx smallint NOT NULL,
	positiony smallint NOT NULL,
	visualization_id integer NOT NULL
);

CREATE TABLE "user"
(
	id serial NOT NULL,
	username varchar(32) NOT NULL,
	password_hash varchar(255) NOT NULL,
	title varchar(5) NULL,
	first_name varchar(255) NOT NULL,
	middle_name varchar(255) NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NULL,
	avatar_link varchar(255) NULL
);

CREATE TABLE visualization
(
	id serial NOT NULL,
	type char(1) NOT NULL,
	settings jsonb NOT NULL
);

-- Primary keys

ALTER TABLE dashboard ADD CONSTRAINT "PK_Dashboard"
	PRIMARY KEY (id);

ALTER TABLE dashboard_filter ADD CONSTRAINT "PK_DashboardFilter"
	PRIMARY KEY (id);

ALTER TABLE data_source ADD CONSTRAINT "PK_DataSource"
	PRIMARY KEY (id);

ALTER TABLE data_source_dashboard ADD CONSTRAINT "PK_DataSource_Dashboard"
	PRIMARY KEY (data_source_id,dashboard_id);

ALTER TABLE invitation ADD CONSTRAINT "PK_Invitation"
	PRIMARY KEY (id);

ALTER TABLE session ADD CONSTRAINT "PK_Session"
	PRIMARY KEY (id);

ALTER TABLE team ADD CONSTRAINT "PK_Team"
	PRIMARY KEY (id);

ALTER TABLE team_management ADD CONSTRAINT "PK_TeamManagement"
	PRIMARY KEY (user_id,team_id);

ALTER TABLE tile ADD CONSTRAINT "PK_Tile"
	PRIMARY KEY (id);

ALTER TABLE "user" ADD CONSTRAINT "PK_User"
	PRIMARY KEY (id);

ALTER TABLE visualization ADD CONSTRAINT "PK_Visualization"
	PRIMARY KEY (id);

-- Foreign keys

ALTER TABLE dashboard ADD CONSTRAINT "FK_Team"
	FOREIGN KEY (team_id) REFERENCES team (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE dashboard_filter ADD CONSTRAINT "FK_Dashboard"
	FOREIGN KEY (dashboard_id) REFERENCES dashboard (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE invitation ADD CONSTRAINT "FK_Team"
	FOREIGN KEY (target_team_id) REFERENCES team (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE invitation ADD CONSTRAINT "FK_User"
	FOREIGN KEY (creator_user_id) REFERENCES "user" (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE session ADD CONSTRAINT "FK_User"
	FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--ALTER TABLE team ADD CONSTRAINT "FK_Team"
--	FOREIGN KEY (parent_team_id) REFERENCES team (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE team ADD CONSTRAINT "FK_Team"
	FOREIGN KEY (parent_team_id) REFERENCES team (id) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE tile ADD CONSTRAINT "FK_Visualization"
	FOREIGN KEY (visualization_id) REFERENCES visualization (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE tile ADD CONSTRAINT "FK_Dashboard"
	FOREIGN KEY (dashboard_id) REFERENCES dashboard (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Add Teams

INSERT INTO team (name, type, parent_team_id) VALUES ('Zathin', 'T', NULL);
INSERT INTO team (name, type, parent_team_id) VALUES ('Rank', 'O', 1);
INSERT INTO team (name, type, parent_team_id) VALUES ('Tresom', 'T', NULL);
INSERT INTO team (name, type, parent_team_id) VALUES ('Tres-Zap', 'O', 3);
INSERT INTO team (name, type, parent_team_id) VALUES ('Hatity', 'O', 4);
INSERT INTO team (name, type, parent_team_id) VALUES ('Toughjoyfax', 'O', 5);
INSERT INTO team (name, type, parent_team_id) VALUES ('Ventosanzap', 'O', 4);
INSERT INTO team (name, type, parent_team_id) VALUES ('Keylex', 'T', NULL);
INSERT INTO team (name, type, parent_team_id) VALUES ('Vagram', 'O', 5);
INSERT INTO team (name, type, parent_team_id) VALUES ('Daltfresh', 'O', 2);

-- Add User

INSERT INTO "user" (username, password_hash, title, first_name, middle_name, last_name, email, avatar_link)
	VALUES ('user', 'useruseruser', NULL, 'Anderea', NULL, 'Carson', NULL, NULL);
