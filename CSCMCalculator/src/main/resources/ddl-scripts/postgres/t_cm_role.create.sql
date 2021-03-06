CREATE TABLE T_CM_ROLE (
	ID   			NUMERIC(6),
	NAME			VARCHAR(50)	NOT NULL,
	DESCRIPTION  	VARCHAR(100)
);

COMMENT ON TABLE 	T_CM_ROLE 				IS 'This table contains definitions of user roles';
COMMENT ON COLUMN 	T_CM_ROLE.ID 			IS 'Role ID';
COMMENT ON COLUMN 	T_CM_ROLE.NAME 			IS 'Role name';
COMMENT ON COLUMN 	T_CM_ROLE.DESCRIPTION 	IS 'Role description';

CREATE UNIQUE INDEX I_CM_ROLE_ID ON T_CM_ROLE (ID);

ALTER TABLE T_CM_ROLE ADD 
	CONSTRAINT PK_CM_ROLE PRIMARY KEY USING INDEX I_CM_ROLE_ID;