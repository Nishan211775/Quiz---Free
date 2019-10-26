-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2019 at 04:45 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quizapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` int(11) NOT NULL,
  `qid` int(11) NOT NULL,
  `answer` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `qid`, `answer`) VALUES
(5, 7, 'Kathmandu'),
(6, 7, 'Pokhara'),
(7, 7, 'Butuwal'),
(8, 7, 'Nepal'),
(9, 8, 'Order of Significance'),
(10, 8, 'Open Software'),
(11, 8, 'Operating System'),
(12, 8, 'Optical Sensor'),
(13, 9, 'Image file'),
(14, 9, 'Animation/movie file'),
(15, 9, 'Audio file'),
(16, 9, 'MS Office document'),
(17, 10, 'Canada'),
(18, 10, 'Sri Lanka'),
(19, 10, 'Zimbabwe'),
(20, 10, 'East Africa'),
(21, 11, 'Glamorgan'),
(22, 11, 'Leicestershire'),
(23, 11, 'Gloucestershire'),
(24, 11, 'Lancashire'),
(25, 12, '200'),
(26, 12, '206'),
(27, 12, '212'),
(28, 12, '218'),
(29, 13, '3 Years'),
(30, 13, '4 Years'),
(31, 13, '5 Years'),
(32, 13, '6 Years'),
(33, 14, 'english'),
(34, 14, 'malayalam'),
(35, 14, 'hindi'),
(36, 14, 'marathi'),
(37, 15, '42'),
(38, 15, '44'),
(39, 15, '46'),
(40, 15, '48'),
(41, 16, 'Influx'),
(42, 16, 'Home-coming'),
(43, 16, 'Return'),
(44, 16, 'Restoration');

-- --------------------------------------------------------

--
-- Table structure for table `correctanswer`
--

CREATE TABLE `correctanswer` (
  `id` int(11) NOT NULL,
  `questionid` int(11) NOT NULL,
  `answerid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `correctanswer`
--

INSERT INTO `correctanswer` (`id`, `questionid`, `answerid`) VALUES
(1, 7, 5),
(2, 8, 10),
(3, 9, 14),
(4, 10, 17),
(5, 11, 22),
(6, 12, 28),
(7, 13, 29),
(8, 14, 35),
(9, 15, 37),
(10, 16, 43);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `qdetails` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `qdetails`) VALUES
(8, '\'OS\' computer abbreviation usually means ?'),
(9, '.MOV extension refers usually to what kind of file?'),
(12, 'The number of already named bones in the human skeleton is'),
(15, 'The number of chromosomes in human body is'),
(14, 'The official language of the Government of India is'),
(13, 'The term of office of the UN Secretary-General is'),
(7, 'what is capital city of Nepal?'),
(16, 'what is the exact OPPOSITE of EXODUS'),
(11, 'Which county did Ravi Shastri play for?'),
(10, 'Which was the 1st non Test playing country to beat India in an international match?');

-- --------------------------------------------------------

--
-- Table structure for table `userhist`
--

CREATE TABLE `userhist` (
  `id` int(11) NOT NULL,
  `questionid` int(11) NOT NULL,
  `givenanswerid` int(11) NOT NULL,
  `isCorrect` tinyint(1) NOT NULL DEFAULT '0',
  `questionSet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userhist`
--

INSERT INTO `userhist` (`id`, `questionid`, `givenanswerid`, `isCorrect`, `questionSet`) VALUES
(43, 8, 5, 0, 1),
(44, 8, 5, 0, 2),
(45, 9, 12, 0, 2),
(46, 10, 15, 0, 2),
(47, 8, 6, 0, 3),
(48, 9, 12, 0, 3),
(49, 10, 15, 0, 3),
(50, 8, 5, 0, 4),
(51, 9, 12, 0, 4),
(52, 10, 13, 0, 4),
(53, 11, 18, 0, 4),
(54, 12, 23, 0, 4),
(55, 13, 28, 0, 4),
(56, 14, 30, 0, 4),
(57, 15, 35, 0, 4),
(58, 7, 5, 1, 5),
(59, 8, 10, 1, 5),
(60, 7, 7, 0, 6),
(61, 8, 11, 0, 6),
(62, 9, 16, 0, 6),
(63, 10, 19, 0, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `qid` (`qid`);

--
-- Indexes for table `correctanswer`
--
ALTER TABLE `correctanswer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `questionid` (`questionid`),
  ADD KEY `answerid` (`answerid`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `qdetails` (`qdetails`);

--
-- Indexes for table `userhist`
--
ALTER TABLE `userhist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `questionid` (`questionid`),
  ADD KEY `givenanswerid` (`givenanswerid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `correctanswer`
--
ALTER TABLE `correctanswer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `userhist`
--
ALTER TABLE `userhist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `questions` (`id`);

--
-- Constraints for table `correctanswer`
--
ALTER TABLE `correctanswer`
  ADD CONSTRAINT `correctanswer_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `questions` (`id`),
  ADD CONSTRAINT `correctanswer_ibfk_2` FOREIGN KEY (`answerid`) REFERENCES `answers` (`id`);

--
-- Constraints for table `userhist`
--
ALTER TABLE `userhist`
  ADD CONSTRAINT `userhist_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `questions` (`id`),
  ADD CONSTRAINT `userhist_ibfk_2` FOREIGN KEY (`givenanswerid`) REFERENCES `answers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
