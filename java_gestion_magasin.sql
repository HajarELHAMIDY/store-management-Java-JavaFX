-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 31, 2019 at 12:10 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_gestion_magasin`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `street` varchar(200) NOT NULL,
  `postalcode` int(11) NOT NULL,
  `city` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `street`, `postalcode`, `city`) VALUES
(1, '114, palastine hadri', 16200, 4),
(3, '144, palastine hadri', 16200, 4),
(4, 'tte casablanca', 20000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `name`) VALUES
(1, 'cih'),
(2, 'tijari');

-- --------------------------------------------------------

--
-- Table structure for table `cash`
--

CREATE TABLE `cash` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cash`
--

INSERT INTO `cash` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `nom`, `description`) VALUES
(0, 'bureau', 'bureau'),
(2, 'bds', 'ahshjab');

-- --------------------------------------------------------

--
-- Table structure for table `checkp`
--

CREATE TABLE `checkp` (
  `id` int(11) NOT NULL,
  `propr` varchar(50) NOT NULL,
  `num` varchar(50) NOT NULL,
  `banck` int(11) NOT NULL,
  `date_effet` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `checkp`
--

INSERT INTO `checkp` (`id`, `propr`, `num`, `banck`, `date_effet`) VALUES
(2, 'imad taoufiq', '63765376', 1, '2019-12-27'),
(4, 'imad taou', '6573928', 1, '2019-12-28'),
(5, 'fatima taoufiq', '65879834', 2, '2019-12-29'),
(6, 'imad', '5426768533', 1, '2019-12-28'),
(7, 'fatima taoufiq', '763219', 2, '2019-12-29');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `country` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `name`, `country`) VALUES
(1, 'Rabat', 1),
(2, 'Sale', 1),
(3, 'Casabanca', 1),
(4, 'Ouezzane', 1),
(5, 'Paris', 2),
(6, 'Brest', 2);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `idAddress` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `phone`, `email`, `idAddress`) VALUES
(1, 'comptoir', 'comptoir', '0523456789', 'comptoir@tte.ma', 0),
(2, 'taoufiq', 'imad', '0620191065', 'imad@gmail.com', 1),
(3, 'taoufiq', 'fatima', '0675928340', 'fatima@gmail.com', 3);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `name`) VALUES
(1, 'Maroc'),
(2, 'France');

-- --------------------------------------------------------

--
-- Table structure for table `draft`
--

