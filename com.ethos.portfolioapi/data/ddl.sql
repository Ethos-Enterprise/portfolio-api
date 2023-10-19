CREATE TABLE portfolio (
    id uuid NOT NULL,
    url_imagem_perfil varchar(500) NULL,
    url_background_perfil varchar(300) NULL,
    descricao_empresa varchar(300) NULL,
    sobre_empresa varchar(1000) NULL,
    link_website_empresa varchar(300) NULL,
    data_empresa_certificada date NULL,
    fk_prestadora_servico uuid NOT NULL,
    CONSTRAINT portfolio_pkey PRIMARY KEY (id)
);