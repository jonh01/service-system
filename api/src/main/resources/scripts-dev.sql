DROP SCHEMA IF EXISTS public cascade ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT USAGE ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO pg_database_owner;


DROP TABLE IF EXISTS tb_rating_image CASCADE;
DROP TABLE IF EXISTS tb_rating CASCADE;
DROP TABLE IF EXISTS tb_order CASCADE;
DROP TABLE IF EXISTS tb_reviews_note CASCADE;
DROP TABLE IF EXISTS tb_type_user CASCADE;
DROP TABLE IF EXISTS tb_service_provided_local_action CASCADE;
DROP TABLE IF EXISTS tb_service_provided_local_action CASCADE;
DROP TABLE IF EXISTS tb_user CASCADE;
DROP TABLE IF EXISTS tb_category CASCADE;

DROP TYPE IF EXISTS registeredUserType;
DROP TYPE IF EXISTS statusService;

CREATE TYPE registeredUserType AS ENUM  ('Client','Provider','Admin');
CREATE TYPE statusService AS ENUM  ('Active','Pending','Disabled');

CREATE TABLE tb_category
(
    
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    description text,
    name character varying(255),
    price double precision
);


CREATE TABLE tb_user
(
    
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at timestamp(6) without time zone,
    cpf character varying(255),
    email character varying(255),
    image character varying(255),
    name character varying(255),
    phone character varying(255),
    CONSTRAINT tb_user_cpf_key UNIQUE (cpf),
	CONSTRAINT tb_user_email_key UNIQUE (email)
);

