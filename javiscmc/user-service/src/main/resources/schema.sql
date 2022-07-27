DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
                        `id` INT AUTO_INCREMENT PRIMARY KEY,
                        `email` VARCHAR(50) NOT NULL,
                        `password` varchar(500) NOT NULL,
                        `salary` double,
                        `member_type` varchar(1)
);

