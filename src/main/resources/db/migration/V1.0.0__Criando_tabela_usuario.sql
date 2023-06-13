CREATE TABLE public.usuario (
   id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
   nome varchar(100) NOT NULL,
   idade int4 NOT NULL,
   cpf varchar(11),
   data_cadastro date,
   CONSTRAINT usuario_pkey PRIMARY KEY (id)
);
