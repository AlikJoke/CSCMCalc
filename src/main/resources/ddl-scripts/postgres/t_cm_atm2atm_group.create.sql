CREATE TABLE T_CM_ATM2ATM_GROUP (
  	ATM_GROUP_ID	NUMERIC(5)	NOT NULL,
  	ATM_ID        	NUMERIC(9)   NOT NULL
);

COMMENT ON TABLE 	T_CM_ATM2ATM_GROUP 				IS 'Table for linking ATMs and ATM groups';
COMMENT ON COLUMN 	T_CM_ATM2ATM_GROUP.ATM_GROUP_ID IS 'Unique ID identifying ATM group';
COMMENT ON COLUMN 	T_CM_ATM2ATM_GROUP.ATM_ID 		IS 'Unique ID identifying ATM';

CREATE UNIQUE INDEX I_CM_ATM2ATM_GROUP_AGID2AID ON T_CM_ATM2ATM_GROUP (ATM_GROUP_ID, ATM_ID);

ALTER TABLE T_CM_ATM2ATM_GROUP ADD
  	CONSTRAINT PK_CM_ATM2ATM_GROUP PRIMARY KEY USING INDEX I_CM_ATM2ATM_GROUP_AGID2AID;
