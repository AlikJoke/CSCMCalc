CREATE TABLE T_CM_USER_LOCKING (
	ID   			NUMERIC(2),
	DESCRIPTION  	VARCHAR(100)
);

COMMENT ON TABLE	T_CM_USER_LOCKING 				IS 'This table contains user locks';
COMMENT ON COLUMN	T_CM_USER_LOCKING.ID 			IS 'Lock ID';
COMMENT ON COLUMN	T_CM_USER_LOCKING.DESCRIPTION	IS 'Description';

CREATE UNIQUE INDEX I_CM_USER_LOCKING_ID ON T_CM_USER_LOCKING (ID);

ALTER TABLE T_CM_USER_LOCKING ADD 
	CONSTRAINT PK_CM_USER_LOCKING PRIMARY KEY USING INDEX I_CM_USER_LOCKING_ID;