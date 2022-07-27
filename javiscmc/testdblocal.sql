-- Drop the database if it already exists
DROP DATABASE IF EXISTS Mock_Project_3;
-- Create database
CREATE DATABASE IF NOT EXISTS Mock_Project_3;
USE Mock_Project_3;

-- Create table user
DROP TABLE IF EXISTS 	`tbl_user`;
CREATE TABLE IF NOT EXISTS `tbl_user` (
    `id` SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `password` varchar(500),
    `salary` double,
    `member_type` varchar(1)
   
);

INSERT INTO 			`tbl_user` 	(`email`,`password`,`salary`,`member_type`	)
	VALUE						('abc@gmail.com', '123456', 150005, 's'), 
								('xyz@gmail.com', '123456',300000, 'g');
								