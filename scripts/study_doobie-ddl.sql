-- MySQL Script generated by MySQL Workbench
-- Mon Aug 31 22:58:36 2015
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema study_doobie
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `study_doobie` ;

-- -----------------------------------------------------
-- Schema study_doobie
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `study_doobie` DEFAULT CHARACTER SET utf8mb4 ;
USE `study_doobie` ;

-- -----------------------------------------------------
-- Table `study_doobie`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `study_doobie`.`users` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `is_admin` TINYINT(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
