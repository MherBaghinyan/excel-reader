
DROP DATABASE IF EXISTS city_university;
CREATE DATABASE city_university;

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(50) DEFAULT NULL,
`date_of_birth` datetime,
`age` INT DEFAULT NULL,
`gender` varchar(50) DEFAULT NULL,
`nationality` text NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `person_details`;
CREATE TABLE `person_details` (
`person_id` bigint(20) NOT NULL,
`current_comm_role` bigint(20) NOT NULL,
`historic_comm_role` bigint(20) NOT NULL,
`last_comp` NUMERIC(15,2),
`liquid_wealth` NUMERIC(15,2),
`total_wealth` NUMERIC(15,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`person_id` bigint(20) NOT NULL,
`date` datetime,
`institute` longtext,
`qualification` varchar(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `achievements`;
CREATE TABLE `achievements` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`person_id` bigint(20) NOT NULL,
`date` datetime,
`organization` varchar(50),
`achievements` longtext,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `past_activities`;
CREATE TABLE `past_activities` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`person_id` bigint(20) NOT NULL,
`start_date` datetime,
`end_date` datetime,
`organization` varchar(50),
`role` varchar(50),
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `current_other_activities`;
CREATE TABLE `current_other_activities` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`person_id` bigint(20) NOT NULL,
`start_date` datetime,
`organization` varchar(50),
`role` varchar(50),
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`person_id` bigint(20) NOT NULL,
`start_date` datetime,
`end_date` datetime,
`organization` varchar(50),
`role` varchar(50),
`role_description` varchar(50),
`yrs_to_retirement` varchar(50),
`Committee` varchar(50),
`last_comp` NUMERIC(15,3),
`liquid_wealth` NUMERIC(15,3),
`total_wealth` NUMERIC(15,3)
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

