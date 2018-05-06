CREATE TABLE T_CM_USER (
	ID							NUMERIC(10),
	LOGIN						VARCHAR(20)		NOT NULL,
	PASSWORD_HASH				VARCHAR(89),
	DESCRIPTION     			VARCHAR(100),
	STATUS_ID       			NUMERIC(2)				NOT NULL,
	NAME            			VARCHAR(50)		NOT NULL,
	POSITION        			VARCHAR(50),
	PHONE_NUMBER				VARCHAR(15),
	LOCKING       				NUMERIC(2)				DEFAULT 0 NOT NULL,
	NEED_PASSWORD_CHANGE		NUMERIC(1)               DEFAULT 1 NOT NULL,
	BAD_ATTEMPT_COUNT  			NUMERIC(2)               DEFAULT 0 NOT NULL,
	PASSWORD_CHANGE_DATETIME  	TIMESTAMP,
	LAST_LOGON_DATETIME  		TIMESTAMP,
	LOCALE             			VARCHAR(5),
    START_PAGE                  VARCHAR(100)
);

COMMENT ON TABLE 	T_CM_USER 							IS 'Table for storing User data';
COMMENT ON COLUMN 	T_CM_USER.ID 						IS 'User ID';
COMMENT ON COLUMN 	T_CM_USER.LOGIN 					IS 'User interface login';
COMMENT ON COLUMN 	T_CM_USER.PASSWORD_HASH				IS 'User interface password SHA-512 hash';
COMMENT ON COLUMN 	T_CM_USER.DESCRIPTION 				IS 'User description';
COMMENT ON COLUMN 	T_CM_USER.STATUS_ID 				IS 'User status ID';
COMMENT ON COLUMN 	T_CM_USER.NAME 						IS 'User full name';
COMMENT ON COLUMN 	T_CM_USER.POSITION 					IS 'User position';
COMMENT ON COLUMN 	T_CM_USER.PHONE_NUMBER 				IS 'User phone number';
COMMENT ON COLUMN 	T_CM_USER.LOCKING 					IS 'Field marking whether the user is locked and reason of that';
COMMENT ON COLUMN 	T_CM_USER.NEED_PASSWORD_CHANGE 		IS 'Flag marking whether the change password is needed at next logon';
COMMENT ON COLUMN 	T_CM_USER.BAD_ATTEMPT_COUNT 		IS 'Number of bad attempts of logon after last succesful logon';
COMMENT ON COLUMN 	T_CM_USER.PASSWORD_CHANGE_DATETIME 	IS 'Date and time of last password change';
COMMENT ON COLUMN 	T_CM_USER.LAST_LOGON_DATETIME 		IS 'Date and time of last logon';
COMMENT ON COLUMN 	T_CM_USER.LOCALE 					IS 'User interface language';
COMMENT ON COLUMN 	T_CM_USER.START_PAGE 				IS 'Interface start page for user';


CREATE UNIQUE INDEX I_CM_USER_ID 		ON T_CM_USER (ID);
CREATE UNIQUE INDEX I_CM_USER_LOGIN 	ON T_CM_USER (LOGIN);

ALTER TABLE T_CM_USER ADD 
	CONSTRAINT PK_CM_USER PRIMARY KEY USING INDEX I_CM_USER_ID;