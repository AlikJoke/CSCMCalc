ALTER TABLE T_CM_USER2INSTS ADD CONSTRAINT FK_CM_USER2INSTS_UID 	FOREIGN KEY (USER_ID) 	REFERENCES T_CM_USER (ID)	ON DELETE CASCADE;
ALTER TABLE T_CM_USER2INSTS ADD CONSTRAINT FK_CM_USER2INSTS_IID 	FOREIGN KEY (INST_ID) 	REFERENCES T_CM_INST (ID)	ON DELETE CASCADE;