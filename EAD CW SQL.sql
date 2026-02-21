-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 21, 2026 at 12:21 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smart_agri`
--

-- --------------------------------------------------------

--
-- Table structure for table `buyers`
--

CREATE TABLE `buyers` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `buyer_demands`
--

CREATE TABLE `buyer_demands` (
  `id` int(11) NOT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `crop_id` int(11) DEFAULT NULL,
  `requested_qty_kg` int(11) DEFAULT NULL,
  `quality_pref` varchar(10) DEFAULT NULL,
  `needed_by_date` date DEFAULT NULL,
  `status` enum('Open','Fulfilled','Cancelled') DEFAULT 'Open',
  `notes` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buyer_demands`
--

INSERT INTO `buyer_demands` (`id`, `buyer_id`, `crop_id`, `requested_qty_kg`, `quality_pref`, `needed_by_date`, `status`, `notes`, `created_at`) VALUES
(1, 4, 1, 500, 'A', '2024-03-20', 'Open', 'Bulk order', '2026-01-19 11:10:16'),
(2, 5, 1, 1800, 'A', '2026-04-01', 'Open', 'Miller request – 12% moisture max', '2026-01-21 06:00:44'),
(3, 6, 3, 350, 'A', '2026-02-14', 'Open', 'Retail – carrot size 16–20 cm', '2026-01-21 06:00:44'),
(4, 1, 4, 500, 'B', '2026-02-02', 'Open', 'Onion – tolerant to outer peel marks', '2026-01-21 06:00:44'),
(5, 2, 5, 900, 'A', '2026-03-10', 'Open', 'Mango – °Brix ≥ 14, export pack', '2026-01-21 06:00:44'),
(6, 4, 40, 1, 'A', '2026-02-10', 'Open', 'It should be ripe and sweet', '2026-02-05 15:42:40'),
(7, 4, 11, 100, 'A', '2027-01-10', 'Open', 'prediction fpor 2027', '2026-02-05 16:28:32'),
(8, 4, 22, 100, 'ANY', '2025-10-05', 'Open', '', '2026-02-10 05:15:46');

-- --------------------------------------------------------

--
-- Table structure for table `crops`
--

