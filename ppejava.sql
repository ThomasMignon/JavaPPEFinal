-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 26 Janvier 2017 à 09:18
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ppejava`
--

-- --------------------------------------------------------

--
-- Structure de la table `attrcompetition`
--

CREATE TABLE `attrcompetition` (
  `id_candidat` int(11) NOT NULL,
  `id_competition` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `attrequipe`
--

CREATE TABLE `attrequipe` (
  `id_personne` int(11) NOT NULL,
  `id_equipe` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `candidats`
--

CREATE TABLE `candidats` (
  `id_candidat` int(11) NOT NULL,
  `id_personne` int(11) DEFAULT NULL,
  `id_equipe` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `deleted_at` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `candidats`
--

INSERT INTO `candidats` (`id_candidat`, `id_personne`, `id_equipe`, `nom`, `deleted_at`) VALUES
(2, 5, NULL, 'Limentour', NULL),
(3, NULL, 2, 'YoyoTeam', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `competitions`
--

CREATE TABLE `competitions` (
  `id_competition` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `nom` varchar(255) NOT NULL,
  `enequipe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `competitions`
--

INSERT INTO `competitions` (`id_competition`, `date`, `nom`, `enequipe`) VALUES
(1, '2012-12-21 00:00:00', 'Tournoi de pingpong', 0);

-- --------------------------------------------------------

--
-- Structure de la table `equipes`
--

CREATE TABLE `equipes` (
  `id_equipe` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `equipes`
--

INSERT INTO `equipes` (`id_equipe`) VALUES
(2);

-- --------------------------------------------------------

--
-- Structure de la table `personnes`
--

CREATE TABLE `personnes` (
  `id_personne` int(11) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `deleted_at` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personnes`
--

INSERT INTO `personnes` (`id_personne`, `prenom`, `mail`, `deleted_at`) VALUES
(5, 'Gaetan', 'gaetalim@gmail.com', NULL);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `attrcompetition`
--
ALTER TABLE `attrcompetition`
  ADD PRIMARY KEY (`id_candidat`,`id_competition`),
  ADD KEY `id_competition` (`id_competition`);

--
-- Index pour la table `attrequipe`
--
ALTER TABLE `attrequipe`
  ADD PRIMARY KEY (`id_personne`,`id_equipe`),
  ADD KEY `id_equipe` (`id_equipe`);

--
-- Index pour la table `candidats`
--
ALTER TABLE `candidats`
  ADD PRIMARY KEY (`id_candidat`),
  ADD KEY `id_personne` (`id_personne`),
  ADD KEY `id_equipe` (`id_equipe`);

--
-- Index pour la table `competitions`
--
ALTER TABLE `competitions`
  ADD PRIMARY KEY (`id_competition`);

--
-- Index pour la table `equipes`
--
ALTER TABLE `equipes`
  ADD PRIMARY KEY (`id_equipe`);

--
-- Index pour la table `personnes`
--
ALTER TABLE `personnes`
  ADD PRIMARY KEY (`id_personne`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `candidats`
--
ALTER TABLE `candidats`
  MODIFY `id_candidat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `competitions`
--
ALTER TABLE `competitions`
  MODIFY `id_competition` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `equipes`
--
ALTER TABLE `equipes`
  MODIFY `id_equipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `personnes`
--
ALTER TABLE `personnes`
  MODIFY `id_personne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
