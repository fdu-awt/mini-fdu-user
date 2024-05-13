use mini_fdu;

INSERT INTO `user` (`name`, `email`, `password`, `self_image`)
VALUES ('Violette', 'violette@example.com', 'violette123', '花时来信_神里绫华'),
       ('Rosy', 'rosy@example.com', 'rosy123', '多莉'),
       ('ZMark', 'zmark@example.com', 'zmark123', '那维莱特'),
       ('chenbuchen', 'chenbuchen@example.com', 'chenbuchen123', '荒泷一斗');

INSERT INTO `history_message` (`local_id`, `remote_id`, `content`, `time_stamp`)
VALUES (1, 2, 'Hello !', '2024-05-01 12:00:00'),
       (2, 1, 'Hi!', '2024-05-01 12:05:00'),
       (1, 2, 'How are you today?', '2024-05-01 12:10:00');

INSERT INTO `study_question` (`id`, `topic`, `data_id`, `question`, `options`, `answer`)
VALUES (1, 'history', 1, '复旦公学是哪一年在上海吴淞正式开学的？', 'A. 1902年, B. 1905年, C. 1911年, D. 1908年', 'B'),
       (2, 'history', 1, '复旦公学的创办人马相伯最初是在哪里借屋为校舍的？',
        'A. 徐家汇天文台, B. 吴淞提督行辕, C. 震旦学院, D. 天主教堂', 'A');