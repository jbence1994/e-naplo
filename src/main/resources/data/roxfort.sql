DROP DATABASE IF EXISTS `roxfort`;
CREATE DATABASE IF NOT EXISTS `roxfort` DEFAULT CHARACTER SET utf8 COLLATE utf8_hungarian_ci;

DROP TABLE IF EXISTS `grades`;
CREATE TABLE IF NOT EXISTS `grades` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` int(10) UNSIGNED NOT NULL,
  `subject_id` int(10) UNSIGNED NOT NULL,
  `value` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `subject_id` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

DROP TABLE IF EXISTS `houses`;
CREATE TABLE IF NOT EXISTS `houses` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

INSERT INTO `houses` (`id`, `name`) VALUES
(1, 'Griffendél'),
(2, 'Mardekár'),
(3, 'Hugrabug'),
(4, 'Hollóhát');

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `house_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `house_id` (`house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

INSERT INTO `students` (`id`, `first_name`, `last_name`, `house_id`) VALUES
(1, 'Harry', 'Potter', 1),
(2, 'Ron', 'Weasley', 1),
(3, 'Hermione', 'Granger', 1),
(4, 'Draco', 'Malfoy', 2),
(5, 'Ginny', 'Weasley', 1),
(6, 'Neville', 'Longbottom', 1),
(7, 'Luna', 'Lovegood', 4),
(8, 'Vincent', 'Crak', 2),
(9, 'Gregory', 'Monstro', 2),
(10, 'Cho', 'Chang', 4),
(11, 'Parvati', 'Patil', 1),
(12, 'Padma', 'Patil', 4),
(13, 'Seamus', 'Finnigan', 1),
(14, 'Cedric', 'Diggory', 3),
(15, 'Ernie', 'Macmillan', 3),
(16, 'Hannah', 'Abbott', 3),
(17, 'Justin', 'Finch-Fletchley', 3),
(18, 'Susan', 'Bones', 3),
(19, 'Megan', 'Jones', 3),
(20, 'Zacharias', 'Smith', 3),
(21, 'Terry', 'Boot', 4),
(22, 'Mandy', 'Brocklehurst', 4),
(23, 'Michael', 'Corner', 4),
(24, 'Stephen', 'Cornfoot', 4),
(25, 'Millicent', 'Bulstrode', 2),
(26, 'Tracey', 'Davis', 2),
(27, 'Daphne', 'Greengrass', 2),
(28, 'Theodore', 'Nott', 2),
(29, 'Pansy', 'Parkinson', 2),
(30, 'Blaise', 'Zambini', 2),
(31, 'Lavender', 'Brown', 1),
(32, 'Dean', 'Thomas', 1),
(33, 'Colin', 'Creevey', 1),
(34, 'Romilda', 'Vane', 1);

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

INSERT INTO `subjects` (`id`, `name`) VALUES
(1, 'Asztronómia'),
(2, 'Átváltoztatástan'),
(3, 'Bájitaltan'),
(4, 'Bűbájtan'),
(5, 'Gyógynövénytan'),
(6, 'Jóslástan'),
(7, 'Legendás Lények Gondozása'),
(8, 'Mágiatörténet'),
(9, 'Mugliismeret'),
(10, 'Repüléstan'),
(11, 'Rúnaismeret'),
(12, 'Sötét Varázslatok Kivédése'),
(13, 'Számmisztika'),
(14, 'Párbajszakkör');

ALTER TABLE `grades`
  ADD CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`house_id`) REFERENCES `houses` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;
