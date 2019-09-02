CREATE TABLE T_COMMENTS(
ID serial primary key,
CONTENT varchar(256) NOT NULL,
T_PRODUCTS_ID integer
);