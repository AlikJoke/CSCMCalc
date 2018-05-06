CREATE OR REPLACE VIEW V_CM_CASHIN_R_DENOM_STAT (
   ATM_ID,
   STAT_DATE,
   CASH_IN_ENCASHMENT_ID,
   DENOM_VALUE,
   DENOM_CURR,
   DENOM_COUNT_IN,
   DENOM_COUNT_OUT,
   DENOM_TRANS_COUNT_IN,
   DENOM_TRANS_COUNT_OUT,
   DENOM_REMAINING )
AS
(
	SELECT 
		ATM_ID,
		STAT_DATE,
		CASH_IN_ENCASHMENT_ID,
		CASS_VALUE as DENOM_VALUE,
		CASS_CURR as DENOM_CURR,
		sum(CASS_COUNT_IN) as DENOM_COUNT_IN,
		sum(CASS_COUNT_OUT) as DENOM_COUNT_OUT,
		sum(CASS_TRANS_COUNT_IN) as DENOM_TRANS_COUNT_IN,
		sum(CASS_TRANS_COUNT_OUT) as DENOM_TRANS_COUNT_OUT,
		sum(CASS_REMAINING) as DENOM_REMAINING
	FROM 
		t_cm_cashin_r_cass_stat
	GROUP BY
		ATM_ID,
		STAT_DATE,
		CASH_IN_ENCASHMENT_ID,
		CASS_VALUE,
		CASS_CURR
);

COMMENT ON VIEW V_CM_CASHIN_R_DENOM_STAT IS 'This table contains statistics periods for cash out denominations.';
COMMENT ON COLUMN V_CM_CASHIN_R_DENOM_STAT.ATM_ID IS 'Unique ID identifying the ATM';
COMMENT ON COLUMN V_CM_CASHIN_R_DENOM_STAT.DENOM_CURR IS 'Denomination currency';
COMMENT ON COLUMN V_CM_CASHIN_R_DENOM_STAT.DENOM_REMAINING IS 'Count of bills remaining in cassette in the end of period';
COMMENT ON COLUMN V_CM_CASHIN_R_DENOM_STAT.DENOM_VALUE IS 'Denomination';
COMMENT ON COLUMN V_CM_CASHIN_R_DENOM_STAT.STAT_DATE IS 'Beginning of statistics period';