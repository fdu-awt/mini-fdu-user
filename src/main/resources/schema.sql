SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `self_image` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `history_message`;
CREATE TABLE `history_message`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `local_id` INT NOT NULL,
    `remote_id` INT NOT NULL,
    `content` VARCHAR(255) NOT NULL,
    `time_stamp` TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`local_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`remote_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `study_question`;
CREATE TABLE `study_question`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `topic` VARCHAR(255) NOT NULL,
    `data_id` INT NOT NULL, -- 关联的知识对应的 id
    `question` VARCHAR(255) NOT NULL,
    `options` VARCHAR(255) NOT NULL, -- 存储如 "A. 1902年, B. 1905年, C. 1911年, D. 1908年"
    `answer` CHAR(1) NOT NULL, -- 存储答案的字母，如 'B'
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;