CREATE TABLE `crops` (
  `id` int(11) NOT NULL,
  `common_name` varchar(255) DEFAULT NULL,
  `variety` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `crops`
--

INSERT INTO `crops` (`id`, `common_name`, `variety`, `category`) VALUES
(1, 'Maize', 'Local-01', 'Cereal'),
(2, 'Tomatoes', 'Roma', 'Vegetable'),
(3, 'Green Beans', 'GB-01', 'Vegetable'),
(4, 'Tomatoes', 'Roma', 'Vegetable'),
(5, 'Tomatoes', 'Cherry', 'Vegetable'),
(6, 'Tomatoes', 'Beefsteak', 'Vegetable'),
(7, 'Carrots', 'Nantes', 'Vegetable'),
(8, 'Carrots', 'Danvers', 'Vegetable'),
(9, 'Rice', 'Basmati', 'Grain'),
(10, 'Rice', 'Jasmine', 'Grain'),
(11, 'Wheat', 'Hard Red Winter', 'Grain'),
(12, 'Wheat', 'Soft White', 'Grain'),
(13, 'Corn', 'Sweet Corn', 'Vegetable'),
(14, 'Corn', 'Field Corn', 'Grain'),
(15, 'Potatoes', 'Russet', 'Vegetable'),
(16, 'Potatoes', 'Red', 'Vegetable'),
(17, 'Lettuce', 'Romaine', 'Vegetable'),
(18, 'Lettuce', 'Iceberg', 'Vegetable'),
(19, 'Cabbage', 'Green', 'Vegetable'),
(20, 'Cabbage', 'Red', 'Vegetable'),
(21, 'Beans', 'Green Beans', 'Vegetable'),
(22, 'Beans', 'Kidney Beans', 'Legume'),
(23, 'Peas', 'Garden Peas', 'Vegetable'),
(24, 'Cucumbers', 'English', 'Vegetable'),
(25, 'Cucumbers', 'Pickling', 'Vegetable'),
(26, 'Peppers', 'Bell Pepper', 'Vegetable'),
(27, 'Peppers', 'Chili Pepper', 'Vegetable'),
(28, 'Onions', 'Yellow Onion', 'Vegetable'),
(29, 'Onions', 'Red Onion', 'Vegetable'),
(30, 'Garlic', 'Softneck', 'Vegetable'),
(31, 'Garlic', 'Hardneck', 'Vegetable'),
(32, 'Pumpkins', 'Sugar Pumpkin', 'Vegetable'),
(33, 'Squash', 'Butternut', 'Vegetable'),
(34, 'Squash', 'Zucchini', 'Vegetable'),
(35, 'Eggplant', 'Globe', 'Vegetable'),
(36, 'Spinach', 'Savoy', 'Vegetable'),
(37, 'Kale', 'Curly', 'Vegetable'),
(38, 'Broccoli', 'Calabrese', 'Vegetable'),
(39, 'Cauliflower', 'Snowball', 'Vegetable'),
(40, 'Apples', 'Fuji', 'Fruit'),
(41, 'Apples', 'Granny Smith', 'Fruit'),
(42, 'Oranges', 'Valencia', 'Fruit'),
(43, 'Oranges', 'Navel', 'Fruit'),
(44, 'Bananas', 'Cavendish', 'Fruit'),
(45, 'Grapes', 'Thompson Seedless', 'Fruit'),
(46, 'Grapes', 'Red Globe', 'Fruit'),
(47, 'Strawberries', 'Albion', 'Fruit'),
(48, 'Mangoes', 'Alphonso', 'Fruit'),
(49, 'Watermelon', 'Crimson Sweet', 'Fruit'),
(50, 'Pineapple', 'Smooth Cayenne', 'Fruit'),
(51, 'Papaya', 'Solo', 'Fruit'),
(52, 'Coffee', 'Arabica', 'Cash Crop'),
(53, 'Coffee', 'Robusta', 'Cash Crop'),
(54, 'Tea', 'Assam', 'Cash Crop'),
(55, 'Cotton', 'Upland Cotton', 'Cash Crop'),
(56, 'Sugarcane', 'Noble Cane', 'Cash Crop'),
(57, 'Soybeans', 'Round-Up Ready', 'Legume'),
(58, 'Peanuts', 'Runner', 'Legume'),
(59, 'Sunflowers', 'Oil Type', 'Oilseed');

-- --------------------------------------------------------

--
-- Table structure for table `crop_health_reports`
--

CREATE TABLE `crop_health_reports` (
  `id` int(11) NOT NULL,
  `season_id` int(11) DEFAULT NULL,
  `report_date` date DEFAULT NULL,
  `disease_type` varchar(255) DEFAULT NULL,
  `pest_issues` varchar(255) DEFAULT NULL,
  `fertilizer_used` varchar(255) DEFAULT NULL,
  `status` enum('Good','Warning','Critical') DEFAULT 'Good',
  `reported_by` int(11) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `crop_health_reports`
--

INSERT INTO `crop_health_reports` (`id`, `season_id`, `report_date`, `disease_type`, `pest_issues`, `fertilizer_used`, `status`, `reported_by`, `notes`, `created_at`) VALUES
(1, 1, '2024-02-15', 'None', 'No issues', 'Urea', 'Good', 3, 'No issues', '2026-01-19 11:10:16');

-- --------------------------------------------------------

--
-- Table structure for table `fields`
--

CREATE TABLE `fields` (
  `id` int(11) NOT NULL,
  `farmer_id` int(11) DEFAULT NULL,
  `field_code` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `area_hectares` decimal(10,2) DEFAULT NULL,
  `lat` decimal(10,6) DEFAULT NULL,
  `lng` decimal(10,6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fields`
--

INSERT INTO `fields` (`id`, `farmer_id`, `field_code`, `name`, `location`, `area_hectares`, `lat`, `lng`) VALUES
(1, 2, 'F-12', 'Field 12', 'Gampaha', 2.50, 7.089700, 80.014400),
(2, 2, 'F-07', 'Field 7', 'Gampaha', 2.00, 7.074400, 80.019800),
(3, 2, 'F-03', 'Field 3', 'Gampaha', 1.50, 7.071200, 80.012300),
(4, 1, 'F001', 'Field A', 'North Zone', 2.50, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `field_logs`
--

CREATE TABLE `field_logs` (
  `id` int(11) NOT NULL,
  `field_id` int(11) DEFAULT NULL,
  `activity` text DEFAULT NULL,
  `log_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `harvests`
--

CREATE TABLE `harvests` (
  `id` int(11) NOT NULL,
  `season_id` int(11) DEFAULT NULL,
  `harvest_date` date DEFAULT NULL,
  `actual_yield_kg` decimal(12,2) DEFAULT NULL,
  `available_qty_kg` decimal(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `harvests`
--
DELIMITER $$
CREATE TRIGGER `trg_harvests_qty_ins` BEFORE INSERT ON `harvests` FOR EACH ROW BEGIN
  IF NEW.actual_yield_kg <= 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Actual yield must be > 0';
  END IF;
  IF NEW.available_qty_kg < 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Available qty cannot be negative';
  END IF;
  IF NEW.available_qty_kg > NEW.actual_yield_kg THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Available qty cannot exceed actual yield';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `harvest_allocations`
--

CREATE TABLE `harvest_allocations` (
  `id` int(11) NOT NULL,
  `harvest_id` int(11) DEFAULT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `allocated_qty_kg` decimal(12,2) DEFAULT NULL,
  `allocation_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Triggers `harvest_allocations`
--
DELIMITER $$
CREATE TRIGGER `trg_allocations_cap_ins` BEFORE INSERT ON `harvest_allocations` FOR EACH ROW BEGIN
  DECLARE total_alloc DECIMAL(12,2);
  DECLARE available DECIMAL(12,2);

  SELECT IFNULL(SUM(allocated_qty_kg), 0) INTO total_alloc
  FROM harvest_allocations WHERE harvest_id = NEW.harvest_id;

  SELECT available_qty_kg INTO available
  FROM harvests WHERE id = NEW.harvest_id;

  IF (total_alloc + NEW.allocated_qty_kg) > available THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Allocated quantity exceeds available harvest';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `role_name`) VALUES
(2, 'ADMIN'),
(1, 'BUYER'),
(4, 'DISPATCH'),
(3, 'FARMER'),
(5, 'OFFICER');

-- --------------------------------------------------------

--
-- Table structure for table `seasons`
--

CREATE TABLE `seasons` (
  `id` int(11) NOT NULL,
  `season_name` varchar(100) NOT NULL,
  `crop_id` int(11) DEFAULT NULL,
  `field_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `expected_yield_kg` decimal(12,2) DEFAULT NULL,
  `status` enum('Planned','Active','Completed') DEFAULT 'Planned'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seasons`
--

INSERT INTO `seasons` (`id`, `season_name`, `crop_id`, `field_id`, `start_date`, `end_date`, `expected_yield_kg`, `status`) VALUES
(1, 'Maha 2024', 1, 1, '2024-09-15', '2025-02-15', 1000.00, 'Active'),
(2, 'Yala 2025', 2, 2, '2025-03-01', '2025-08-15', 500.00, 'Planned'),
(3, 'Inter-Monsoon 2024', 3, 3, '2024-04-01', '2024-05-31', NULL, 'Active'),
(4, 'Maha 2023', 4, 1, '2023-09-20', '2024-02-10', NULL, 'Active'),
(5, 'Maha 2024', 5, 2, '2024-09-15', '2025-02-15', NULL, 'Active'),
(6, 'Yala 2025', 6, 3, '2025-03-01', '2025-08-15', NULL, 'Active'),
(7, 'Inter-Monsoon 2024', 7, 1, '2024-04-01', '2024-05-31', NULL, 'Active'),
(8, 'Maha 2023', 8, 2, '2023-09-20', '2024-02-10', NULL, 'Active'),
(9, '2025 Season', NULL, 4, '2025-01-01', '2025-12-31', NULL, 'Planned');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password_hash` text DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `full_name`, `email`, `phone`, `password_hash`, `role_id`, `is_active`, `created_at`) VALUES
(1, 'Admin', 'admin@test.com', '0710000009', '1234', 1, 1, '2026-01-19 11:10:16'),
(2, 'Farmer', 'farmer@test.com', '0710000000', 'Farmer123', 2, 1, '2026-01-19 11:10:16'),
(3, 'Officer', 'officer@test.com', '0710000001', 'Office456', 3, 1, '2026-01-19 11:10:16'),
(4, 'Buyer', 'buyer@test.com', '0710000002', 'Buyer789', 4, 1, '2026-01-19 11:10:16');

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_crop_yield_vs_demand`
-- (See below for the actual view)
--
CREATE TABLE `v_crop_yield_vs_demand` (
`crop` varchar(255)
,`expected_kg` decimal(34,2)
,`actual_kg` decimal(34,2)
,`demand_kg` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_dashboard_crop_totals`
-- (See below for the actual view)
--
CREATE TABLE `v_dashboard_crop_totals` (
`crop` varchar(255)
,`total_expected_kg` decimal(34,2)
,`total_actual_kg` decimal(34,2)
,`total_available_kg` decimal(34,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_health_status_counts`
-- (See below for the actual view)
--
CREATE TABLE `v_health_status_counts` (
`status` enum('Good','Warning','Critical')
,`cnt` bigint(21)
);

-- --------------------------------------------------------

--
-- Structure for view `v_crop_yield_vs_demand`
--
DROP TABLE IF EXISTS `v_crop_yield_vs_demand`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_crop_yield_vs_demand`  AS SELECT `c`.`common_name` AS `crop`, sum(`s`.`expected_yield_kg`) AS `expected_kg`, ifnull(sum(`h`.`actual_yield_kg`),0) AS `actual_kg`, ifnull(sum(`d`.`requested_qty_kg`),0) AS `demand_kg` FROM (((`crops` `c` left join `seasons` `s` on(`s`.`crop_id` = `c`.`id`)) left join `harvests` `h` on(`h`.`season_id` = `s`.`id`)) left join `buyer_demands` `d` on(`d`.`crop_id` = `c`.`id` and `d`.`status` = 'Open')) GROUP BY `c`.`common_name` ;

-- --------------------------------------------------------

--
-- Structure for view `v_dashboard_crop_totals`
--
DROP TABLE IF EXISTS `v_dashboard_crop_totals`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_dashboard_crop_totals`  AS SELECT `c`.`common_name` AS `crop`, sum(`s`.`expected_yield_kg`) AS `total_expected_kg`, sum(`h`.`actual_yield_kg`) AS `total_actual_kg`, sum(`h`.`available_qty_kg`) AS `total_available_kg` FROM ((`crops` `c` left join `seasons` `s` on(`s`.`crop_id` = `c`.`id`)) left join `harvests` `h` on(`h`.`season_id` = `s`.`id`)) GROUP BY `c`.`common_name` ;

-- --------------------------------------------------------

--
-- Structure for view `v_health_status_counts`
--
DROP TABLE IF EXISTS `v_health_status_counts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_health_status_counts`  AS SELECT `crop_health_reports`.`status` AS `status`, count(0) AS `cnt` FROM `crop_health_reports` GROUP BY `crop_health_reports`.`status` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buyers`
--
ALTER TABLE `buyers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `buyer_demands`
--
ALTER TABLE `buyer_demands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `crops`
--
ALTER TABLE `crops`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `crop_health_reports`
--
ALTER TABLE `crop_health_reports`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fields`
--
ALTER TABLE `fields`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_fields_farmer` (`farmer_id`);

--
-- Indexes for table `field_logs`
--
ALTER TABLE `field_logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `harvests`
--
ALTER TABLE `harvests`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `harvest_allocations`
--
ALTER TABLE `harvest_allocations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_roles_role_name` (`role_name`);

--
-- Indexes for table `seasons`
--
ALTER TABLE `seasons`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_users_role` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buyers`
--
ALTER TABLE `buyers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `buyer_demands`
--
ALTER TABLE `buyer_demands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `crops`
--
ALTER TABLE `crops`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `crop_health_reports`
--
ALTER TABLE `crop_health_reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `fields`
--
ALTER TABLE `fields`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `field_logs`
--
ALTER TABLE `field_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `harvests`
--
ALTER TABLE `harvests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `harvest_allocations`
--
ALTER TABLE `harvest_allocations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `seasons`
--
ALTER TABLE `seasons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `buyers`
--
ALTER TABLE `buyers`
  ADD CONSTRAINT `fk_buyers_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
