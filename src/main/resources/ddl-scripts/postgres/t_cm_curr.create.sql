CREATE TABLE T_CM_CURR(
  	CODE_N3   	NUMERIC(3)          	NOT NULL,
  	CODE_A3   	VARCHAR(3)   	NOT NULL,
  	CURRENCY  	VARCHAR(32),
  	EXP       	NUMERIC(1)
);

COMMENT ON TABLE 	T_CM_CURR 			IS 'This table contains currency codes';
COMMENT ON COLUMN 	T_CM_CURR.CODE_N3 	IS 'Numeric currency code';
COMMENT ON COLUMN 	T_CM_CURR.CODE_A3 	IS 'The 3 letter abbreviation for the currency';
COMMENT ON COLUMN 	T_CM_CURR.CURRENCY 	IS 'Currency';
COMMENT ON COLUMN 	T_CM_CURR.EXP 		IS 'Currency exponent (i.e. 2 for US dollars or russian rubbles)';

CREATE UNIQUE INDEX I_CM_CURR_CN3 ON T_CM_CURR (CODE_N3);

ALTER TABLE T_CM_CURR ADD 
  	CONSTRAINT PK_CM_CURR PRIMARY KEY USING INDEX I_CM_CURR_CN3;
