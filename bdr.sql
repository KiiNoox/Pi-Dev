-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 04, 2023 at 06:08 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdr`
--

-- --------------------------------------------------------

--
-- Table structure for table `abonnement`
--

CREATE TABLE `abonnement` (
  `id` int(11) NOT NULL,
  `ab_nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ab_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ab_prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `abonnement`
--

INSERT INTO `abonnement` (`id`, `ab_nom`, `ab_type`, `ab_prix`) VALUES
(5, 'bodycombat', 'art martiaux', 35),
(6, 'Musculation', 'fitness', 30),
(7, 'yuga', 'chilling', 39),
(8, 'test', 'test', 55);

-- --------------------------------------------------------

--
-- Table structure for table `carac_sport`
--

CREATE TABLE `carac_sport` (
  `id` int(11) NOT NULL,
  `user` int(11) DEFAULT NULL,
  `taille` int(11) NOT NULL,
  `poids` int(11) NOT NULL,
  `prot_needs` int(11) DEFAULT NULL,
  `calorie_need` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `genre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `carac_sport`
--

INSERT INTO `carac_sport` (`id`, `user`, `taille`, `poids`, `prot_needs`, `calorie_need`, `age`, `genre`) VALUES
(1, 1, 180, 78, 156, 1745, 33, 'homme');

-- --------------------------------------------------------

--
-- Table structure for table `categorieabo`
--

CREATE TABLE `categorieabo` (
  `id` int(11) NOT NULL,
  `abonnement_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categorieabo`
--

INSERT INTO `categorieabo` (`id`, `abonnement_id`, `nom`) VALUES
(8, 6, 'AMIR');

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `id` int(11) NOT NULL,
  `id_publication` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_com` date DEFAULT NULL,
  `note` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id`, `id_publication`, `nom`, `comment`, `date_com`, `note`) VALUES
(6, 7, 'seif', 'quelle bonne initiative', '2022-01-01', 4),
(7, 7, 'amir', 'c\'est sur je vais y aller', '2017-01-01', 4),
(8, 8, 'raed', 'j\'en ai vraiment besoin', '2017-01-01', 4),
(9, 8, 'oussema', 'pas interessant', '2017-01-01', 2),
(10, 7, 'seif', 'seif', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20220512060123', '2022-05-12 08:01:32', 826);

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE `employe` (
  `id_emp` int(11) NOT NULL,
  `date_nais` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `profession` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id_even` int(11) NOT NULL,
  `id_salle` int(11) DEFAULT NULL,
  `descri` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Date` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom_even` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id_even`, `id_salle`, `descri`, `Date`, `nom_even`) VALUES
(2, 1, 'dzada', 'dazdad', 'addazd');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `idEven` int(11) NOT NULL,
  `dateEven` date NOT NULL,
  `descri` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`idEven`, `dateEven`, `descri`, `nom`, `prix`) VALUES
(2, '2022-05-18', 'festival les mills', 'bodycombat', 120),
(5, '2017-01-01', 'RAED', 'BEJAOUI', 300),
(6, '2021-01-01', 'SAIF', 'RRRR', 111),
(7, '2017-01-01', 'SS', 'RAA', 120);

-- --------------------------------------------------------

--
-- Table structure for table `mail`
--

CREATE TABLE `mail` (
  `id` int(11) NOT NULL,
  `subject` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `object` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE `participant` (
  `id_parti` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`id_parti`, `idEvent`, `id_user`) VALUES
(2, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payement`
--

CREATE TABLE `payement` (
  `id_pay` int(11) NOT NULL,
  `ab_id` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `montant` int(11) NOT NULL,
  `date_pay` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `payement`
--

INSERT INTO `payement` (`id_pay`, `ab_id`, `id_user`, `montant`, `date_pay`) VALUES
(1, 5, 1, 35000, '25/12/2021'),
(2, 5, 1, 40000, '25/12/2022'),
(3, 5, 1, 35000, '25/12/2021'),
(4, 5, 1, 40000, '25/12/2022'),
(5, NULL, NULL, 3000, '05/13/2022');

-- --------------------------------------------------------

--
-- Table structure for table `planning_client`
--

CREATE TABLE `planning_client` (
  `id_p` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_seance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `planning_coach`
--

CREATE TABLE `planning_coach` (
  `id_p` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_seance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `plat`
--

CREATE TABLE `plat` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `poids` int(11) NOT NULL,
  `sodium` int(11) NOT NULL,
  `cholesterol` int(11) NOT NULL,
  `carbohydrate` int(11) NOT NULL,
  `protein` int(11) NOT NULL,
  `calories` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `plat`
--

INSERT INTO `plat` (`id`, `nom`, `poids`, `sodium`, `cholesterol`, `carbohydrate`, `protein`, `calories`) VALUES
(5, 'SQD', 32, 23, 23, 232, 23, 23),
(6, 'fe', 13123, 12312, 13123, 1, 1231, 33);

-- --------------------------------------------------------

--
-- Table structure for table `profanities`
--

CREATE TABLE `profanities` (
  `id` int(11) NOT NULL,
  `word` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `profanities`
--

INSERT INTO `profanities` (`id`, `word`) VALUES
(1, 'fuck');

-- --------------------------------------------------------

--
-- Table structure for table `publication`
--

CREATE TABLE `publication` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datecreation` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `publication`
--

INSERT INTO `publication` (`id`, `nom`, `description`, `image`, `adresse`, `datecreation`) VALUES
(7, 'Comment se muscler', 'Tu souhaites prendre du muscle rapidement ? Alors il faut bien te nourrir et t’entraîner avec discipline. Dans cet article, nous te montrons 5 conseils et 9 exercices, qui t’aideront à atteindre tes objectifs. Tu trouveras aussi un PDF gratuit avec nos 9', 'contact-bg-627e110dce22c.jpeg', 'Tunis', '2022-05-13'),
(8, 'comment perdre du poids', 'Maigrir vite n’est pas forcément conseillé par les professionnels de la santé. Toutefois, il est possible de perdre rapidement du poids en suivant des consignes drastiques. Truc Mania vous explique comment procéder et vous encourage à poursuivre vos effor', 'schedule-bg-627e12f6416da.jpeg', 'sousse', '2022-05-13'),
(9, 'se sentir bien dans sa peau', 'Les diététiciens le répètent suffisamment : adopter une bonne hygiène alimentaire nécessite de varier les aliments et de les prendre en quantité raisonnable. Aucun aliment n\'est interdit, tant qu\'il est consommé modérément ! Le régime méditerranéen avec p', 'first-trainer-627e13a638896.jpeg', 'tunis', '2022-05-13'),
(11, 'test', 'testetststets', 'schedule-bg-627e2b018fa34.jpeg', 'tunis', '2022-05-13');

-- --------------------------------------------------------

--
-- Table structure for table `regime`
--

CREATE TABLE `regime` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `niveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `regime`
--

INSERT INTO `regime` (`id`, `titre`, `type`, `niveau`) VALUES
(1, 'fdgfdgfd', 'Perte de poids', 3),
(3, 'EXTREME', 'Gain de poids', 3),
(4, 'SOFT', 'Perte de poids', 1);

-- --------------------------------------------------------

--
-- Table structure for table `regime_plat`
--

CREATE TABLE `regime_plat` (
  `regime_id` int(11) NOT NULL,
  `plat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `regime_plat`
--

INSERT INTO `regime_plat` (`regime_id`, `plat_id`) VALUES
(3, 5),
(4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id_reser` int(11) NOT NULL,
  `id_even` int(11) DEFAULT NULL,
  `id_coach` int(11) NOT NULL,
  `id_salle` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id_reser`, `id_even`, `id_coach`, `id_salle`, `id_user`, `date`) VALUES
(13, NULL, 9, 2, 1, '2022-09-30 09:00:00'),
(25, NULL, 10, 3, 1, '2022-09-21 09:00:00'),
(30, NULL, 10, 2, 1, '2022-10-04 09:00:00'),
(31, NULL, 9, 3, 3, '2022-10-04 11:00:00'),
(32, NULL, 0, 1, 8, '2022-09-07 16:01:00'),
(33, NULL, 11, 3, 1, '2022-09-22 09:00:00'),
(35, NULL, 10, 2, 1, '2022-09-14 12:00:00'),
(36, NULL, 9, 3, 1, '2022-09-06 11:00:00'),
(37, NULL, 10, 2, 9, '2022-10-04 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `id_salle` int(11) NOT NULL,
  `adress_salle` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `salle`
--

INSERT INTO `salle` (`id_salle`, `adress_salle`, `nom`, `rating`) VALUES
(1, 'dzdazd', 'dzadazd', 2),
(2, 'rue chams', 'calif lac', 6),
(3, 'bardo', 'calif bardo', 7);

-- --------------------------------------------------------

--
-- Table structure for table `seance`
--

CREATE TABLE `seance` (
  `id_seance` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `type_seance` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `seance`
--

INSERT INTO `seance` (`id_seance`, `date`, `type_seance`) VALUES
(1, '2022-05-19 09:47:00', 'BODYATTACCK'),
(5, '2022-05-13 11:47:00', 'FZUYBUYB'),
(6, '2022-05-13 11:47:00', 'JHBEDUEV'),
(7, '2022-05-13 11:00:00', 'FHHYBF'),
(14, '2022-05-27 11:00:00', 'JHBEDUEV'),
(15, '2022-05-12 15:47:00', 'BODYATTACCK'),
(16, '2022-09-07 14:03:00', 'tabata'),
(17, '2022-09-22 09:00:00', 'zumba'),
(18, '2022-11-07 14:31:00', 'somo'),
(19, '2022-09-21 09:00:00', 'zumba'),
(20, '1970-01-01 15:12:15', 'takwondo');

-- --------------------------------------------------------

--
-- Table structure for table `search`
--

CREATE TABLE `search` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `Nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Adress` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Date_nais` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `Nom`, `Prenom`, `Adress`, `Date_nais`, `role`, `email`, `password`) VALUES
(1, 'Chaabene', 'Oussema', 'ariana', '25/12/1999', 'client', 'oussema', 'oussema'),
(2, 'aeqdda', 'dazdaz', 'dazda', 'dzadaz', 'admin', 'dzada', 'dazdaz'),
(3, 'BENCHIEKH', 'LEILA', 'ARIANA', '23/12/2022', 'client', 'LEILA', 'LEILA'),
(4, 'abdellaoui', 'hedi', 'rue tunis', '21/03/1999', 'client', 'hadhoud', 'abdou'),
(5, 'ghbontny', 'ahmed', 'laaouina', '19/05/1990', 'admin', 'laouina', 'brothers'),
(6, 'sami', 'abdelkarim', 'azerty', '05/12/1998', 'Client', 'sami.abdelkarim@esprit.tn', 'sami123456'),
(7, 'sami', 'abdelkarim', 'azerty', '05/12/1998', 'coach', 'sami@esprit.tn', '$argon2id$v=19$m=65536,t=4,p=1$Zy9QejhVcklDektwaFpOWg$IPSS65hMOiQ1uf8erPqieujbQtjgIwja3EGLXuyASGM'),
(8, 'jidahkll!', 'dzid nuao', 'azerty', 'dpaou,fyaeui', 'Client', 'samiabdlerkim98@gmail.com', '123456789'),
(9, 'sami', 'abdelkarim', 'azerty', '05/12/1998', 'ROLE_USER', 'samnni@esprit.tn', '$argon2id$v=19$m=65536,t=4,p=1$WXJHemVhZnNBdEhSS2p4Uw$EoUmC30woBVUi4vjiOWTB8IkPeU/8XnOjCy+AnuU8BM'),
(10, 'l3ati', 'abd', 'azerty', '22/10/2022', 'coach', 'sami.abdelkrim@isimg.tn', '123456789'),
(11, 'ouss', 'aezert', 'fuztenfjkay', '20/12/2022', 'coach', 'leila.bencheikh@esprit.tn', '123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `carac_sport`
--
ALTER TABLE `carac_sport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_carac_user` (`user`);

--
-- Indexes for table `categorieabo`
--
ALTER TABLE `categorieabo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_E6262FF4F1D74413` (`abonnement_id`);

--
-- Indexes for table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`id_emp`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_even`),
  ADD KEY `id_salle` (`id_salle`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`idEven`);

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`id_parti`),
  ADD KEY `ev` (`idEvent`),
  ADD KEY `us` (`id_user`);

--
-- Indexes for table `payement`
--
ALTER TABLE `payement`
  ADD PRIMARY KEY (`id_pay`),
  ADD KEY `ab_id` (`ab_id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `planning_client`
--
ALTER TABLE `planning_client`
  ADD PRIMARY KEY (`id_p`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_seance` (`id_seance`);

--
-- Indexes for table `planning_coach`
--
ALTER TABLE `planning_coach`
  ADD PRIMARY KEY (`id_p`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_seance` (`id_seance`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reser`),
  ADD KEY `id_even` (`id_even`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_salle` (`id_salle`);

--
-- Indexes for table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id_salle`);

--
-- Indexes for table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`id_seance`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `Login` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employe`
--
ALTER TABLE `employe`
  MODIFY `id_emp` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_even` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `idEven` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `participant`
--
ALTER TABLE `participant`
  MODIFY `id_parti` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `payement`
--
ALTER TABLE `payement`
  MODIFY `id_pay` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `planning_client`
--
ALTER TABLE `planning_client`
  MODIFY `id_p` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `planning_coach`
--
ALTER TABLE `planning_coach`
  MODIFY `id_p` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `salle`
--
ALTER TABLE `salle`
  MODIFY `id_salle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `seance`
--
ALTER TABLE `seance`
  MODIFY `id_seance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681EA0123F6C` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`);

--
-- Constraints for table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `ev` FOREIGN KEY (`idEvent`) REFERENCES `events` (`idEven`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `us` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payement`
--
ALTER TABLE `payement`
  ADD CONSTRAINT `FK_B20A78856A5F8A4A` FOREIGN KEY (`ab_id`) REFERENCES `abonnement` (`id`),
  ADD CONSTRAINT `FK_B20A78856B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `planning_client`
--
ALTER TABLE `planning_client`
  ADD CONSTRAINT `FK_A765263A6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `FK_A765263AF94A48E3` FOREIGN KEY (`id_seance`) REFERENCES `seance` (`id_seance`);

--
-- Constraints for table `planning_coach`
--
ALTER TABLE `planning_coach`
  ADD CONSTRAINT `FK_AEF5D7536B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `FK_AEF5D753F94A48E3` FOREIGN KEY (`id_seance`) REFERENCES `seance` (`id_seance`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C8495544600370` FOREIGN KEY (`id_even`) REFERENCES `evenement` (`id_even`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_42C849556B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `FK_42C84955A0123F6C` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
