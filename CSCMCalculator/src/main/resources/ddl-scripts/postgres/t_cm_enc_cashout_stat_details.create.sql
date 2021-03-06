CREATE TABLE T_CM_ENC_CASHOUT_STAT_DETAILS (
	ENCASHMENT_ID  NUMERIC(6)    NOT NULL,
	CASS_VALUE     NUMERIC(6)    NOT NULL,
	CASS_CURR      NUMERIC(3)    NOT NULL,
	CASS_COUNT     NUMERIC(6)    NOT NULL,
	ACTION_TYPE    NUMERIC(1)	NOT NULL,
	CASS_NUMBER    NUMERIC(2)
);

COMMENT ON TABLE 	T_CM_ENC_CASHOUT_STAT_DETAILS 				IS 'This table contains cassette details for loaded cash out replenishments';
COMMENT ON COLUMN 	T_CM_ENC_CASHOUT_STAT_DETAILS.ENCASHMENT_ID IS 'Internal Cash Management cash out replenishment identifier';
COMMENT ON COLUMN 	T_CM_ENC_CASHOUT_STAT_DETAILS.CASS_VALUE 	IS 'Denominations of bills in cassette';
COMMENT ON COLUMN 	T_CM_ENC_CASHOUT_STAT_DETAILS.CASS_CURR 	IS 'Cuurency of bills in cassette';
COMMENT ON COLUMN 	T_CM_ENC_CASHOUT_STAT_DETAILS.CASS_COUNT 	IS 'Count of bills in cassette';
COMMENT ON COLUMN 	T_CM_ENC_CASHOUT_STAT_DETAILS.ACTION_TYPE 	IS 'Action type (1-bills unload,2-bills upload)';
COMMENT ON COLUMN 	T_CM_ENC_CASHOUT_STAT_DETAILS.CASS_NUMBER 	IS 'Cassette number';

CREATE UNIQUE INDEX I_CM_ECOSTDET_EID2CN ON T_CM_ENC_CASHOUT_STAT_DETAILS (ENCASHMENT_ID, CASS_NUMBER, ACTION_TYPE);


