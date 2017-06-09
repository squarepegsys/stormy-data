create sequence storm_type_id_seq;
create sequence state_id_seq;
create sequence storm_info_id_seq;

CREATE TABLE state (id INTEGER DEFAULT nextval('state_id_seq'::regclass) NOT NULL, name CHARACTER VARYING, PRIMARY KEY (id));

CREATE TABLE storm_type (id INTEGER DEFAULT nextval('storm_type_id_seq'::regclass) NOT NULL, name CHARACTER VARYING, PRIMARY KEY (id));

CREATE TABLE storm_info (id INTEGER DEFAULT nextval('storm_info_id_seq'::regclass) NOT NULL, begin_timestamp TIMESTAMP(6) WITHOUT TIME ZONE, end_timestamp TIMESTAMP(6) WITHOUT TIME ZONE, state_id INTEGER, storm_type_id INTEGER, comments TEXT, PRIMARY KEY (id));

ALTER TABLE storm_info ADD CONSTRAINT storm_info_state_id_fkey FOREIGN KEY (state_id) REFERENCES state (id);
ALTER TABLE storm_info ADD CONSTRAINT storm_info_storm_type_id_fkey FOREIGN KEY (storm_type_id) REFERENCES storm_type (id);


