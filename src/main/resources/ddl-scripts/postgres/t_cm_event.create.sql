CREATE TABLE T_CM_EVENT
(
  ID             NUMERIC(4),
  NAME           VARCHAR(50),
  DESCRIPTION    VARCHAR(100),
  EVENT_TYPE_ID  NUMERIC(3),
  FORECAST_TYPE  NUMERIC(1),
  DATE_FROM      TIMESTAMP,
  DATE_TO        TIMESTAMP
);
