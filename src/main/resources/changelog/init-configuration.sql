-- liquibase formatted sql

-- changeset herberttcarneiro:1
INSERT INTO `test`.`contact_type` (`id`, `name`)
VALUES ('1', 'Residencial');
INSERT INTO `test`.`contact_type` (`id`, `name`)
VALUES ('2', 'Comercial');
INSERT INTO `test`.`contact_type` (`id`, `name`)
VALUES ('3', 'Celular');
INSERT INTO `test`.`application_user` (`authorities`, `name`, `password`, `username`)
VALUES ('ROLE_ADMIN,ROLE_USER',
        'Herbertt Teixeira',
        '{bcrypt}$2a$10$aNJNrpANtm9LojXeKygZRe80LkmE/d0RuTWqkZHoW3X8.8f1lLfo6',
        'admin');
INSERT INTO `test`.`application_user` (`authorities`, `name`, `password`, `username`)
VALUES ('ROLE_ADMIN,ROLE_USER',
        'Pedro Silva',
        '{bcrypt}$2a$10$aNJNrpANtm9LojXeKygZRe80LkmE/d0RuTWqkZHoW3X8.8f1lLfo6',
        'comum');

-- rollback TRUNCATE `test`.`contact_type`/

