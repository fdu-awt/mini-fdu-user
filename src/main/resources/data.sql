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

INSERT INTO `quiz` (`id`, `topic`, `data_id`, `question`, `options`, `answer`)
VALUES (1, 'club', 1, '尤克里里社的slogan是什么？',
        '音乐的翅膀，携带梦想飞翔|披音乐星光，传弦歌魅力|弹奏快乐，传递音符|弦上跳动的不仅是音符，还有梦想',
        '披音乐星光，传弦歌魅力'),
       (2, 'club', 2, '钢琴协会的slogan是什么？',
        '以琴会友，以声寻梦，以乐传承|黑白键上，奏响友谊之歌|钢琴之声，心灵之交|音乐的桥梁，连接你我',
        '以琴会友，以声寻梦，以乐传承'),
       (3, 'club', 3, '国标舞协的slogan是什么？',
        '舞出青春，舞出自我|舞步中的诗，生命中的歌|To dance, to live, to be.|以舞会友，以舞传情',
        'To dance, to live, to be.'),
       (4, 'club', 4, '演讲与口才协会的slogan是什么？',
        '言为心声，语为桥梁|用声音传递力量，用话语触动心灵|演讲的舞台，思想的火花|宁鸣而死，不默而生', '宁鸣而死，不默而生'),
       (5, 'club', 5, '天文协会的slogan是什么？',
        '星辰大海，探索无限|漫天星辰愿作你最深沉的寄托|天文之美，科学之光|仰望星空，脚踏实地',
        '漫天星辰愿作你最深沉的寄托'),
       (6, 'club', 6, '复旦心脑学社的slogan是什么？',
        'Discover the mystery in your mind.|探索大脑，启迪智慧|心脑科学，探索未来|智慧的源泉，思想的火花',
        'Discover the mystery in your mind.'),
       (7, 'club', 7, '智能机器人创新社的slogan是什么？',
        '智能科技，创新未来|机器人，现实与梦想的桥梁|科技与浪漫的结合|机器人是科幻致于现实的浪漫',
        '机器人是科幻致于现实的浪漫'),
       (8, 'club', 8, '生态自然科考协会的slogan是什么？',
        '自然之美，科学之光|探索自然界的奥秘|与自然同行，探索生态奥秘|保护生态，探索自然', '探索自然界的奥秘'),
       (9, 'club', 9, '爱和猫协的slogan是什么？',
        '猫咪的守护者，校园的爱心使者|爱猫，爱生活，爱校园|科普爱猫知识，关注校园猫咪，爱和你在一起|关注流浪猫，传递爱与关怀',
        '科普爱猫知识，关注校园猫咪，爱和你在一起'),
       (10, 'club', 10, 'FudanACMICPC的slogan是什么？',
        '破题于弹指之间|编程挑战，智慧对决|算法之美，竞赛之魂|代码世界，解题高手', '破题于弹指之间');

INSERT INTO `quiz_record` (`id`, `user_id`, `quiz_id`, `answer`, `is_correct`, `create_timestamp`)
VALUES (1, 1, 1, '披音乐星光，传弦歌魅力', TRUE, '2024-05-4 12:25:30'),
       (2, 1, 2, '钢琴之声，心灵之交', FALSE, '2024-05-12 20:09:36'),
       (3, 1, 3, '舞步中的诗，生命中的歌', FALSE, '2024-05-12 21:15:43');