-- Create the database

-- CREATE database
DROP DATABASE IF EXISTS `jensen_library`;
CREATE DATABASE `jensen_library`;

USE `jensen_library`;

-- Create the necessary views to help with database schema queries

-- VIEWS

	-- Columns types information
	DROP VIEW IF EXISTS jensen_library_types;
	CREATE VIEW `jensen_library_types` AS
	SELECT
		`table_name` AS `table`,
		`column_name` AS `column`,
		`is_nullable`,
		`data_type` AS `type`,
		`character_maximum_length` AS `max_length`
	FROM information_schema.columns
	WHERE table_schema = 'jensen_library';

	-- Primary keys information
	DROP VIEW IF EXISTS `jensen_library_pk`;
	CREATE VIEW `jensen_library_pk` AS
	SELECT
		table_name as `table`,
		column_name as `column`
	FROM information_schema.key_column_usage
	WHERE
		constraint_schema = 'jensen_library' AND
		constraint_name = 'PRIMARY';

	-- Foreign keys information
    DROP VIEW IF EXISTS `jensen_library_fk`;
	CREATE VIEW `jensen_library_fk` AS
    SELECT
		constraint_name,
		table_name as `child_table`,
		column_name as `child_column`,
		referenced_table_name as `parent_table`,
		referenced_column_name as `parent_column`

	FROM information_schema.key_column_usage
	WHERE
		constraint_schema = 'jensen_library' AND
		referenced_table_name IS NOT NULL;

-- Create the necessary tables, foreign keys, etc. to model the problem specified in README.md

CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)) ;

CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `phone_number` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `books` (
  `id` int NOT NULL,
  `isbn` char(13) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `id_author` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk__books__authors_idx` (`id_author`),
  CONSTRAINT `fk__books__authors` FOREIGN KEY (`id_author`) REFERENCES `authors` (`id`) ON DELETE SET NULL ON UPDATE CASCADE);

CREATE TABLE `shared_list_customers_books` (
  `id_customer` int DEFAULT NULL,
  `id_book` int DEFAULT NULL,
  `loan_date` date DEFAULT NULL,
  `loan_days` int DEFAULT NULL,
  KEY `fk__shared_list_customers_books__customers_idx` (`id_customer`),
  KEY `fk__shared_list_customers_books__books_idx` (`id_book`),
  CONSTRAINT `fk__shared_list_customers_books__books` FOREIGN KEY (`id_book`) REFERENCES `books` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk__shared_list_customers_books__customers` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE);


-- VG view

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
VIEW `lazy_customer` AS
    SELECT DISTINCT
        `customers`.`id` AS `id`,
        `customers`.`first_name` AS `first_name`,
        `customers`.`last_name` AS `last_name`,
        `customers`.`email` AS `email`,
        `customers`.`phone_number` AS `phone_number`
    FROM
        (`shared_list_customers_books`
        JOIN `customers` ON ((`customers`.`id` = `shared_list_customers_books`.`id_customer`)))
    WHERE
        ((`shared_list_customers_books`.`loan_date` + INTERVAL `shared_list_customers_books`.`loan_days` DAY) < CURDATE())