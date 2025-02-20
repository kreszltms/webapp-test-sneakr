-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Gép: localhost:3306
-- Létrehozás ideje: 2025. Feb 19. 22:11
-- Kiszolgáló verziója: 5.7.24
-- PHP verzió: 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `sneakr`
--
CREATE DATABASE IF NOT EXISTS `sneakr` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `sneakr`;

DELIMITER $$
--
-- Eljárások
--
DROP PROCEDURE IF EXISTS `deleteShoes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteShoes` (IN `idIN` INT(11))   DELETE FROM cipok WHERE cipok.id = idIN$$

DROP PROCEDURE IF EXISTS `deleteUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `userId` INT)   BEGIN
    DELETE FROM userek WHERE id = userId;
    SELECT 'Felhasználó sikeresen törölve.' AS message;
END$$

DROP PROCEDURE IF EXISTS `getAllResellShoesData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllResellShoesData` ()   SELECT * FROM resell_cipok$$

DROP PROCEDURE IF EXISTS `getAllShoes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllShoes` ()   SELECT * FROM cipok$$

DROP PROCEDURE IF EXISTS `getAllShoesData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllShoesData` ()   SELECT * FROM cipok$$

DROP PROCEDURE IF EXISTS `getAllUsers`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllUsers` ()   SELECT * FROM userek$$

DROP PROCEDURE IF EXISTS `getShoesByAir`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getShoesByAir` ()   SELECT cipok.nev AS Cipők, cipok.ar AS Ár, cipok.img AS Kép
FROM cipok
WHERE cipok.nev LIKE Concat('%','Air','%')$$

DROP PROCEDURE IF EXISTS `getShoesDetails`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getShoesDetails` (IN `tipusIN` ENUM('férfi','női','uniszex'))   SELECT cipok.nev AS Cipők, markak.nev AS MárkaNevek, nemek.tipus AS Típusok, cipok.img AS Kép FROM `cipok` INNER JOIN `nemek` ON `cipok`.`nem_id` = `nemek`.`id` INNER JOIN `markak` ON `cipok`.`marka_id` = `markak`.`id` WHERE nemek.tipus = tipusIN$$

DROP PROCEDURE IF EXISTS `getShoesNamePrice`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getShoesNamePrice` ()   SELECT `nev` AS Cipők, `ar` AS Ár, cipok.img AS Kép FROM `cipok`$$

DROP PROCEDURE IF EXISTS `getUserById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserById` (IN `userId` INT)   BEGIN
    SELECT * FROM userek WHERE id = userId;
END$$

DROP PROCEDURE IF EXISTS `isUserExists`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `isUserExists` (IN `emailIN` VARCHAR(255), OUT `resultOUT` BOOLEAN)   SELECT EXISTS(
        SELECT 1 FROM userek WHERE userek.email = emailIN
    ) INTO resultOUT$$

DROP PROCEDURE IF EXISTS `listNewShoes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listNewShoes` (IN `tipusIN` ENUM('férfi','női','uniszex'))   SELECT cipok.nev AS Cipők, markak.nev AS MárkaNevek, nemek.tipus AS Típusok, cipok.allapot AS Állapot, cipok.img AS Kép FROM `cipok` INNER JOIN `nemek` ON `cipok`.`nem_id` = `nemek`.`id` INNER JOIN `markak` ON `cipok`.`marka_id` = `markak`.`id` WHERE nemek.tipus = tipusIN AND cipok.allapot = "új"$$

DROP PROCEDURE IF EXISTS `listShoesPriceASC`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listShoesPriceASC` ()   SELECT `nev` AS Cipők, `ar` AS Ár, cipok.img AS Kép FROM `cipok` ORDER BY Ár ASC$$

