package ru.project.cscm.calc.base;

final class PreparedQueries {

	private PreparedQueries() {
		super();
	}

	protected static final String INSERT_TRANS_QUERY = "INSERT INTO t_cm_intgr_trans "
			+ "(ATM_ID, UTRNNO, DATETIME, TRANS_TYPE_IND, BILL_RETRACT, BILL_REJECT, "
			+ "BILL_CASS1, DENOM_CASS1, CURRENCY_CASS1, TYPE_CASS1, "
			+ "BILL_CASS2, DENOM_CASS2, CURRENCY_CASS2, TYPE_CASS2, "
			+ "BILL_CASS3, DENOM_CASS3, CURRENCY_CASS3, TYPE_CASS3, "
			+ "BILL_CASS4, DENOM_CASS4, CURRENCY_CASS4, TYPE_CASS4, "
			+ "BILL_CASS5, DENOM_CASS5, CURRENCY_CASS5, TYPE_CASS5, "
			+ "BILL_CASS6, DENOM_CASS6, CURRENCY_CASS6, TYPE_CASS6, "
			+ "BILL_CASS7, DENOM_CASS7, CURRENCY_CASS7, TYPE_CASS7, "
			+ "BILL_CASS8, DENOM_CASS8, CURRENCY_CASS8, TYPE_CASS8) "
			+ "select ATM_ID, ?, ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, "
			+ " ?, ?, ?, ?, " 
			+ " ?, ?, ?, ? "
			+ " from T_CM_ATM "
			+ "where EXTERNAL_ATM_ID = ?";

}
