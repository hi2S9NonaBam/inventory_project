DROP TABLE Entity CASCADE CONSTRAINTS ;

DROP TABLE EntityType CASCADE CONSTRAINTS ;

DROP TABLE List CASCADE CONSTRAINTS ;

DROP TABLE Parameters CASCADE CONSTRAINTS ;

DROP TABLE ParametersTypes CASCADE CONSTRAINTS ;

DROP SEQUENCE EntityType_seq;

DROP SEQUENCE Entity_seq;

DROP SEQUENCE List_seq;

DROP SEQUENCE ParametersTypes_seq;

CREATE TABLE Entity
  (
    Entity_id     INTEGER NOT NULL ,
    Name          VARCHAR2 (20) ,
    EntityType_id INTEGER NOT NULL ,
    Parent_id     INTEGER
  ) ;
ALTER TABLE Entity ADD CONSTRAINT Entity_PK PRIMARY KEY ( Entity_id ) ;


CREATE TABLE EntityType
  (
    EntityType_id INTEGER NOT NULL ,
    Name          VARCHAR2 (20) ,
    Parent_id     INTEGER
  ) ;
ALTER TABLE EntityType ADD CONSTRAINT EntityType_PK PRIMARY KEY ( EntityType_id ) ;
ALTER TABLE EntityType ADD CONSTRAINT EntityType__UN UNIQUE ( Name ) ;


CREATE TABLE List
  (
    List_id            INTEGER NOT NULL ,
    Value              VARCHAR2 (20) ,
    ParametersTypes_id INTEGER NOT NULL
  ) ;
ALTER TABLE List ADD CONSTRAINT List_PK PRIMARY KEY ( List_id ) ;
ALTER TABLE List ADD CONSTRAINT List__UN UNIQUE ( Value ) ;


CREATE TABLE Parameters
  (
    Entity_id          INTEGER NOT NULL ,
    ParametersTypes_id INTEGER NOT NULL ,
    Value_date         DATE ,
    Value_char         VARCHAR2 (50) ,
    Value_clob CLOB ,
    List_id INTEGER
  ) ;
ALTER TABLE Parameters ADD CONSTRAINT Parameters_PK PRIMARY KEY ( Entity_id, ParametersTypes_id ) ;


CREATE TABLE ParametersTypes
  (
    ParametersTypes_id INTEGER NOT NULL ,
    Name               VARCHAR2 (20) ,
    Type               VARCHAR2 (10)
  ) ;
ALTER TABLE ParametersTypes ADD CONSTRAINT ParametersTypes_PK PRIMARY KEY ( ParametersTypes_id ) ;
ALTER TABLE ParametersTypes ADD CONSTRAINT ParametersTypes__UN UNIQUE ( Name , Type ) ;


ALTER TABLE EntityType ADD CONSTRAINT EntityType_EntityType_FKv1 FOREIGN KEY ( Parent_id ) REFERENCES EntityType ( EntityType_id ) ;

ALTER TABLE Entity ADD CONSTRAINT Entity_EntityType_FK FOREIGN KEY ( EntityType_id ) REFERENCES EntityType ( EntityType_id ) ON
DELETE CASCADE ;

ALTER TABLE Entity ADD CONSTRAINT Entity_Entity_FKv2 FOREIGN KEY ( Parent_id ) REFERENCES Entity ( Entity_id ) ;

ALTER TABLE List ADD CONSTRAINT List_ParametersTypes_FK FOREIGN KEY ( ParametersTypes_id ) REFERENCES ParametersTypes ( ParametersTypes_id ) ON
DELETE CASCADE ;

ALTER TABLE Parameters ADD CONSTRAINT Parameters_Entity_FK FOREIGN KEY ( Entity_id ) REFERENCES Entity ( Entity_id ) ON
DELETE CASCADE ;

ALTER TABLE Parameters ADD CONSTRAINT Parameters_List_FK FOREIGN KEY ( List_id ) REFERENCES List ( List_id ) ;

ALTER TABLE Parameters ADD CONSTRAINT Parameters_ParametersTypes_FK FOREIGN KEY ( ParametersTypes_id ) REFERENCES ParametersTypes ( ParametersTypes_id ) ON
DELETE CASCADE ;

CREATE SEQUENCE Entity_seq START WITH 1 ;
CREATE OR REPLACE TRIGGER Entity_bir BEFORE
  INSERT ON Entity FOR EACH ROW BEGIN :NEW.Entity_id := Entity_seq.NEXTVAL;
END;
/

CREATE SEQUENCE EntityType_seq START WITH 1 ;
CREATE OR REPLACE TRIGGER EntityType_bir BEFORE
  INSERT ON EntityType FOR EACH ROW BEGIN :NEW.EntityType_id := EntityType_seq.NEXTVAL;
END;
/

CREATE SEQUENCE List_seq START WITH 1 ;
CREATE OR REPLACE TRIGGER List_bir BEFORE
  INSERT ON List FOR EACH ROW BEGIN :NEW.List_id := List_seq.NEXTVAL;
END;
/

CREATE SEQUENCE ParametersTypes_seq START WITH 1 ;
CREATE OR REPLACE TRIGGER ParametersTypes_bir BEFORE
  INSERT ON ParametersTypes FOR EACH ROW BEGIN :NEW.ParametersTypes_id := ParametersTypes_seq.NEXTVAL;
END;
/