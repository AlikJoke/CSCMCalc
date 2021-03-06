CREATE TABLE T_CM_ATM_GROUP_TYPE (
	  ID  			      NUMERIC(3),
  	DESCRIPTION   	VARCHAR(50)
);

COMMENT ON TABLE 	T_CM_ATM_GROUP_TYPE 				IS 'This table contains ATM types';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_TYPE.ID 				IS 'Unique ID identifying ATM group type';
COMMENT ON COLUMN 	T_CM_ATM_GROUP_TYPE.DESCRIPTION 	IS 'Group type description';

CREATE UNIQUE INDEX I_CM_ATM_GROUP_TYPE_ID ON T_CM_ATM_GROUP_TYPE (ID);
ALTER TABLE T_CM_ATM_GROUP_TYPE 
	ADD CONSTRAINT PK_CM_ATM_GROUP_TYPE PRIMARY KEY USING INDEX I_CM_ATM_GROUP_TYPE_ID;