drop table if exists tb_category cascade;
drop table if exists tb_order cascade;
drop table if exists tb_rating cascade;
drop table if exists tb_rating_image cascade;
drop table if exists tb_service_provided cascade;
drop table if exists tb_service_provided_local_action cascade;
drop table if exists tb_type_user cascade;
drop table if exists tb_user cascade;

create table tb_category (price float(53), id uuid not null, description TEXT, name varchar(255), primary key (id));

create table tb_order (price float(53), created_at timestamp(6), end_at timestamp(6), start_at timestamp(6), id uuid not null, service_provided_id uuid,user_id uuid, description varchar(255), primary key (id));

create table tb_rating (note integer, created_at timestamp(6), id uuid not null, service_provided_id uuid, user_id uuid, comment TEXT, primary key (id));

create table tb_rating_image (tb_rating_id uuid not null, images varchar(255));

create table tb_service_provided (status tinyint check (status between 0 and 2), created_at timestamp(6), category_id uuid, id uuid not null, user_id uuid, description TEXT, image varchar(255), name varchar(255), primary key (id));

create table tb_service_provided_local_action (tb_service_provided_id uuid not null, local_action varchar(255));

create table tb_type_user (type tinyint check (type between 0 and 1), tb_user_id uuid not null);

create table tb_user (created_at timestamp(6), id uuid not null, cpf varchar(255), email varchar(255) unique, image varchar(255), name varchar(255), phone varchar(255), primary key (id));




INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone)  VALUES  ('2024-05-14 08:00:00', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '123.456.789-00', 'usuario1@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'Usuário 1', '(00) 1234-5678');

INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone)  VALUES  ('2024-05-14 13:00:00', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '987.654.321-00', 'usuario2@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'Usuário 2', '(00) 9876-5432');

INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone)  VALUES  ('2024-05-15 07:00:00', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '111.222.333-44', 'usuario3@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'Usuário 3', '(00) 1111-2222');

INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone)  VALUES  ('2024-05-15 12:00:00', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '555.666.777-88', 'usuario4@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'Usuário 4', '(00) 5555-6666');

INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone)  VALUES  ('2024-05-16 08:00:00', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '999.888.777-66', 'usuario5@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'Usuário 5', '(00) 9999-8888');




INSERT INTO tb_type_user (type, tb_user_id) VALUES (0, '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25');

INSERT INTO tb_type_user (type, tb_user_id) VALUES (1, '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623');

INSERT INTO tb_type_user (type, tb_user_id) VALUES (0, '3d7e1f12-9206-4814-b29a-4a9cfc3f199b');

INSERT INTO tb_type_user (type, tb_user_id) VALUES (1, '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20');

INSERT INTO tb_type_user (type, tb_user_id) VALUES (0, '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e');



INSERT INTO tb_category (price, id, description, name)  VALUES  (10.99, '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Categoria de desenvolvimento de software', 'Desenvolvimento de Software');

INSERT INTO tb_category (price, id, description, name)  VALUES  (15.50, '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Categoria de redes de computadores', 'Rede de Computadores');

INSERT INTO tb_category (price, id, description, name)  VALUES  (20.75, '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Categoria de montagem e manutenção de notebooks e computadores', 'Montagem e Manutenção de Notebooks e Computadores');

INSERT INTO tb_category (price, id, description, name)  VALUES  (12.25, '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Categoria de UX designer', 'UX Designer');

INSERT INTO tb_category (price, id, description, name)  VALUES  (18.00, '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Categoria de DevOps ', 'DevOps');


INSERT INTO tb_service_provided (status, created_at, category_id, id, user_id, description, image, name)  VALUES  (1, '2024-05-14 08:00:00', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Desenvolvimento de aplicativos móveis', 'https://www.cobli.co/wp-content/uploads/2022/01/contrato-prestacao-servicos-scaled-1140x490.jpeg', 'Desenvolvimento de Aplicativos Móveis');

INSERT INTO tb_service_provided (status, created_at, category_id, id, user_id, description, image, name)  VALUES  (1, '2024-05-14 13:00:00', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Configuração de rede doméstica', 'https://www.cobli.co/wp-content/uploads/2022/01/contrato-prestacao-servicos-scaled-1140x490.jpeg', 'Configuração de Rede Doméstica');

INSERT INTO tb_service_provided (status, created_at, category_id, id, user_id, description, image, name)  VALUES  (1, '2024-05-15 07:00:00', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Manutenção preventiva de notebooks', 'https://www.cobli.co/wp-content/uploads/2022/01/contrato-prestacao-servicos-scaled-1140x490.jpeg', 'Manutenção Preventiva de Notebooks');

INSERT INTO tb_service_provided (status, created_at, category_id, id, user_id, description, image, name)  VALUES  (1, '2024-05-15 12:00:00', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Design de interface de usuário', 'https://www.cobli.co/wp-content/uploads/2022/01/contrato-prestacao-servicos-scaled-1140x490.jpeg', 'Design de Interface de Usuário');

INSERT INTO tb_service_provided (status, created_at, category_id, id, user_id, description, image, name)  VALUES  (1, '2024-05-16 08:00:00', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Implantação de ferramentas de automação', 'https://www.cobli.co/wp-content/uploads/2022/01/contrato-prestacao-servicos-scaled-1140x490.jpeg', 'Implantação de Ferramentas de Automação');




INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES  ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'RJ');

INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES  ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Campo Grande RJ');

INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES  ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Centro RJ');

INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES  ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Botafogo RJ');

INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES  ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Zona Oeste RJ');



INSERT INTO tb_order (price, created_at, end_at, start_at, id, service_provided_id, user_id, description)  VALUES  (50.00, '2024-05-14 10:00:00', '2024-05-14 14:00:00', '2024-05-14 09:00:00', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Desenvolvimento de aplicativos móveis para Android e iOS');

INSERT INTO tb_order (price, created_at, end_at, start_at, id, service_provided_id, user_id, description)  VALUES  (80.00, '2024-05-15 13:00:00', '2024-05-15 17:00:00', '2024-05-15 12:00:00', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Configuração de rede doméstica e instalação de roteador');

INSERT INTO tb_order (price, created_at, end_at, start_at, id, service_provided_id, user_id, description)  VALUES  (120.00, '2024-05-16 08:00:00', '2024-05-16 12:00:00', '2024-05-16 07:00:00', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Manutenção preventiva de notebooks e diagnóstico de falha no sistema operacional');

INSERT INTO tb_order (price, created_at, end_at, start_at, id, service_provided_id, user_id, description)  VALUES  (60.00, '2024-05-17 11:00:00', '2024-05-17 15:00:00', '2024-05-17 10:00:00', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Design de interface de usuário para aplicativo móvel');

INSERT INTO tb_order (price, created_at, end_at, start_at, id, service_provided_id, user_id, description)  VALUES  (90.00, '2024-05-18 09:00:00', '2024-05-18 13:00:00', '2024-05-18 08:00:00', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Implantação de ferramentas de automação e configuração de pipeline de CI/CD');




INSERT INTO tb_rating (note, created_at, id, service_provided_id, user_id, comment)  VALUES  (4, '2024-05-14 15:00:00', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'O desenvolvimento foi ótimo, mas houve alguns atrasos no cronograma.');

INSERT INTO tb_rating (note, created_at, id, service_provided_id, user_id, comment)  VALUES  (5, '2024-05-16 14:00:00', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'O técnico foi muito profissional e resolveu o problema rapidamente.');

INSERT INTO tb_rating (note, created_at, id, service_provided_id, user_id, comment)  VALUES  (3, '2024-05-17 16:00:00', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'A manutenção foi realizada, mas algumas questões não foram completamente resolvidas.');

INSERT INTO tb_rating (note, created_at, id, service_provided_id, user_id, comment)  VALUES  (4, '2024-05-18 14:00:00', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'O design ficou muito bonito, mas houve algumas discrepâncias em relação ao que foi pedido.');

INSERT INTO tb_rating (note, created_at, id, service_provided_id, user_id, comment)  VALUES  (5, '2024-05-19 12:00:00', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'A implantação foi feita com sucesso e todas as etapas foram explicadas de forma clara.');




INSERT INTO tb_rating_image (tb_rating_id, images) VALUES  ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZ');

INSERT INTO tb_rating_image (tb_rating_id, images) VALUES  ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');

INSERT INTO tb_rating_image (tb_rating_id, images) VALUES  ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');

INSERT INTO tb_rating_image (tb_rating_id, images) VALUES  ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');

INSERT INTO tb_rating_image (tb_rating_id, images) VALUES  ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');





alter table if exists tb_order add constraint FKdi3i5ixn9r8u9jex0dc7aetho foreign key (service_provided_id) references tb_service_provided;
alter table if exists tb_order add constraint FK2p4n9ciui39792tk5qdpcxq1w foreign key (user_id) references tb_user;
alter table if exists tb_rating add constraint FK7q4bgb45menbry9cbfvcs89yr foreign key (user_id) references tb_user;
alter table if exists tb_rating add constraint FK2envgctjw09fmmn203xrv7sxa foreign key (service_provided_id) references tb_service_provided;
alter table if exists tb_rating_image add constraint FK9opypckfbf32cc1gahlbo5655 foreign key (tb_rating_id) references tb_rating;
alter table if exists tb_service_provided add constraint FKmsd7x2btttks2i313soj8rylp foreign key (category_id) references tb_category;
alter table if exists tb_service_provided add constraint FKr9bw9ldlvkmd6y5fpoawh2o4k foreign key (user_id) references tb_user;
alter table if exists tb_service_provided_local_action add constraint FKl1p9a34foti7h36whdmfi2pxg foreign key (tb_service_provided_id) references tb_service_provided;
alter table if exists tb_type_user add constraint FK18cro9gk7cr4kncn1o28eilfd foreign key (tb_user_id) references tb_user;