CREATE TABLE tb_service_provided
(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	num_reviews INTEGER, 
	sum_reviews INTEGER,
    status statusService,
    created_at timestamp(6) without time zone,
    description text,
    image character varying(255),
    name character varying(255),
	fk_category_id uuid,
    fk_user_id uuid,
    CONSTRAINT fk_category_service FOREIGN KEY (fk_category_id) REFERENCES tb_category (id)
		ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT fk_user_service FOREIGN KEY (fk_user_id) REFERENCES tb_user (id) 
		ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE tb_service_provided_local_action
(
    tb_service_provided_id uuid NOT NULL,
    local_action character varying(255),
    CONSTRAINT fk_service_local FOREIGN KEY (tb_service_provided_id) REFERENCES tb_service_provided (id) 
		ON DELETE CASCADE
        ON UPDATE NO ACTION
);


CREATE TABLE tb_type_user
(

    tb_user_id uuid NOT NULL,
    type registeredUserType,
    CONSTRAINT fk_user_type FOREIGN KEY (tb_user_id) REFERENCES tb_user (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE tb_reviews_note
(
    note INTEGER NOT NULL,
    num_reviews INTEGER NOT NULL,
    fk_service_provided_id uuid NOT NULL,
    CONSTRAINT tb_reviews_note_pkey PRIMARY KEY (note, fk_service_provided_id),
    CONSTRAINT fk_service_reviews FOREIGN KEY (fk_service_provided_id) REFERENCES tb_service_provided (id) 
		ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE tb_order
(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    price double precision,
    created_at timestamp(6) without time zone,
    end_at timestamp(6) without time zone,
    start_at timestamp(6) without time zone,    
	description character varying(255),
    fk_service_provided_id uuid,
    fk_user_id uuid,
    CONSTRAINT fk_user_order FOREIGN KEY (fk_user_id) REFERENCES tb_user (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT fk_service_order FOREIGN KEY (fk_service_provided_id) REFERENCES tb_service_provided (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE tb_rating
(

    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    note integer,
    created_at timestamp(6) without time zone,
	comment text,
    fk_service_provided_id uuid,
    fk_user_id uuid,
    CONSTRAINT fk_service_rating FOREIGN KEY (fk_service_provided_id) REFERENCES tb_service_provided (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT fk_user_rating FOREIGN KEY (fk_user_id) REFERENCES tb_user (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE tb_rating_image
(
    tb_rating_id uuid NOT NULL,
    images character varying(255),
    CONSTRAINT fk_rating_image FOREIGN KEY (tb_rating_id) REFERENCES tb_rating (id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);



INSERT INTO tb_category (price, id, description, name) 
VALUES 
(10.99, '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Categoria de desenvolvimento de software', 'Desenvolvimento de Software'),
(15.50, '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Categoria de redes de computadores', 'Rede de Computadores'),
(20.75, '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Categoria de montagem e manutenção de notebooks e computadores', 'Montagem e Manutenção de Notebooks e Computadores'),
(12.25, '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Categoria de UX designer', 'UX Designer'),
(18.00, '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Categoria de DevOps', 'DevOps');


INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) 
VALUES 
('2024-01-01 10:00:00', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '123.456.789-01', 'user1@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User One', '111-111-1111'),
('2024-01-02 11:00:00', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '234.567.890-12', 'user2@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Two', '222-222-2222'),
('2024-01-03 12:00:00', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '345.678.901-23', 'user3@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Three', '333-333-3333'),
('2024-01-04 13:00:00', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '456.789.012-34', 'user4@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Four', '444-444-4444'),
('2024-01-05 14:00:00', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '567.890.123-45', 'user5@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Five', '555-555-5555'),
('2024-01-06 15:00:00', '902f5e7c-1045-4522-924d-4eb2c20b3105', '678.901.234-56', 'user6@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Six', '666-666-6666'),
('2024-01-07 16:00:00', 'c238de93-05e0-4e06-8347-3048c9692a23', '789.012.345-67', 'user7@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Seven', '777-777-7777'),
('2024-01-08 17:00:00', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed', '890.123.456-78', 'user8@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Eight', '888-888-8888'),
('2024-01-09 18:00:00', '1f1f79a2-b0cc-4798-9482-56be14051038', '901.234.567-89', 'user9@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Nine', '999-999-9999'),
('2024-01-10 19:00:00', 'ff754c40-f90c-444d-b00c-7cee28971573', '012.345.678-90', 'user0@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Ten', '000-000-0000');


INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) 
VALUES 
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 1, 5, 'Active', '2024-01-01 10:00:00', 'Desenvolvimento de aplicativos', 'https://example.com/service1.jpg', 'App Development', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 1, 4, 'Pending', '2024-01-02 11:00:00', 'Configuração de rede', 'https://example.com/service2.jpg', 'Network Configuration', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 1, 5, 'Disabled', '2024-01-03 12:00:00', 'Manutenção de computadores', 'https://example.com/service3.jpg', 'Computer Maintenance', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 1, 3, 'Active', '2024-01-04 13:00:00', 'Design de interfaces', 'https://example.com/service4.jpg', 'Interface Design', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 1, 2, 'Pending', '2024-01-05 14:00:00', 'Automação de DevOps', 'https://example.com/service5.jpg', 'DevOps Automation', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 1, 5, 'Active', '2024-01-06 15:00:00', 'Consultoria em TI', 'https://example.com/service6.jpg', 'IT Consulting', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '902f5e7c-1045-4522-924d-4eb2c20b3105'),
('c238de93-05e0-4e06-8347-3048c9692a23', 1, 1, 'Disabled', '2024-01-07 16:00:00', 'Desenvolvimento Web', 'https://example.com/service7.jpg', 'Web Development', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'c238de93-05e0-4e06-8347-3048c9692a23'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 1, 1, 'Active', '2024-01-08 17:00:00', 'Análise de dados', 'https://example.com/service8.jpg', 'Data Analysis', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 1, 5, 'Pending', '2024-01-09 18:00:00', 'Suporte técnico', 'https://example.com/service9.jpg', 'Technical Support', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '1f1f79a2-b0cc-4798-9482-56be14051038'),
('ff754c40-f90c-444d-b00c-7cee28971573', 1, 3, 'Active', '2024-01-10 19:00:00', 'Gestão de projetos', 'https://example.com/service10.jpg', 'Project Management', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action) 
VALUES 
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Campo Grande RJ'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Zona Oeste RJ'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Campo Grande RJ'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Zona Oeste RJ'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Campo Grande RJ'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 'Zona Oeste RJ'),
('c238de93-05e0-4e06-8347-3048c9692a23', 'Campo Grande RJ'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'Zona Oeste RJ'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 'Campo Grande RJ'),
('ff754c40-f90c-444d-b00c-7cee28971573', 'Zona Oeste RJ'),
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Botafogo RJ'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Botafogo RJ'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Botafogo RJ'),
('c238de93-05e0-4e06-8347-3048c9692a23', 'Botafogo RJ'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 'Botafogo RJ'),
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Inhaúma RJ'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Realengo RJ');


INSERT INTO tb_type_user (tb_user_id, type) 
VALUES 
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Provider'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Provider'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Provider'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Provider'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Provider'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 'Provider'),
('c238de93-05e0-4e06-8347-3048c9692a23', 'Provider'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'Provider'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 'Provider'),
('ff754c40-f90c-444d-b00c-7cee28971573', 'Provider'),
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Client'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Client'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Client'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Client'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Client'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 'Client'),
('c238de93-05e0-4e06-8347-3048c9692a23', 'Client'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'Client'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 'Client'),
('ff754c40-f90c-444d-b00c-7cee28971573', 'Client'),
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Admin');


INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) 
VALUES 
(1, 5, '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25'),
(1, 4, '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623'),
(1, 5, '3d7e1f12-9206-4814-b29a-4a9cfc3f199b'),
(1, 3, '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20'),
(1, 2, '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e'),
(1, 5, '902f5e7c-1045-4522-924d-4eb2c20b3105'),
(1, 1, 'c238de93-05e0-4e06-8347-3048c9692a23'),
(1, 1, 'f1199e50-f0fd-4cf9-9da7-dba71e164aed'),
(1, 5, '1f1f79a2-b0cc-4798-9482-56be14051038'),
(1, 3, 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) 
VALUES 
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 50.00, '2024-01-01 10:00:00', '2024-01-05 10:00:00', '2024-01-01 12:00:00', 'Ordem de serviço 1', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 75.00, '2024-01-02 11:00:00', '2024-01-06 11:00:00', '2024-01-02 13:00:00', 'Ordem de serviço 2', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 100.00, '2024-01-03 12:00:00', '2024-01-07 12:00:00', '2024-01-03 14:00:00', 'Ordem de serviço 3', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 60.00, '2024-01-04 13:00:00', '2024-01-08 13:00:00', '2024-01-04 15:00:00', 'Ordem de serviço 4', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 80.00, '2024-01-05 14:00:00', '2024-01-09 14:00:00', '2024-01-05 16:00:00', 'Ordem de serviço 5', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 70.00, '2024-01-06 15:00:00', '2024-01-10 15:00:00', '2024-01-06 17:00:00', 'Ordem de serviço 6', '902f5e7c-1045-4522-924d-4eb2c20b3105', '902f5e7c-1045-4522-924d-4eb2c20b3105'),
('c238de93-05e0-4e06-8347-3048c9692a23', 85.00, '2024-01-07 16:00:00', '2024-01-11 16:00:00', '2024-01-07 18:00:00', 'Ordem de serviço 7', 'c238de93-05e0-4e06-8347-3048c9692a23', 'c238de93-05e0-4e06-8347-3048c9692a23'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 90.00, '2024-01-08 17:00:00', '2024-01-12 17:00:00', '2024-01-08 19:00:00', 'Ordem de serviço 8', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 95.00, '2024-01-09 18:00:00', '2024-01-13 18:00:00', '2024-01-09 20:00:00', 'Ordem de serviço 9', '1f1f79a2-b0cc-4798-9482-56be14051038', '1f1f79a2-b0cc-4798-9482-56be14051038'),
('ff754c40-f90c-444d-b00c-7cee28971573', 55.00, '2024-01-10 19:00:00', '2024-01-14 19:00:00', '2024-01-10 21:00:00', 'Ordem de serviço 10', 'ff754c40-f90c-444d-b00c-7cee28971573', 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id) 
VALUES 
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 5, '2024-01-01 10:00:00', 'Excelente serviço!', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 4, '2024-01-02 11:00:00', 'Muito bom!', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 5, '2024-01-03 12:00:00', 'Bom, mas pode melhorar.', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 3, '2024-01-04 13:00:00', 'Serviço excelente!', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 2, '2024-01-05 14:00:00', 'Bom serviço.', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 5, '2024-01-06 15:00:00', 'Excelente!', '902f5e7c-1045-4522-924d-4eb2c20b3105', '902f5e7c-1045-4522-924d-4eb2c20b3105'),
('c238de93-05e0-4e06-8347-3048c9692a23', 1, '2024-01-07 16:00:00', 'Serviço mediano.', 'c238de93-05e0-4e06-8347-3048c9692a23', 'c238de93-05e0-4e06-8347-3048c9692a23'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 1, '2024-01-08 17:00:00', 'Bom atendimento.', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 5, '2024-01-09 18:00:00', 'Pode melhorar.', '1f1f79a2-b0cc-4798-9482-56be14051038', '1f1f79a2-b0cc-4798-9482-56be14051038'),
('ff754c40-f90c-444d-b00c-7cee28971573', 3, '2024-01-10 19:00:00', 'Ótimo serviço!', 'ff754c40-f90c-444d-b00c-7cee28971573', 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_rating_image (tb_rating_id, images) 
VALUES 
('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('902f5e7c-1045-4522-924d-4eb2c20b3105', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('c238de93-05e0-4e06-8347-3048c9692a23', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('1f1f79a2-b0cc-4798-9482-56be14051038', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg'),
('ff754c40-f90c-444d-b00c-7cee28971573', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
