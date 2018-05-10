CREATE TABLE T_CM_USER2ROLE_GROUPS (
	USER_ID      	NUMERIC(10),
	ROLE_GROUP_ID  	NUMERIC(10)
);

COMMENT ON TABLE 	T_CM_USER2ROLE_GROUPS 					IS 'Table for linking users and role groups';
COMMENT ON COLUMN 	T_CM_USER2ROLE_GROUPS.USER_ID 			IS 'User ID';
COMMENT ON COLUMN 	T_CM_USER2ROLE_GROUPS.ROLE_GROUP_ID 	IS 'Role Group ID';

CREATE UNIQUE INDEX I_CM_USER2RG_UID2RGID ON T_CM_USER2ROLE_GROUPS (USER_ID, ROLE_GROUP_ID);

ALTER TABLE T_CM_USER2ROLE_GROUPS ADD
	CONSTRAINT PK_CM_USER2ROLE_GROUPS PRIMARY KEY USING INDEX I_CM_USER2RG_UID2RGID;