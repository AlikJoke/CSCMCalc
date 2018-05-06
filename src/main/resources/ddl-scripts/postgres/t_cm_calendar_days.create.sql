CREATE TABLE T_CM_CALENDAR_DAYS (
  	CL_ID             	NUMERIC(9) 	NOT NULL,
  	CL_DATE           	TIMESTAMP        NOT NULL,
  	CL_DAY_TYPE       	NUMERIC(2),
  	CL_DAY_ENC_AVAIL  	NUMERIC(1)   DEFAULT 1
);

COMMENT ON TABLE 	T_CM_CALENDAR_DAYS 					IS 'This table contains dates for calendar';
COMMENT ON COLUMN 	T_CM_CALENDAR_DAYS.CL_ID 			IS 'Unique ID identifying the calendar';
COMMENT ON COLUMN 	T_CM_CALENDAR_DAYS.CL_DATE 			IS 'Date';
COMMENT ON COLUMN 	T_CM_CALENDAR_DAYS.CL_DAY_TYPE 		IS 'Type of date (by demand)';
COMMENT ON COLUMN 	T_CM_CALENDAR_DAYS.CL_DAY_ENC_AVAIL IS 'Flag that indicates whether encashment is available on this date';

CREATE UNIQUE INDEX I_CM_CALENDAR_DAYS_CID2CDATE ON T_CM_CALENDAR_DAYS (CL_ID, CL_DATE);
