-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema meal
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `meal`;
CREATE SCHEMA IF NOT EXISTS `meal` DEFAULT CHARACTER SET utf8 ;
USE `meal` ;

-- -----------------------------------------------------
-- Table `meal`.`Comidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meal`.`Comidas` (
  `idMeal` INT NOT NULL AUTO_INCREMENT,
  `strMeal` VARCHAR(100) NULL,
  `strDrinkAlternate` VARCHAR(100) NULL,
  `strCategory` VARCHAR(45) NULL,
  `strArea` VARCHAR(45) NULL,
  `strTags` VARCHAR(100) NULL,
  `strInstructions` TEXT NULL,
  `strMealThumb` VARCHAR(255) NULL,
  `strIngredient1` VARCHAR(100) NULL,
  `strIngredient2` VARCHAR(100) NULL,
  `strIngredient3` VARCHAR(100) NULL,
  `strIngredient4` VARCHAR(100) NULL,
  `favorite` INT NULL,
  PRIMARY KEY (`idMeal`)
) ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
