CREATE TABLE public.usr (
	id int8 NOT NULL,
	age int4 NULL,
	email varchar NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT usr_pkey PRIMARY KEY (id)
);


