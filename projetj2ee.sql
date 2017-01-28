-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 31 Mai 2014 à 17:49
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `projetj2ee`
--

-- --------------------------------------------------------

--
-- Structure de la table `actualites`
--

CREATE TABLE IF NOT EXISTS `actualites` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Titre` text NOT NULL,
  `Description` text NOT NULL,
  `URL` text NOT NULL,
  `Date` date NOT NULL,
  `IDAdmin` int(11) NOT NULL,
  `Statut` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDAdmin` (`IDAdmin`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `actualites`
--

INSERT INTO `actualites` (`ID`, `Titre`, `Description`, `URL`, `Date`, `IDAdmin`, `Statut`) VALUES
(7, 'titre 2', 'mon description', 'titre 2_7_0_1_0.png', '2014-05-21', 1, 2),
(8, 'New Product Actualitite', 'sqfkfnskfskfdskgfnsdf;ds;sdv;sdn vf;dsfn,klsdf,kdgklsdgnsdklgndklgnsdklfgn,sdfkldslfgn,gsd,g:,sdglsdgmsdgmsdg,sd:g,dsg:sd,fgk:sddsfsdfsdfdsgddsfksdgdsgfmsdlf;dvsl,vdsfs,flmsdg,;sdlgsdg,dgdslmgsd,glmsdgmlsd,gsdlg,msdg,sd,mgl,sdmf,sdf,sd,mfsdf,sdmflsdfmsdf,sdfsdlmf', 'New Product Actualitite_8_0_2_0.png', '2014-05-26', 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `administrateurs`
--

CREATE TABLE IF NOT EXISTS `administrateurs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` text NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `DateInscription` date NOT NULL,
  `Level` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Username` (`Username`,`Email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `administrateurs`
--

INSERT INTO `administrateurs` (`ID`, `Username`, `Password`, `Email`, `Nom`, `Prenom`, `Adresse`, `DateInscription`, `Level`) VALUES
(1, 'SuperAdmin', '/9/YcVshKcZeYg+8mKjisA==', 'SuperAdmin@smartphone.com', 'Messaoudi', 'Takieddine', 'Avenue Zriba Hammem', '2014-05-17', 1),
(2, 'NewAdmin', '/9/YcVshKcZeYg+8mKjisA==', 'newadmin@mail.com', 'Messaoudi', 'Takieddine', 'Avenue Zriba Hammem', '2014-05-17', 2);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` text NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Ville` int(11) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `TypeCard` int(11) NOT NULL,
  `NumeroCard` varchar(16) NOT NULL,
  `NewsLettres` int(11) NOT NULL,
  `DateInscription` date NOT NULL,
  `AllowedToSubmit` int(11) NOT NULL,
  `Actif` int(11) NOT NULL,
  `Country` int(11) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` text NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Username` (`Username`,`Email`),
  KEY `TypeCard` (`TypeCard`),
  KEY `Ville` (`Ville`),
  KEY `Country` (`Country`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=95 ;

--
-- Contenu de la table `clients`
--

INSERT INTO `clients` (`ID`, `Username`, `Password`, `Email`, `Nom`, `Prenom`, `Ville`, `Adresse`, `TypeCard`, `NumeroCard`, `NewsLettres`, `DateInscription`, `AllowedToSubmit`, `Actif`, `Country`, `Age`, `Gender`) VALUES
(5, 'Takieddine', '/9/YcVshKcZeYg+8mKjisA==', 'monemaildsdfsdfsd@mail.com', 'taki5sssssss', 'mess5', 19, 'mon adresse', 5, '2222222222222222', 1, '2014-05-27', 1, 2, 1, 24, 'Female'),
(12, 'username4', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com4', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(13, 'username5', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com5', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(14, 'username6', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com6', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(15, 'username7', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com7', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(16, 'username8', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com8', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(17, 'username9', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com9', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(18, 'username10', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com10', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(19, 'username11', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com11', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(20, 'username12', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com12', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(21, 'username13', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com13', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(22, 'username14', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com14', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(23, 'username15', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com15', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(24, 'username16', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com16', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(25, 'username17', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com17', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(26, 'username18', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com18', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(27, 'username19', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com19', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(28, 'username20', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com20', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(29, 'username21', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com21', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(31, 'username23', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com23', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(32, 'username24', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com24', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(33, 'username25', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com25', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(34, 'username26', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com26', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(35, 'username27', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com27', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(36, 'username28', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com28', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 3, 1, 25, 'Male'),
(37, 'username29', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com29', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(38, 'username30', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com30', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 2, 1, 25, 'Male'),
(39, 'username31', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com31', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(40, 'username32', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com32', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 0, 1, 1, 25, 'Male'),
(41, 'username33', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com33', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(42, 'username34', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com34', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(43, 'username35', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com35', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 2, 1, 25, 'Male'),
(45, 'username37', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com37', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(46, 'username38', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com38', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(47, 'username39', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com39', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(48, 'username40', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com40', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(49, 'username41', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com41', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(50, 'username42', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com42', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(51, 'username43', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com43', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(52, 'username44', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com44', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(53, 'username45', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com45', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(54, 'username46', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com46', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(55, 'username47', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com47', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(56, 'username48', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com48', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(57, 'username49', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com49', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(58, 'username50', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com50', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 2, 1, 25, 'Male'),
(59, 'username51', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com51', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(60, 'username52', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com52', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(61, 'username53', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com53', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(62, 'username54', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com54', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(63, 'username55', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com55', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(64, 'username56', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com56', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(65, 'username57', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com57', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(66, 'username58', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com58', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(67, 'username59', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com59', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(68, 'username60', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com60', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(69, 'username61', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com61', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(70, 'username62', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com62', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(71, 'username63', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com63', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(72, 'username64', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com64', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(73, 'username65', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com65', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(74, 'username66', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com66', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(75, 'username67', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com67', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(76, 'username68', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com68', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(77, 'username69', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com69', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(78, 'username70', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com70', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(80, 'username72', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com72', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(81, 'username73', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com73', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(82, 'username74', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com74', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(83, 'username75', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com75', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(84, 'username76', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com76', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(85, 'username77', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com77', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 0, 1, 1, 25, 'Male'),
(86, 'username78', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'monemail@mail.com78', 'nom', 'prenom', 1, 'adresse', 1, '0000000000000000', 0, '2014-05-24', 1, 1, 1, 25, 'Male'),
(88, 'NewTakitaki', '80apbb9vucrwGj3HBX4qy0hw/lFbiofDyxnO0LO/zsw=', 'NewTaki@emailtaki2.com', 'Messaodtaki', 'Takieddinemessaoudi', 17, 'this my new adresse', 2, '9584712568012658', 1, '2014-05-25', 0, 1, 2, 25, 'Female'),
(89, 'The Passenger', '/9/YcVshKcZeYg+8mKjisA==', 'messaoudi.takieddine@gmail.com', 'Messaoudi', 'takieddine', 24, 'Avenue de lenvironnement zriba hammam', 2, '1234567890258769', 1, '2014-05-25', 1, 0, 1, 25, 'Male'),
(90, 'sdfdfsdf', '58Q7Sg9XsPMXRw2jpfxh0Q==', 'sdfsdfd@email.com', 'taki', 'last name', 20, 'first', 5, '1234567890258769', 1, '2014-05-25', 1, 2, 1, 17, 'Male'),
(91, 'newuser', '/9/YcVshKcZeYg+8mKjisA==', 'emaik@email.com', 'taki', 'mess', 2, 'new adresse', 2, '5486987452842569', 1, '2014-05-26', 1, 0, 1, 34, 'Female'),
(92, 'aaaaaa', 'jhD6PuumlZm3d0wCLvhUbw==', 'sssssssss@mmmm.ccc', 'sssss', 'fffffff', 20, 'Adresse', 3, '1234567890258769', 1, '2014-05-26', 1, 0, 1, 13, 'Male'),
(93, 'serveur', 'null', 'email@mail.ser', 'taki', 'taki', 24, 'mon adresse', 2, '1234567890258769', 1, '2014-05-27', 1, 0, 1, 15, 'Male'),
(94, 'test', 'xGdooY+6V8q1cze7mR9TjQ==', 'test@mailtest.com', 'test', 'test', 24, 'testsdfsdfsdf', 5, '2222222222222222', 1, '2014-05-29', 1, 1, 1, 12, 'Male');

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE IF NOT EXISTS `commandes` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDClient` int(11) NOT NULL,
  `IDProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  `Date` date NOT NULL,
  `ValidationClient` int(11) NOT NULL,
  `ValidationAdmin` int(11) NOT NULL,
  `Statut` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDClient` (`IDClient`),
  KEY `IDProduit` (`IDProduit`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `commandes`
--

INSERT INTO `commandes` (`ID`, `IDClient`, `IDProduit`, `Quantite`, `Date`, `ValidationClient`, `ValidationAdmin`, `Statut`) VALUES
(4, 18, 19, 10, '2014-05-08', 1, 0, 3);

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

CREATE TABLE IF NOT EXISTS `countries` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` text NOT NULL,
  `Statut` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `countries`
--

INSERT INTO `countries` (`ID`, `Libelle`, `Statut`) VALUES
(1, 'Tunisia', 1),
(2, 'Algerie', 1);

-- --------------------------------------------------------

--
-- Structure de la table `marques`
--

CREATE TABLE IF NOT EXISTS `marques` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Marque` varchar(255) NOT NULL,
  `Description` text NOT NULL,
  `Logo` text NOT NULL,
  `Statut` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `marques`
--

INSERT INTO `marques` (`ID`, `Marque`, `Description`, `Logo`, `Statut`) VALUES
(1, 'marque0', 'description0', 'logo0', 1),
(2, 'marque1', 'description1', 'logo1', 1),
(3, 'marque2', 'description2', 'logo2', 1),
(4, 'marque3', 'description3', 'logo3', 1),
(5, 'marque4', 'description4', 'logo4', 1);

-- --------------------------------------------------------

--
-- Structure de la table `newsletters`
--

CREATE TABLE IF NOT EXISTS `newsletters` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDClient` int(11) NOT NULL,
  `IDMarque` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDClient` (`IDClient`),
  KEY `IDMarque` (`IDMarque`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48 ;

--
-- Contenu de la table `newsletters`
--

INSERT INTO `newsletters` (`ID`, `IDClient`, `IDMarque`) VALUES
(9, 88, 2),
(10, 88, 4),
(11, 88, 5),
(12, 89, 1),
(13, 89, 2),
(14, 89, 3),
(15, 89, 4),
(16, 89, 5),
(17, 90, 3),
(18, 90, 4),
(19, 90, 5),
(20, 91, 1),
(21, 91, 2),
(22, 91, 3),
(23, 91, 4),
(24, 91, 5),
(25, 92, 1),
(26, 92, 3),
(32, 5, 1),
(33, 5, 2),
(34, 5, 3),
(35, 5, 4),
(36, 93, 1),
(37, 93, 3),
(38, 93, 5),
(44, 94, 1),
(45, 94, 2),
(46, 94, 3),
(47, 94, 5);

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE IF NOT EXISTS `produits` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(150) NOT NULL,
  `PrixUnitaire` double NOT NULL,
  `Quantite` int(11) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `Marque` int(11) NOT NULL,
  `Description` text NOT NULL,
  `Owner` int(11) NOT NULL,
  `IDOwner` int(11) NOT NULL,
  `Statut` int(11) NOT NULL,
  `DateSubmit` date NOT NULL,
  `Downloads` int(11) NOT NULL,
  `Rating` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `Marque` (`Marque`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Contenu de la table `produits`
--

INSERT INTO `produits` (`ID`, `Libelle`, `PrixUnitaire`, `Quantite`, `URL`, `Marque`, `Description`, `Owner`, `IDOwner`, `Statut`, `DateSubmit`, `Downloads`, `Rating`) VALUES
(1, 'Galaxy S5', 23, 60, 'SMARTPHONE.png', 2, 'this is a new product', 1, 1, 1, '2014-05-06', 0, 0),
(4, 'New product', 14, 14, 'New product_marque3_4_0_0_4_3_0_0.jpg', 4, 'desc new prod', 1, 1, 1, '2014-05-25', 0, 0),
(5, 'produitsumsun', 13, 14, 'produitsumsun_marque4_5_1_1_5_3_2_2.jpg', 5, 'desssss', 1, 1, 1, '2014-05-25', 0, 0),
(6, 'Galaxy S5', 8, 13, 'Galaxy S5_marque4_6_1_1_5_4_3_3.jpg', 5, 'sqsqfd', 1, 1, 1, '2014-05-25', 0, 0),
(7, 'produit1', 11, 13, 'produit1_marque4_7_1_1_5_5_4_4.jpg', 5, 'dsdsqqsd', 1, 1, 0, '2014-05-25', 0, 0),
(8, 'New product', 11, 16, 'New product_marque4_8_1_1_5_6_5_5.jpg', 5, 'sdfdsfsd', 1, 1, 0, '2014-05-25', 0, 0),
(9, 'Galaxy S5', 9, 12, 'Galaxy S5_marque4_9_1_1_5_7_6_6.jpg', 5, 'sdfsdfs', 1, 1, 0, '2014-05-25', 0, 0),
(10, 'Galaxy S5', 9, 12, 'Galaxy S5_marque4_10_1_1_5_8_7_7.jpg', 5, 'sdfsdfs', 1, 1, 0, '2014-05-25', 0, 0),
(11, 'sdfsdfsf', 13, 15, 'sdfsdfsf_marque0_11_1_1_1_9_8_8.jpg', 1, 'dgd', 1, 1, 0, '2014-05-25', 0, 0),
(12, 'azerty', 12, 19, 'azerty_marque4_12_1_1_5_10_9_9.jpg', 5, 'sfsdfdsdf', 1, 1, 1, '2014-05-25', 0, 0),
(13, 'azerty', 12, 19, 'azerty_marque4_13_1_1_5_11_10_10.jpg', 5, 'sfsdfdsdf', 1, 1, 1, '2014-05-25', 0, 0),
(14, 'marque 0 prod', 14, 16, 'marque 0 prod_marque0_14_1_1_1_12_11_11.jpg', 1, 'marque0proddescnn', 1, 1, 3, '2014-05-26', 0, 0),
(15, 'produitsumsun', 9, 13, 'produitsumsun_marque4_15_1_1_5_13_12_12.jpg', 5, 'sfsfs', 1, 1, 0, '2014-05-26', 0, 0),
(16, 'produit1', 12, 13, 'produit1_marque4_16_1_1_5_14_13_13.jpg', 5, 'dsfdf', 1, 1, 0, '2014-05-26', 0, 0),
(17, 'New product', 9, 15, 'New product_marque4_17_1_1_5_15_14_14.jpg', 5, 'sdfsdfs', 1, 1, 0, '2014-05-26', 0, 0),
(18, 'New product', 9, 15, 'New product_marque4_18_1_1_5_16_15_15.jpg', 5, 'sdfsdfs', 1, 1, 0, '2014-05-26', 0, 0),
(19, 'produit1', 11, 12, 'produit1_marque4_19_1_1_5_17_16_16.jpg', 5, 'dsfdf', 1, 1, 0, '2014-05-26', 0, 0),
(20, 'marque 0 prod', 12, 13, 'marque 0 prod_marque4_20_1_1_5_18_17_17.jpg', 5, 'takisss', 1, 1, 0, '2014-05-26', 0, 0),
(21, 'marque 0 prod', 14, 16, 'marque 0 prod_marque2_21_1_1_3_19_18_18.jpg', 3, 'sdfsdf', 1, 1, 0, '2014-05-26', 0, 0),
(22, 'marque 0 prod', 14, 16, 'marque 0 prod_marque2_22_1_1_3_20_19_19.jpg', 3, 'sdfsdf', 1, 1, 0, '2014-05-26', 0, 0),
(23, 'Galaxy S5', 9, 14, 'Galaxy S5_marque4_23_1_1_5_21_20_20.jpg', 5, 'dsfsdfds', 1, 1, 0, '2014-05-26', 0, 0),
(24, 'Galaxy S5', 9, 14, 'Galaxy S5_marque4_24_1_1_5_22_21_21.jpg', 5, 'dsfsdfds', 1, 1, 0, '2014-05-26', 0, 0),
(25, 'Galaxy S5', 9, 14, 'Galaxy S5_marque4_25_1_1_5_23_22_22.jpg', 5, 'dsfsdfds', 1, 1, 0, '2014-05-26', 0, 0),
(26, 'New product', 11, 14, 'New product_marque4_26_1_1_5_24_23_23.jpg', 5, 'fdsfsdfsd', 1, 1, 0, '2014-05-26', 0, 0),
(27, 'produit1', 12, 9, 'produit1_marque4_27_1_1_5_25_24_24.png', 5, 'taki', 1, 1, 0, '2014-05-26', 0, 0),
(28, 'aaaaaaa', 36, 500, 'aaaaaaa_marque0_28_1_1_1_26_25_25.png', 1, 'ssssssssssssssss', 1, 1, 1, '2014-05-26', 0, 0),
(29, 'gallll', 23, 500, 'qsfqsfqs', 3, 'xwxwvxwvxwvwxvwx', 2, 94, 3, '2014-05-27', 0, 0),
(30, 'produit1', 11, 14, 'produit1_marque4_30_2_94_5_27_1_1.png', 5, 'dsfsdfsd', 2, 94, 1, '2014-05-27', 0, 0),
(31, 'produit1', 11, 11, 'produit1_marque4_31_2_94_5_28_2_2.png', 5, 'dessss', 2, 94, 1, '2014-05-27', 0, 0),
(32, 'ssssssss', 12, 13, 'ssssssss_marque0_32_2_94_1_29_3_3.jpg', 1, 'dfsdfsdfsdf', 2, 94, 0, '2014-05-28', 0, 0),
(33, 'dsgsdg', 12, 13, 'dsgsdg_marque1_33_2_94_2_30_4_4.jpg', 2, 'sdgsdfgsd', 2, 94, 1, '2014-05-29', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `typecard`
--

CREATE TABLE IF NOT EXISTS `typecard` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Libelle` text NOT NULL,
  `Description` text NOT NULL,
  `Statut` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `typecard`
--

INSERT INTO `typecard` (`ID`, `Libelle`, `Description`, `Statut`) VALUES
(1, 'libelle0', 'description0', 1),
(2, 'libelle1', 'description1', 1),
(3, 'libelle2', 'description2', 1),
(4, 'libelle3', 'description3', 1),
(5, 'libelle4', 'description4', 1);

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDCountry` int(11) NOT NULL,
  `Libelle` text NOT NULL,
  `Statut` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDCountry` (`IDCountry`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`ID`, `IDCountry`, `Libelle`, `Statut`) VALUES
(1, 1, 'Ariana', 1),
(2, 1, 'Beja', 1),
(3, 1, 'BenArous', 1),
(4, 1, 'Bizerte', 1),
(5, 1, 'Gabes', 1),
(6, 1, 'Gafsa', 1),
(7, 1, 'Jendouba', 1),
(8, 1, 'Kairouan', 1),
(9, 1, 'Kasserine', 1),
(10, 1, 'Kebili', 1),
(11, 2, 'Le Kef', 1),
(12, 1, 'Mahdia', 1),
(13, 1, 'Manouba', 1),
(14, 1, 'Mednine', 1),
(15, 1, 'Monastir', 1),
(16, 1, 'Nabeul', 1),
(17, 2, 'Sfax', 1),
(18, 1, 'Sidi Bouzid', 1),
(19, 1, 'Siliana', 1),
(20, 1, 'Sousse', 1),
(21, 1, 'Tataouine', 1),
(22, 1, 'Tozeur', 1),
(23, 1, 'Tunis', 1),
(24, 1, 'Zaghouan', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `actualites`
--
ALTER TABLE `actualites`
  ADD CONSTRAINT `actualites_ibfk_1` FOREIGN KEY (`IDAdmin`) REFERENCES `administrateurs` (`ID`);

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`TypeCard`) REFERENCES `typecard` (`ID`),
  ADD CONSTRAINT `clients_ibfk_2` FOREIGN KEY (`Ville`) REFERENCES `ville` (`ID`),
  ADD CONSTRAINT `clients_ibfk_3` FOREIGN KEY (`Country`) REFERENCES `countries` (`ID`);

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`IDClient`) REFERENCES `clients` (`ID`),
  ADD CONSTRAINT `commandes_ibfk_2` FOREIGN KEY (`IDProduit`) REFERENCES `produits` (`ID`);

--
-- Contraintes pour la table `newsletters`
--
ALTER TABLE `newsletters`
  ADD CONSTRAINT `newsletters_ibfk_1` FOREIGN KEY (`IDClient`) REFERENCES `clients` (`ID`),
  ADD CONSTRAINT `newsletters_ibfk_2` FOREIGN KEY (`IDMarque`) REFERENCES `marques` (`ID`);

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `produits_ibfk_1` FOREIGN KEY (`Marque`) REFERENCES `marques` (`ID`);

--
-- Contraintes pour la table `ville`
--
ALTER TABLE `ville`
  ADD CONSTRAINT `ville_ibfk_1` FOREIGN KEY (`IDCountry`) REFERENCES `countries` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
