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

SET FOREIGN_KEY_CHECKS = 1;