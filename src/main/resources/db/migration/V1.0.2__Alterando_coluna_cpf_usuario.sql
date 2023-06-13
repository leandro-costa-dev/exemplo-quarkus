ALTER TABLE public.usuario
ADD CONSTRAINT cpf_unico
UNIQUE (cpf)