DROP PROCEDURE IF EXISTS `listShoesPriceDESC`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listShoesPriceDESC` ()   SELECT `nev` AS Cipők, `ar` AS Ár, cipok.img AS Kép FROM `cipok` ORDER BY Ár DESC$$

DROP PROCEDURE IF EXISTS `listUsedShoes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `listUsedShoes` (IN `tipusIN` ENUM('férfi','női','uniszex'))   SELECT cipok.nev AS Cipők, markak.nev AS MárkaNevek, nemek.tipus AS Típusok, cipok.allapot AS Állapot, cipok.img AS Kép FROM `cipok` INNER JOIN `nemek` ON `cipok`.`nem_id` = `nemek`.`id` INNER JOIN `markak` ON `cipok`.`marka_id` = `markak`.`id` WHERE nemek.tipus = tipusIN AND cipok.allapot = "használt"$$

DROP PROCEDURE IF EXISTS `login`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login` (IN `emailIN` VARCHAR(255), IN `passwordIN` VARCHAR(255))   SELECT * FROM userek WHERE userek.email = emailIN AND userek.jelszo = passwordIN$$

DROP PROCEDURE IF EXISTS `registerUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registerUser` (IN `nevIN` VARCHAR(255), IN `emailIN` VARCHAR(255), IN `jelszoIN` VARCHAR(255))   INSERT INTO `userek` (`id`, `nev`, `email`, `jelszo`, `admin`) VALUES (NULL, nevIN, emailIN, jelszoIN, "nem")$$

DROP PROCEDURE IF EXISTS `searchShoeByBrand`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchShoeByBrand` (IN `markaIN` VARCHAR(255))   SELECT cipok.nev AS Cipők, markak.nev AS Márka, cipok.ar AS Ár, cipok.img AS Kép
FROM cipok INNER JOIN markak ON cipok.marka_id = markak.id
WHERE markak.nev LIKE Concat('%',markaIN,'%')$$

DROP PROCEDURE IF EXISTS `searchShoeByName`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchShoeByName` (IN `cipoIN` VARCHAR(255))   SELECT cipok.nev AS Cipők, markak.nev AS Márka, cipok.ar AS Ár, cipok.img AS Kép
FROM cipok INNER JOIN markak ON cipok.marka_id = markak.id
WHERE cipok.nev LIKE Concat('%',cipoIN,'%')$$

DROP PROCEDURE IF EXISTS `updateShoe`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateShoe` (IN `idIN` INT(11), IN `nevIN` VARCHAR(255), IN `markaIN` VARCHAR(100), IN `nemIN` VARCHAR(100), IN `allapotIN` VARCHAR(100), IN `meretIN` INT(2), IN `arIN` FLOAT, IN `imgIN` TEXT)   UPDATE cipok
SET cipok.nev = nevIN, 
cipok.marka = markaIN,
cipok.nem = nemIN,
cipok.allapot = allapotIN,
cipok.meret = meretIN,
cipok.ar = arIN,
cipok.img = imgIN
WHERE cipok.id = idIN$$

DROP PROCEDURE IF EXISTS `uploadResellShoes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `uploadResellShoes` (IN `resell_nevIN` VARCHAR(255), IN `resell_markaIN` VARCHAR(100), IN `resell_nemIN` VARCHAR(100), IN `resell_allapotIN` VARCHAR(100), IN `resell_meretIN` INT(2), IN `resell_arIN` FLOAT, IN `resell_imgIN` TEXT, IN `resell_userIdIN` INT(11))   INSERT INTO `resell_cipok` (`id`, `nev`, `marka`, `nem`, `allapot`, `meret`,`ar`,`img`,`user_id`) VALUES (NULL, resell_nevIN, resell_markaIN,            resell_nemIN,resell_allapotIN, resell_meretIN, resell_arIN,resell_imgIN, resell_userIdIN)$$

DROP PROCEDURE IF EXISTS `uploadShoes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `uploadShoes` (IN `nevIN` VARCHAR(255), IN `markaIN` VARCHAR(100), IN `nemIN` VARCHAR(100), IN `allapotIN` VARCHAR(100), IN `meretIN` INT(2), IN `arIN` FLOAT, IN `imgIN` TEXT)   INSERT INTO `cipok` (`id`, `nev`, `marka`, `nem`, `allapot`, `meret`,`ar`,`akcio_id`,`exkluziv_id`,`ujdonsag_id`,`img`) VALUES (NULL, nevIN, markaIN,nemIN, allapotIN, meretIN, arIN, NULL, NULL, NULL, imgIN)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `akciok`
--

