ALTER TABLE T_CM_USER2ATM_GROUPS ADD CONSTRAINT FK_CM_USER2ATM_GROUPS_UID	FOREIGN KEY (USER_ID) 		REFERENCES T_CM_USER (ID) 		ON DELETE CASCADE;
ALTER TABLE T_CM_USER2ATM_GROUPS ADD CONSTRAINT FK_CM_USER2ATM_GROUPS_GID	FOREIGN KEY (ATM_GROUP_ID) 	REFERENCES T_CM_ATM_GROUP (ID)	ON DELETE CASCADE;