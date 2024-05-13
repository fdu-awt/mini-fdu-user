use mini_fdu;

INSERT INTO `user` (`name`, `email`, `password`,`self_image`) VALUES
    ('Violette', 'violette@example.com', 'violette123','花时来信_神里绫华');

INSERT INTO `user` (`name`, `email`, `password`,`self_image`) VALUES
    ('Rosy', 'rosy@example.com', 'rosy123','多莉');

INSERT INTO `user` (`name`, `email`, `password`,`self_image`) VALUES
    ('ZMark', 'zmark@example.com', 'zmark123','那维莱特');

INSERT INTO `user` (`name`, `email`, `password`,`self_image`) VALUES
    ('chenbuchen', 'chenbuchen@example.com', 'chenbuchen123','荒泷一斗');

INSERT INTO `history_message` (`local_id`, `remote_id`, `content`, `time_stamp`)
VALUES (1, 2, 'Hello !', '2024-05-01 12:00:00');

INSERT INTO `history_message` (`local_id`, `remote_id`, `content`, `time_stamp`)
VALUES (2, 1, 'Hi!', '2024-05-01 12:05:00');

INSERT INTO `history_message` (`local_id`, `remote_id`, `content`, `time_stamp`)
VALUES (1, 2, 'How are you today?', '2024-05-01 12:10:00');