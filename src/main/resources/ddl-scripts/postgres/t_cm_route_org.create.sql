CREATE TABLE T_CM_ROUTE_ORG
(
  ID           NUMERIC(10),
  NAME         VARCHAR(40),
  DESCRIPTION  VARCHAR(200),
  INST_ID      VARCHAR(4),
  DEPOT_ID     NUMERIC(8)
);

COMMENT ON TABLE T_CM_ROUTE_ORG IS 'This table contains information about routing Organizations';

COMMENT ON COLUMN T_CM_ROUTE_ORG.ID IS 'Unique ID identifying Organization';

COMMENT ON COLUMN T_CM_ROUTE_ORG.NAME IS 'Organization Name';

COMMENT ON COLUMN T_CM_ROUTE_ORG.DESCRIPTION IS 'Description of Organization';

COMMENT ON COLUMN T_CM_ROUTE_ORG.INST_ID IS 'Organization Institute';

COMMENT ON COLUMN T_CM_ROUTE_ORG.DEPOT_ID IS 'Depot id of Organization';



ALTER TABLE T_CM_ROUTE_ORG ADD
  CONSTRAINT T_CM_ROUTE_ORG_PK
  PRIMARY KEY
  (ID);