CREATE TABLE `draft` (
  `id` int(11) NOT NULL,
  `nbDraft` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `rest` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `draft`
--

INSERT INTO `draft` (`id`, `nbDraft`, `date_debut`, `rest`) VALUES
(2, 4, '2019-12-28', 0);

-- --------------------------------------------------------

--
-- Table structure for table `itemdraft`
--

CREATE TABLE `itemdraft` (
  `id` int(11) NOT NULL,
  `montant` double NOT NULL,
  `date_prevue` date NOT NULL,
  `date_effectue` date NOT NULL,
  `idDraft` int(11) NOT NULL,
  `typePayement` varchar(50) NOT NULL,
  `idTypePayment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itemdraft`
--

INSERT INTO `itemdraft` (`id`, `montant`, `date_prevue`, `date_effectue`, `idDraft`, `typePayement`, `idTypePayment`) VALUES
(2, 5000, '2019-12-28', '2019-12-28', 2, 'Check', 4),
(3, 1000, '2019-12-30', '2019-12-31', 2, 'Cash', 1),
(4, 2000, '2020-01-02', '2020-01-03', 3, 'Check', 5),
(5, 600, '2019-12-28', '2019-12-28', 4, 'Check', 6),
(6, 2000, '2019-12-28', '2019-12-29', 2, 'Check', 7),
(7, 2000, '2020-01-02', '2020-01-02', 2, 'Cash', 2),
(8, 1000, '2020-01-04', '2020-01-05', 2, 'Cash', 3),
(9, 500, '2020-01-05', '2020-01-04', 2, 'Cash', 4),
(12, 3740, '2019-12-30', '2019-12-29', 2, 'Cash', 8);

-- --------------------------------------------------------

--
-- Table structure for table `lineorder`
--

CREATE TABLE `lineorder` (
  `id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `total` double NOT NULL,
  `idProduct` int(11) NOT NULL,
  `idOrder` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lineorder`
--

INSERT INTO `lineorder` (`id`, `quantite`, `total`, `idProduct`, `idOrder`) VALUES
(1, 7, 490, 4, 1),
(2, 2, 1400, 8, 1),
(3, 11, 6050, 11, 1),
(4, 12, 1440, 2, 3),
(5, 2, 240, 2, 2),
(6, 1, 600, 6, 1),
(7, 12, 8400, 8, 3),
(8, 6, 360, 3, 2),
(17, 12, 840, 4, 4),
(19, 12, 14400, 10, 4);

-- --------------------------------------------------------

--
-- Table structure for table `online`
--

CREATE TABLE `online` (
  `id` int(11) NOT NULL,
  `numCart` varchar(50) NOT NULL,
  `annee_fin` int(11) NOT NULL,
  `mois_fin` int(11) NOT NULL,
  `cvv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `online`
--

INSERT INTO `online` (`id`, `numCart`, `annee_fin`, `mois_fin`, `cvv`) VALUES
(1, '5673921342569832', 2025, 11, 563),
(6, '5673921342569832', 2025, 11, 563);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `total` double NOT NULL,
  `idClient` int(11) NOT NULL,
  `typeOrder` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `date`, `total`, `idClient`, `typeOrder`) VALUES
(1, '2019-12-18', 8540, 2, 2),
(2, '2019-12-19', 600, 2, 2),
(3, '2019-12-26', 9840, 2, 2),
(4, '2019-12-20', 15240, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `idOrder` int(11) NOT NULL,
  `idMode` int(11) NOT NULL,
  `mode` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `date`, `idOrder`, `idMode`, `mode`) VALUES
(1, '2019-12-27', 2, 2, 'Check'),
(2, '2019-12-27', 3, 1, 'Online'),
(4, '2019-12-28', 4, 2, 'Draft'),
(9, '2019-12-29', 1, 6, 'Online');

-- --------------------------------------------------------

--
-- Table structure for table `produits`
--

CREATE TABLE `produits` (
  `id` int(11) NOT NULL,
  `designation` varchar(50) NOT NULL,
  `prixAchat` double NOT NULL,
  `prixVente` double NOT NULL,
  `quantite` int(11) NOT NULL,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produits`
--

INSERT INTO `produits` (`id`, `designation`, `prixAchat`, `prixVente`, `quantite`, `category`) VALUES
(1, 'imprimanr22', 445.56, 500, 10, 0),
(2, 'produit88', 100.99, 120, 101, 0),
(3, 'imprimghanr', 55.66, 60, 68, 0),
(4, 'imprimghanr', 55.67, 70, -3, 0),
(6, 'imprimanr', 445.56, 600, 38, 0),
(8, 'new product 3', 585.66, 700, 9, 0),
(10, 'uuhjj', 988, 1200, 54, 0),
(11, 'jhgdhf', 456, 550, 140, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cash`
--
ALTER TABLE `cash`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkp`
--
ALTER TABLE `checkp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `draft`
--
ALTER TABLE `draft`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `itemdraft`
--
ALTER TABLE `itemdraft`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lineorder`
--
ALTER TABLE `lineorder`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `online`
--
ALTER TABLE `online`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `cash`
--
ALTER TABLE `cash`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `checkp`
--
ALTER TABLE `checkp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `draft`
--
ALTER TABLE `draft`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `itemdraft`
--
ALTER TABLE `itemdraft`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `lineorder`
--
ALTER TABLE `lineorder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `online`
--
ALTER TABLE `online`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `produits`
--
ALTER TABLE `produits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
