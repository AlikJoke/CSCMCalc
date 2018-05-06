CREATE TABLE t_cm_atm_cassettes
    (atm_id                        NUMERIC(9),
    cass_type                      NUMERIC(1),
    cass_number                    NUMERIC(1),
    cass_curr                      NUMERIC(3),
    cass_value                     NUMERIC(6),
    cass_state                     NUMERIC(1),
    cass_is_present                INTEGER DEFAULT 1
 );
 
CREATE UNIQUE INDEX I_CM_ACAS_AID2CT2CN ON T_CM_ATM_CASSETTES (
    atm_id                          ASC,
    CASS_TYPE,
    CASS_NUMBER
);
 
COMMENT ON TABLE T_CM_ATM_CASSETTES IS 'This table contains defualt cash demand coefficient multipliers for ATM currencies';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.ATM_ID IS 'Unique ID identifying ATM';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.CASS_CURR IS 'Currency code for cassette';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.CASS_IS_PRESENT IS 'Flag indicating whether cassette is in ATM now (0 - cassette is not present)';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.CASS_NUMBER IS 'Cassette number';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.CASS_STATE IS 'Flag indicating whether cassette is working or not (1- cassette is not working)';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.CASS_TYPE IS 'Cassette type - cash-in, cash-out, recycling';
COMMENT ON COLUMN T_CM_ATM_CASSETTES.CASS_VALUE IS 'Denominations value for cassette';
 
