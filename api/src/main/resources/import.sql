INSERT INTO tb_category (price, id, description, name) VALUES (10.99, '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Categoria de desenvolvimento de software', 'Desenvolvimento de Software');
INSERT INTO tb_category (price, id, description, name) VALUES (15.50, '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Categoria de redes de computadores', 'Rede de Computadores');
INSERT INTO tb_category (price, id, description, name) VALUES (20.75, '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Categoria de montagem e manutenção de notebooks e computadores', 'Montagem e Manutenção de Notebooks e Computadores');
INSERT INTO tb_category (price, id, description, name) VALUES (12.25, '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Categoria de UX designer', 'UX Designer');
INSERT INTO tb_category (price, id, description, name) VALUES (18.00, '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Categoria de DevOps', 'DevOps');


INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-01 10:00:00', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '123.456.789-01', 'user1@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User One', '111-111-1111');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-02 11:00:00', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '234.567.890-12', 'user2@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Two', '222-222-2222');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-03 12:00:00', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '345.678.901-23', 'user3@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Three', '333-333-3333');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-04 13:00:00', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '456.789.012-34', 'user4@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Four', '444-444-4444');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-05 14:00:00', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '567.890.123-45', 'user5@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Five', '555-555-5555');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-06 15:00:00', '902f5e7c-1045-4522-924d-4eb2c20b3105', '678.901.234-56', 'user6@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Six', '666-666-6666');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-07 16:00:00', 'c238de93-05e0-4e06-8347-3048c9692a23', '789.012.345-67', 'user7@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Seven', '777-777-7777');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-08 17:00:00', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed', '890.123.456-78', 'user8@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Eight', '888-888-8888');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-09 18:00:00', '1f1f79a2-b0cc-4798-9482-56be14051038', '901.234.567-89', 'user9@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Nine', '999-999-9999');
INSERT INTO tb_user (created_at, id, cpf, email, image, name, phone) VALUES ('2024-01-10 19:00:00', 'ff754c40-f90c-444d-b00c-7cee28971573', '012.345.678-90', 'user0@example.com', 'https://cdn-icons-png.flaticon.com/512/17/17004.png', 'User Ten', '000-000-0000');


INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 1, 5, 'Active', '2024-01-01 10:00:00', 'Desenvolvimento de aplicativos', 'https://example.com/service1.jpg', 'App Development', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 1, 4, 'Pending', '2024-01-02 11:00:00', 'Configuração de rede', 'https://example.com/service2.jpg', 'Network Configuration', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 1, 5, 'Disabled', '2024-01-03 12:00:00', 'Manutenção de computadores', 'https://example.com/service3.jpg', 'Computer Maintenance', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 1, 3, 'Active', '2024-01-04 13:00:00', 'Design de interfaces', 'https://example.com/service4.jpg', 'Interface Design', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 1, 2, 'Pending', '2024-01-05 14:00:00', 'Automação de DevOps', 'https://example.com/service5.jpg', 'DevOps Automation', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 1, 5, 'Active', '2024-01-06 15:00:00', 'Consultoria em TI', 'https://example.com/service6.jpg', 'IT Consulting', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '902f5e7c-1045-4522-924d-4eb2c20b3105');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 1, 1, 'Disabled', '2024-01-07 16:00:00', 'Desenvolvimento Web', 'https://example.com/service7.jpg', 'Web Development', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'c238de93-05e0-4e06-8347-3048c9692a23');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 1, 1, 'Active', '2024-01-08 17:00:00', 'Análise de dados', 'https://example.com/service8.jpg', 'Data Analysis', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 1, 5, 'Pending', '2024-01-09 18:00:00', 'Suporte técnico', 'https://example.com/service9.jpg', 'Technical Support', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '1f1f79a2-b0cc-4798-9482-56be14051038');
INSERT INTO tb_service_provided (id, num_reviews, sum_reviews, status, created_at, description, image, name, fk_category_id, fk_user_id) VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 1, 3, 'Active', '2024-01-10 19:00:00', 'Gestão de projetos', 'https://example.com/service10.jpg', 'Project Management', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Campo Grande RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Zona Oeste RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Campo Grande RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Zona Oeste RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Campo Grande RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 'Zona Oeste RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 'Campo Grande RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'Zona Oeste RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 'Campo Grande RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 'Zona Oeste RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Botafogo RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Botafogo RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Botafogo RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 'Botafogo RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 'Botafogo RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Inhaúma RJ');
INSERT INTO tb_service_provided_local_action (tb_service_provided_id, local_action)  VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Realengo RJ');


INSERT INTO tb_type_user (tb_user_id, type) VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 'Provider');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 'Client');
INSERT INTO tb_type_user (tb_user_id, type) VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'Admin');


INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 5, '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 4, '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 5, '3d7e1f12-9206-4814-b29a-4a9cfc3f199b');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 3, '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 2, '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 5, '902f5e7c-1045-4522-924d-4eb2c20b3105');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 1, 'c238de93-05e0-4e06-8347-3048c9692a23');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 1, 'f1199e50-f0fd-4cf9-9da7-dba71e164aed');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 5, '1f1f79a2-b0cc-4798-9482-56be14051038');
INSERT INTO tb_reviews_note (num_reviews, note, fk_service_provided_id) VALUES (1, 3, 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 50.00, '2024-01-01 10:00:00', '2024-01-05 10:00:00', '2024-01-01 12:00:00', 'Ordem de serviço 1', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 75.00, '2024-01-02 11:00:00', '2024-01-06 11:00:00', '2024-01-02 13:00:00', 'Ordem de serviço 2', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 100.00, '2024-01-03 12:00:00', '2024-01-07 12:00:00', '2024-01-03 14:00:00', 'Ordem de serviço 3', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 60.00, '2024-01-04 13:00:00', '2024-01-08 13:00:00', '2024-01-04 15:00:00', 'Ordem de serviço 4', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 80.00, '2024-01-05 14:00:00', '2024-01-09 14:00:00', '2024-01-05 16:00:00', 'Ordem de serviço 5', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 70.00, '2024-01-06 15:00:00', '2024-01-10 15:00:00', '2024-01-06 17:00:00', 'Ordem de serviço 6', '902f5e7c-1045-4522-924d-4eb2c20b3105', '902f5e7c-1045-4522-924d-4eb2c20b3105');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 85.00, '2024-01-07 16:00:00', '2024-01-11 16:00:00', '2024-01-07 18:00:00', 'Ordem de serviço 7', 'c238de93-05e0-4e06-8347-3048c9692a23', 'c238de93-05e0-4e06-8347-3048c9692a23');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 90.00, '2024-01-08 17:00:00', '2024-01-12 17:00:00', '2024-01-08 19:00:00', 'Ordem de serviço 8', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 95.00, '2024-01-09 18:00:00', '2024-01-13 18:00:00', '2024-01-09 20:00:00', 'Ordem de serviço 9', '1f1f79a2-b0cc-4798-9482-56be14051038', '1f1f79a2-b0cc-4798-9482-56be14051038');
INSERT INTO tb_order (id, price, created_at, end_at, start_at, description, fk_service_provided_id, fk_user_id) VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 55.00, '2024-01-10 19:00:00', '2024-01-14 19:00:00', '2024-01-10 21:00:00', 'Ordem de serviço 10', 'ff754c40-f90c-444d-b00c-7cee28971573', 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 5, '2024-01-01 10:00:00', 'Excelente serviço!', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', '1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 4, '2024-01-02 11:00:00', 'Muito bom!', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', '2c9f3e8a-fb9e-4a8e-af77-3d0411b08623');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 5, '2024-01-03 12:00:00', 'Bom, mas pode melhorar.', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b', '3d7e1f12-9206-4814-b29a-4a9cfc3f199b');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 3, '2024-01-04 13:00:00', 'Serviço excelente!', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', '4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 2, '2024-01-05 14:00:00', 'Bom serviço.', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', '5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 5, '2024-01-06 15:00:00', 'Excelente!', '902f5e7c-1045-4522-924d-4eb2c20b3105', '902f5e7c-1045-4522-924d-4eb2c20b3105');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 1, '2024-01-07 16:00:00', 'Serviço mediano.', 'c238de93-05e0-4e06-8347-3048c9692a23', 'c238de93-05e0-4e06-8347-3048c9692a23');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 1, '2024-01-08 17:00:00', 'Bom atendimento.', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'f1199e50-f0fd-4cf9-9da7-dba71e164aed');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 5, '2024-01-09 18:00:00', 'Pode melhorar.', '1f1f79a2-b0cc-4798-9482-56be14051038', '1f1f79a2-b0cc-4798-9482-56be14051038');
INSERT INTO tb_rating (id, note, created_at, comment, fk_service_provided_id, fk_user_id)  VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 3, '2024-01-10 19:00:00', 'Ótimo serviço!', 'ff754c40-f90c-444d-b00c-7cee28971573', 'ff754c40-f90c-444d-b00c-7cee28971573');


INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('1a8b64b7-6e4c-4c62-9b79-5e94ae1b8e25', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('2c9f3e8a-fb9e-4a8e-af77-3d0411b08623', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('3d7e1f12-9206-4814-b29a-4a9cfc3f199b', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('4e6b3a79-f9e1-4c8d-8f0a-89ef42653b20', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('5f5d4b30-c8e5-4e7a-b646-ff2b2de8ef2e', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('902f5e7c-1045-4522-924d-4eb2c20b3105', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('c238de93-05e0-4e06-8347-3048c9692a23', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('f1199e50-f0fd-4cf9-9da7-dba71e164aed', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('1f1f79a2-b0cc-4798-9482-56be14051038', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');
INSERT INTO tb_rating_image (tb_rating_id, images) VALUES ('ff754c40-f90c-444d-b00c-7cee28971573', 'https://lh3.googleusercontent.com/EDnobTG2hce6p03gozFnrB9JkQy8eEjxHcXyCXAvrdoNK29n2E1baGT5taUBBdfYvXXzfkKIGmti0fGP7oJ8FgiMIwjGZO43CfGEehK21lq7yqZkLBg');


-- Importe esta trigger diretamente no banco h2
--  CREATE TRIGGER update_reviews_after_insert AFTER INSERT ON tb_rating FOR EACH ROW CALL 'com.servicesystem.api.config.triggers.UpdateReviewsAfterInsert';