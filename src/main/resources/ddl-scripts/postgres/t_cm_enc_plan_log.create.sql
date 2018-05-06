CREATE TABLE T_CM_ENC_PLAN_LOG (
  	ENC_PLAN_ID   	NUMERIC(6)          	NOT NULL,
  	LOG_DATE      	TIMESTAMP               	NOT NULL,
  	USER_ID       	NUMERIC(10)         	DEFAULT -1,
  	MESSAGE       	VARCHAR(1500),
  	MESSAGE_TYPE  	NUMERIC(1),
  	LOG_ID        	NUMERIC(8)          	NOT NULL,
    MESSAGE_PARAMS  VARCHAR(100)
);

COMMENT ON TABLE 	T_CM_ENC_PLAN_LOG 					IS 'This table contains messages for replenishments';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.ENC_PLAN_ID 		IS 'Planned replenishment ID';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.LOG_DATE 			IS 'Date of message';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.USER_ID 			IS 'User person ID';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.MESSAGE 			IS 'Message text';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.MESSAGE_TYPE 		IS 'Message type';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.LOG_ID 			IS 'Message ID';
COMMENT ON COLUMN 	T_CM_ENC_PLAN_LOG.MESSAGE_PARAMS  	IS 'Comma-separated list of strings';