DROP TABLE IF EXISTS `akciok`;
CREATE TABLE `akciok` (
  `id` int(11) NOT NULL,
  `tipus` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `akciok`
--

INSERT INTO `akciok` (`id`, `tipus`) VALUES
(1, '50% kedvezmény'),
(2, '20% kedvezmény'),
(3, 'Ingyenes szállítás');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cipok`
--

DROP TABLE IF EXISTS `cipok`;
CREATE TABLE `cipok` (
  `id` int(11) NOT NULL,
  `nev` varchar(255) DEFAULT NULL,
  `marka` varchar(100) DEFAULT NULL,
  `nem` varchar(100) DEFAULT NULL,
  `allapot` varchar(100) DEFAULT NULL,
  `meret` int(2) DEFAULT NULL,
  `ar` float DEFAULT NULL,
  `akcio_id` int(11) DEFAULT NULL,
  `exkluziv_id` int(11) DEFAULT NULL,
  `ujdonsag_id` int(11) DEFAULT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `cipok`
--

INSERT INTO `cipok` (`id`, `nev`, `marka`, `nem`, `allapot`, `meret`, `ar`, `akcio_id`, `exkluziv_id`, `ujdonsag_id`, `img`) VALUES
(1, 'Air Max 1 \'86\nBig Bubble Sport Red', 'Nike', 'Férfi', 'új', 39, 25000, 1, 1, 1, 'https://i.imgur.com/fmF3v1t.png'),
(2, 'Ultraboost Multi-Color Heel Primeknit', 'Adidas', 'Nő', 'használt', 40, 15000, 2, NULL, NULL, 'https://i.imgur.com/PEpt7Uf.png'),
(3, 'Puma Mayze Flutur', 'Puma', 'Férfi', 'új', 42, 20000, NULL, 2, 2, 'https://i.imgur.com/RleDPg9.png'),
(4, 'Sacai Vaporwaffle Black Gum', 'Nike', 'Nő', 'új', 45, 18000, NULL, NULL, 1, 'https://i.imgur.com/NRKy0uh.png'),
(5, 'Air Jordan 4 Infrared', 'Jordan', 'Férfi', 'új', 42, 69900, NULL, NULL, NULL, 'https://i.imgur.com/CLQyTre.png'),
(6, 'Air Jordan 3 Retro Fire Red', 'Jordan', 'Férfi', 'használt', 45, 45000, 3, NULL, NULL, 'https://i.imgur.com/OS86MVh.png'),
(7, 'Kobe 6 Protro\nReverse Grinch', 'Nike', 'Férfi', 'új', 45, 74900, NULL, NULL, NULL, 'https://i.imgur.com/Pc7x47O.png'),
(8, 'Yeezy Boost 350 V2 Carbon', 'Yeezy', 'Nő', 'új', 42, 53900, NULL, NULL, NULL, 'https://i.imgur.com/S4EMLZq.png'),
(9, 'Off-White Dunk Low Lot 24/50', 'Nike', 'Férfi', 'új', 40, 45900, NULL, NULL, NULL, 'https://i.imgur.com/fadLkYw.png'),
(10, 'Jordan 5 Retro\nUNC University Blue', 'Reebok', 'Nő', 'használt', 45, 61900, NULL, NULL, NULL, 'https://i.imgur.com/BRLK0se.png'),
(11, 'LaMelo Ball LaFrancé Amour Moment', 'Puma', 'Férfi', 'új', 40, 29900, NULL, NULL, NULL, 'https://i.imgur.com/5qm2yGQ.png'),
(12, 'Air Jordan 1 High OG Heritage', 'Jordan', 'Férfi', 'új', 42, 112900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/TruetoSole-AirJordan1HighOGHeritage-555088-161-01_1000x600.png?v=1649667929'),
(13, 'Boost 350 V2 Core Black Red', 'Yeezy', 'Nő', 'új', 40, 129900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-AdidasYeezyBoost350V2CoreBlackRed-_BY9612_-01_1000x600.png?v=1659452309'),
(14, 'New Balance 550 White Grey', 'New Balance', 'Férfi', 'új', 45, 65900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-New-Balance-550-Sea-White-Grey-BB550PB1-01_1000x600.png?v=1629123000'),
(15, 'New Balance 1906D Protection Pack Black\n', 'New Balance', 'Nő', 'új', 42, 94900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/4353790797_1000x600.png?v=1674661131'),
(16, 'Travis Scott Air Max', 'Travis Scott', 'Nő', 'új', 40, 115000, NULL, NULL, 2, 'https://balazskicks.com/cdn/shop/products/nike-air-max-1-travis-scott-baroque-brown-1-1000.png?v=1654189942&width=2048'),
(17, 'Nike Air Force 1 Low \'07 Triple White', 'Nike', 'Nő', 'új', 43, 75900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-Air-Force-1-Low-07-White-on-White-_315122-111_-01_1000x600.png?v=1623668489'),
(18, 'Campus 80s South Park Towelie', 'Adidas', 'Nő', 'használt', 41, 49900, NULL, NULL, NULL, 'https://i.imgur.com/pawLaNC.png'),
(19, 'Air Jordan 4 Retro Red Cement\n', 'Jordan', 'Férfi', 'új', 45, 58900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/files/AirJordan4RetroRedCement-1_1000x600.png?v=1694525195'),
(20, 'Nike Air Max 1 SP Concepts Mellow', 'Nike', 'Nő', 'új', 40, 41900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/TruetoSole-NikeAirMax1SPConceptsMellow-DN1803-300-01_1000x600.png?v=1648220973'),
(21, 'New Balance 550 Syracuse', 'New Balance', 'Nő', 'használt', 39, 32900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-New-Balance-550-Orange-BB550HG1-01_1000x600.png?v=1633668382'),
(22, 'Air Jordan Retro 4 Black White', 'Jordan', 'Férfi', 'új', 45, 122900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/TruetoSole-AirJordan4RetroMilitaryBlack-DH6927-111-01_1000x600.png?v=1652364461'),
(23, 'Yeezy Slide Pure', 'Yeezy', 'Férfi', 'új', 44, 36900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-AdidasYeezySlideBone_2022Restock_FZ5897_-01_1000x600.png?v=1664928022'),
(24, 'New Balance 550', 'New Balance', 'Férfi', 'használt', 41, 52900, NULL, NULL, NULL, 'https://balazskicks.com/cdn/shop/products/image_3317acb1-f13b-43ce-81b4-3c511d7e87f7.png?v=1702321424&width=2048'),
(25, 'New Balance 1906R\nKith Black', 'New Balance', 'Nő', 'új', 45, 109900, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/files/NewBalance1906RKithBlack_1_1000x600.png?v=1710974215'),
(26, 'Nike SB Dunk Low Travis Scott', 'Travis Scott', 'Férfi', 'új', 45, 129000, NULL, NULL, NULL, 'https://en.grailpoint.com/wp-content/uploads/2024/01/nike-sb-dunk-low-travis-scott.jpg'),
(27, 'Nike Air Max 1 Premium\nShima Shima', 'Nike', 'Férfi', 'új', 44, 75990, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/NikeAirMax1PremiumShimaShima-1_1000x600.png?v=1679419253'),
(28, 'Nike Dunk Low Purple Pulse', 'Nike', 'Női', 'új', 38, 69990, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-Nike-Dunk-Low-Purple-Pulse-DM9467-500-01_1000x600.png?v=1627397078'),
(29, 'Nike Dunk Low Panda Retro Black White', 'Nike', 'Férfi', 'használt', 45, 55499, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-Nike-Dunk-Low-Retro-Black-White-01_42371a5e-7e8a-48c3-8854-a3bbdeef87b6_1000x600.png?v=1653054125'),
(30, 'adidas Yeezy 500 \'Utility Black\'', 'Yeezy', 'Női', 'használt', 38, 48990, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/true-to-sole-adidas-yeezy-500-utility-black-01_1000x600.png?v=1620652763'),
(31, 'Air Jordan 1 Low Travis Scott Reverse Mocha', 'Travis Scott', 'Férfi', 'használt', 45, 754990, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/TruetoSole-AirJordan1LowTravisScottReverseMocha-DM7866-162-01_1000x600.png?v=1658766234'),
(32, 'Air Jordan 1 Retro High Dark Mocha', 'Travis Jordan', 'Férfi', 'használt', 42, 329000, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/555088-105_1_2000x1200.png?v=1606247460'),
(33, 'Air Jordan 6 Retro Travis Scott British Khaki', 'Travis Scott', 'Férfi', 'új', 45, 249000, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-Air-Jordan-6-Retro-Travis-Scott-British-Khaki-DH0690-200-01_1000x600.png?v=1622051132'),
(40, 'Nike Dunk Low SP Kentucky', 'Nike', 'Férfi', 'Új', 47, 67990, NULL, NULL, NULL, 'https://www.truetosole.hu/cdn/shop/products/True-to-Sole-NikeDunkLowSPKentucky_CU1726-100_-01_1000x600.png?v=1667928660');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `exkluzivok`
--

DROP TABLE IF EXISTS `exkluzivok`;
CREATE TABLE `exkluzivok` (
  `id` int(11) NOT NULL,
  `tipus` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `exkluzivok`
--

INSERT INTO `exkluzivok` (`id`, `tipus`) VALUES
(1, 'Limitált kiadás'),
(2, 'Különleges színkombináció'),
(3, 'Exkluzív anyag');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kiegeszitok`
--

DROP TABLE IF EXISTS `kiegeszitok`;
CREATE TABLE `kiegeszitok` (
  `id` int(11) NOT NULL,
  `nev` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `kiegeszitok`
--

INSERT INTO `kiegeszitok` (`id`, `nev`) VALUES
(1, 'Sneaker Shield'),
(2, 'Cipőálpolási termékek'),
(3, 'Fűzők'),
(4, 'Ajándékutalványok');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kuponkodok`
--

DROP TABLE IF EXISTS `kuponkodok`;
CREATE TABLE `kuponkodok` (
  `id` int(11) NOT NULL,
  `kod` varchar(255) DEFAULT NULL,
  `kedvezmeny_szazalek` int(11) DEFAULT NULL,
  `ervenyes_tol` date DEFAULT NULL,
  `ervenyes_ig` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `kuponkodok`
--

INSERT INTO `kuponkodok` (`id`, `kod`, `kedvezmeny_szazalek`, `ervenyes_tol`, `ervenyes_ig`) VALUES
(1, '10PERCENTOFF', 10, '2024-10-07', '2024-10-31');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `lakcimek`
--

DROP TABLE IF EXISTS `lakcimek`;
CREATE TABLE `lakcimek` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `orszag` varchar(255) DEFAULT NULL,
  `varos` varchar(255) DEFAULT NULL,
  `iranyitoszam` varchar(255) DEFAULT NULL,
  `utca` varchar(255) DEFAULT NULL,
  `hazszam` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `lakcimek`
--

INSERT INTO `lakcimek` (`id`, `user_id`, `orszag`, `varos`, `iranyitoszam`, `utca`, `hazszam`) VALUES
(1, 1, 'Magyarország', 'Budapest', '1011', 'Vár utca', '12'),
(2, 2, 'Magyarország', 'Szeged', '6720', 'Kárász utca', '5'),
(3, 3, 'Magyarország', 'Debrecen', '4026', 'Nagyerdei körút', '24');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendelesek`
--

DROP TABLE IF EXISTS `rendelesek`;
CREATE TABLE `rendelesek` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `szallitasi_cim_id` int(11) DEFAULT NULL,
  `rendeles_datum` date DEFAULT NULL,
  `szallitas_id` int(11) DEFAULT NULL,
  `kuponkod_id` int(11) DEFAULT NULL,
  `rendeles_allapot` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendeles_tetelek`
--

DROP TABLE IF EXISTS `rendeles_tetelek`;
CREATE TABLE `rendeles_tetelek` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `cipok_id` int(11) DEFAULT NULL,
  `mennyiseg` int(11) DEFAULT NULL,
  `ar` float DEFAULT NULL,
  `rendeles_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `resell_cipok`
--

DROP TABLE IF EXISTS `resell_cipok`;
CREATE TABLE `resell_cipok` (
  `id` int(11) NOT NULL,
  `nev` varchar(255) DEFAULT NULL,
  `marka` varchar(100) DEFAULT NULL,
  `nem` varchar(100) DEFAULT NULL,
  `allapot` varchar(100) DEFAULT NULL,
  `meret` int(2) DEFAULT NULL,
  `ar` float DEFAULT NULL,
  `img` text NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `resell_cipok`
--

INSERT INTO `resell_cipok` (`id`, `nev`, `marka`, `nem`, `allapot`, `meret`, `ar`, `img`, `user_id`) VALUES
(1, 'Air Max 90', 'Nike', 'Férfi', 'Új', 39, 29990, 'https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/a7f07bf7-7896-48c7-b53d-ab5daf86f84e/NIKE+AIR+MAX+EXCEE.png', 61),
(2, 'Ultraboost ', 'Adidas', 'Nő', 'Használt', 40, 15000, 'https://www.shooos.hu/media/catalog/product/cache/5/image/1350x778/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas-ultraboost-22-w-gx55911.jpg', 62),
(3, 'Smash V2 Classic Leather', 'Puma', 'Férfi', 'Új', 42, 20000, 'https://shoecity.com/cdn/shop/products/0450209_01_1000x.jpg?v=1674060650', 6),
(4, 'Chuck Taylor All Star', 'Converse', 'Nő', 'Új', 45, 18000, 'https://dr9l7gb9cebpv.cloudfront.net/media/catalog/product/cache/2774d42b8e55ba58cfe3fc9632392955/m/9/m9160_a_107x1.jpg', 62),
(5, 'Jumpman MVP', 'Jordan', 'Férfi', 'Új', 42, 69900, 'https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/6ede48e2-7cfa-4a17-8ccf-f0ae3f851a46/JORDAN+MVP.png', 4),
(6, 'True Flight', 'Jordan', 'Férfi', 'Használt', 45, 45000, 'https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco,u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/69d62417-8464-4f98-9489-efafa96de6b1/JORDAN+TRUE+FLIGHT.png', 4),
(7, 'Air Zoom Alpha', 'Nike', 'Férfi', 'Új', 45, 74900, 'https://cdn-images.farfetch-contents.com/13/86/15/44/13861544_21694244_1000.jpg', 5),
(8, 'Classic Stan', 'Adidas', 'Nő', 'Új', 42, 53900, 'https://photos6.spartoo.hu/photos/188/18898694/adidas-Originals-STAN-SMITH-18898694_1200_A.jpg', 62),
(9, 'Velocity Nitro 3', 'Puma', 'Férfi', 'Új', 40, 45900, 'https://img01.ztat.net/article/spp-media-p1/d629e4e5ae284e0fbe6cec2ab0138732/4d1dce999ad248ed8d539805e59bd9ab.jpg?imwidth=1800&filter=packshot', 61),
(10, 'Legacy', 'Reebok', 'Nő', 'Használt', 45, 61900, 'https://www.shooos.hu/media/catalog/product/cache/5/image/1350x778/9df78eab33525d08d6e5fb8d27136e95/h/6/h686511.jpg', 62),
(11, 'El Distrito 2.0', 'Converse', 'Férfi', 'Új', 40, 29900, 'https://i.sportisimo.com/products/images/1944/1944552/700x700/converse-el-distrito-2-0_3.jpeg', 4),
(12, 'Fly', 'Jordan', 'Férfi', 'Új', 42, 112900, 'https://ro.basketzone.net/zdjecia/2018/04/11/304/49/NIKE_AIR_JORDAN_FLY_LOCKDOWN_BLACK_TECH_GREY_k.png', 5),
(13, 'Nike Dunk Low Retro', 'Nike', 'Férfi', 'Használt', 45, 55499, 'https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/b1bcbca4-e853-4df7-b329-5be3c61ee057/NIKE+DUNK+LOW+RETRO.png', 6),
(14, 'Nike Dunk Low Retro', 'Nike', 'Férfi', 'használt', 45, 55499, 'https://static.nike.com/a/images/t_PDP_936_v1/f_auto,q_auto:eco/b1bcbca4-e853-4df7-b329-5be3c61ee057/NIKE+DUNK+LOW+RETRO.png', 62),
(15, 'AIR JORDAN 1 RETRO LOW OG CANARY YELLOW', 'Travis Scott', 'Férfi', 'használt', 45, 255499, 'https://www.rdrop.hu/cdn/shop/files/air-jordan-1-retro-low-og-sp-travis-scott-canary-yellow-rdrop-262965.png?v=1720350714', 62),
(16, 'New Balance 550', 'New Balance', 'Férfi', 'Új', 41, 49990, 'https://balazskicks.com/cdn/shop/products/image_3317acb1-f13b-43ce-81b4-3c511d7e87f7.png?v=1702321424&width=2048', 5);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `shopping_session`
--

DROP TABLE IF EXISTS `shopping_session`;
CREATE TABLE `shopping_session` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `session_start` datetime DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `cipok_id` int(11) DEFAULT NULL,
  `mennyiseg` int(11) DEFAULT NULL,
  `ar` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szallitasok`
--

DROP TABLE IF EXISTS `szallitasok`;
CREATE TABLE `szallitasok` (
  `id` int(11) NOT NULL,
  `tipus` varchar(255) DEFAULT NULL,
  `ar` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `ujdonsagok`
--

DROP TABLE IF EXISTS `ujdonsagok`;
CREATE TABLE `ujdonsagok` (
  `id` int(11) NOT NULL,
  `tipus` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `ujdonsagok`
--

INSERT INTO `ujdonsagok` (`id`, `tipus`) VALUES
(1, 'Új kollekció'),
(2, '2024-es modell');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `userek`
--

DROP TABLE IF EXISTS `userek`;
CREATE TABLE `userek` (
  `id` int(11) NOT NULL,
  `nev` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `jelszo` varchar(255) DEFAULT NULL,
  `admin` varchar(255) DEFAULT 'nem'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `userek`
--

INSERT INTO `userek` (`id`, `nev`, `email`, `jelszo`, `admin`) VALUES
(1, 'John Doe', 'john.doe@example.com', 'password123', 'nem'),
(2, 'Jane Smith', 'jane.smith@example.com', 'securepass', 'nem'),
(3, 'Admin User', 'admin@example.com', 'adminpass', 'igen'),
(4, 'Kiss Lajos', 'lajoskiss1@gmail.com', 'Alma123!', 'nem'),
(5, 'Péter Aladár', 'petialadar@gmail.com', 'petike12345', 'nem'),
(6, 'Nagy Zsombor', 'nagyzsombi@gmail.com', 'zsombika12345', 'nem'),
(61, 'Lajos Pal', 'lali@gmail.com', 'Jelszo123!', 'nem'),
(62, 'Nagy Emma', 'nagyemma@gmail.com', 'EmmaNagy2000!', 'nem');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `akciok`
--
ALTER TABLE `akciok`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `cipok`
--
ALTER TABLE `cipok`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_marka_id` (`marka`),
  ADD KEY `fk_nem_id` (`nem`),
  ADD KEY `fk_meret_id` (`meret`),
  ADD KEY `fk_akcio_id` (`akcio_id`),
  ADD KEY `fk_exkluziv_id` (`exkluziv_id`),
  ADD KEY `fk_ujdonsag_id` (`ujdonsag_id`),
  ADD KEY `meret_id` (`meret`);

--
-- A tábla indexei `exkluzivok`
--
ALTER TABLE `exkluzivok`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `kiegeszitok`
--
ALTER TABLE `kiegeszitok`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `kuponkodok`
--
ALTER TABLE `kuponkodok`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kod` (`kod`);

--
-- A tábla indexei `lakcimek`
--
ALTER TABLE `lakcimek`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- A tábla indexei `rendelesek`
--
ALTER TABLE `rendelesek`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `szallitasi_cim_id` (`szallitasi_cim_id`),
  ADD KEY `szallitas_id` (`szallitas_id`),
  ADD KEY `kuponkod_id` (`kuponkod_id`);

--
-- A tábla indexei `rendeles_tetelek`
--
ALTER TABLE `rendeles_tetelek`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `cipok_id` (`cipok_id`),
  ADD KEY `rendeles_id` (`rendeles_id`);

--
-- A tábla indexei `resell_cipok`
--
ALTER TABLE `resell_cipok`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- A tábla indexei `shopping_session`
--
ALTER TABLE `shopping_session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `cipok_id` (`cipok_id`);

--
-- A tábla indexei `szallitasok`
--
ALTER TABLE `szallitasok`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `ujdonsagok`
--
ALTER TABLE `ujdonsagok`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `userek`
--
ALTER TABLE `userek`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `akciok`
--
ALTER TABLE `akciok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `cipok`
--
ALTER TABLE `cipok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT a táblához `exkluzivok`
--
ALTER TABLE `exkluzivok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `kiegeszitok`
--
ALTER TABLE `kiegeszitok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `kuponkodok`
--
ALTER TABLE `kuponkodok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT a táblához `lakcimek`
--
ALTER TABLE `lakcimek`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT a táblához `rendelesek`
--
ALTER TABLE `rendelesek`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `rendeles_tetelek`
--
ALTER TABLE `rendeles_tetelek`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `resell_cipok`
--
ALTER TABLE `resell_cipok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT a táblához `shopping_session`
--
ALTER TABLE `shopping_session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `szallitasok`
--
ALTER TABLE `szallitasok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `ujdonsagok`
--
ALTER TABLE `ujdonsagok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `userek`
--
ALTER TABLE `userek`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `cipok`
--
ALTER TABLE `cipok`
  ADD CONSTRAINT `fk_akcio_id` FOREIGN KEY (`akcio_id`) REFERENCES `akciok` (`id`),
  ADD CONSTRAINT `fk_exkluziv_id` FOREIGN KEY (`exkluziv_id`) REFERENCES `exkluzivok` (`id`),
  ADD CONSTRAINT `fk_ujdonsag_id` FOREIGN KEY (`ujdonsag_id`) REFERENCES `ujdonsagok` (`id`);

--
-- Megkötések a táblához `lakcimek`
--
ALTER TABLE `lakcimek`
  ADD CONSTRAINT `lakcimek_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userek` (`id`);

--
-- Megkötések a táblához `rendelesek`
--
ALTER TABLE `rendelesek`
  ADD CONSTRAINT `rendelesek_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userek` (`id`),
  ADD CONSTRAINT `rendelesek_ibfk_2` FOREIGN KEY (`szallitasi_cim_id`) REFERENCES `lakcimek` (`id`),
  ADD CONSTRAINT `rendelesek_ibfk_3` FOREIGN KEY (`szallitas_id`) REFERENCES `szallitasok` (`id`),
  ADD CONSTRAINT `rendelesek_ibfk_4` FOREIGN KEY (`kuponkod_id`) REFERENCES `kuponkodok` (`id`);

--
-- Megkötések a táblához `rendeles_tetelek`
--
ALTER TABLE `rendeles_tetelek`
  ADD CONSTRAINT `rendeles_tetelek_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userek` (`id`),
  ADD CONSTRAINT `rendeles_tetelek_ibfk_2` FOREIGN KEY (`cipok_id`) REFERENCES `cipok` (`id`),
  ADD CONSTRAINT `rendeles_tetelek_ibfk_3` FOREIGN KEY (`rendeles_id`) REFERENCES `rendelesek` (`id`);

--
-- Megkötések a táblához `shopping_session`
--
ALTER TABLE `shopping_session`
  ADD CONSTRAINT `shopping_session_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userek` (`id`),
  ADD CONSTRAINT `shopping_session_ibfk_2` FOREIGN KEY (`cipok_id`) REFERENCES `cipok` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
