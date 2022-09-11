-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2021 a las 12:22:15
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `easytpv`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `contrasenya` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`dni`, `nombre`, `id_empresa`, `id`, `usuario`, `contrasenya`) VALUES
('87654321A', 'Manolo Gómez Torrevieja', 1, 1, '1A@gmail.com', '123'),
('87654321B', 'Alejandro García Senté', 1, 2, '2A@gmail.com', '1234'),
('87654321C', 'Tulio Matamoros Pérez', 1, 3, '3A@gmail.com', '12345'),
('11223344F', 'Alex', 1, 4, 'alavso', '$2a$10$ZtIwTUp4xNMzU6LZ6lCJvurxE1yz0MfS2ATWz87Dwz3H5/mV8r.0S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id` int(11) NOT NULL,
  `nif` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `propietario` varchar(45) NOT NULL,
  `hora_apertura` time NOT NULL,
  `hora_cierre` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id`, `nif`, `nombre`, `propietario`, `hora_apertura`, `hora_cierre`) VALUES
(1, '12345678A', 'Peluquería El Rocío', 'Ramón Palomares Linares', '08:00:00', '18:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `fecha_de_expedicion` date DEFAULT NULL,
  `impuesto_aplicado` varchar(45) DEFAULT NULL,
  `id_reserva` int(11) NOT NULL,
  `metodo_pago` tinyint(1) NOT NULL,
  `total` int(11) NOT NULL,
  `servicios` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `id_empresa`, `fecha_de_expedicion`, `impuesto_aplicado`, `id_reserva`, `metodo_pago`, `total`, `servicios`) VALUES
(2, 1, '2021-10-05', '0,78', 2, 0, 47, 'Lustrado de calva.'),
(3, 1, '2021-10-05', '0,42', 1, 0, 18, 'Lustrado de calva.'),
(7, 1, '2021-10-05', '0,42', 6, 1, 16, 'Lustrado de calva.'),
(20, 1, '2021-11-03', '0,21', 1, 1, 20, 'Corte mujer con lavado,Corte mujer sin lavado,'),
(21, 1, '2021-11-03', '0,21', 1, 1, 18, 'Lustrado de calva.'),
(23, 1, '2021-12-05', '0,21', 15, 1, 12, 'Corte caballero.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` int(11) NOT NULL,
  `nombre_cliente` varchar(45) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `hora` varchar(45) NOT NULL,
  `numero_cliente` int(11) NOT NULL,
  `id_servicio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `nombre_cliente`, `id_empresa`, `fecha`, `hora`, `numero_cliente`, `id_servicio`) VALUES
(1, 'Kiko Hernández López', 1, '2021-10-01', '09:00', 608745513, NULL),
(2, 'María Dolores Rivera Milagros', 1, '2021-10-02', '09:30', 608210156, NULL),
(6, 'Francisco Pérez', 1, '2021-10-13', '10:30', 654741524, NULL),
(7, 'Paco Fernández', 1, '2021-11-20', '09:00:00', 645712355, NULL),
(8, 'Alejandro Ávila', 1, '2021-11-14', '12:30:00', 987654321, NULL),
(11, 'Carmelo Molero', 1, '2021-11-30', '14:30:00', 987654321, 5),
(12, 'Carmelo Molero', 1, '2021-11-29', '14:00:00', 987654321, 1),
(14, 'Daniel García Hermosilla', 1, '2021-12-08', '16:00:00', 987654321, 5),
(15, 'Alejandro Ávila', 1, '2021-12-08', '17:00:00', 123456789, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `imagen` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `id_empresa`, `nombre`, `precio`, `imagen`) VALUES
(1, 1, 'Corte caballero', 12, '/images/servicio3.jpg'),
(2, 1, 'Corte caballero sin lavado', 7, '/images/servicio2.jpg'),
(3, 1, 'Lustrado de calva', 3, '/images/maldini.jpeg'),
(4, 1, 'Tinte', 15, '/images/servicio1.jpg'),
(5, 1, 'Corte mujer con lavado', 12, '/images/servicio2.jpg'),
(6, 1, 'Corte mujer sin lavado', 8, '/images/servicio3.jpg'),
(8, 1, 'Corte de mujer', 7, '/images/servicio1.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio_facturado`
--

CREATE TABLE `servicio_facturado` (
  `id` int(11) NOT NULL,
  `id_factura` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `servicio_facturado`
--

INSERT INTO `servicio_facturado` (`id`, `id_factura`, `id_servicio`) VALUES
(2, 3, 1),
(3, 3, 4),
(4, 2, 5),
(7, 20, 5),
(8, 20, 6),
(9, 21, 3),
(10, 21, 4),
(12, 23, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_empresa_empleados_idx` (`id_empresa`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_empresa_factura_idx` (`id_empresa`),
  ADD KEY `id_reserva` (`id_reserva`),
  ADD KEY `id_reserva_2` (`id_reserva`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_empresa_reserva_idx` (`id_empresa`),
  ADD KEY `FK_servicio_reserva` (`id_servicio`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_empresa_servicio_idx` (`id_empresa`);

--
-- Indices de la tabla `servicio_facturado`
--
ALTER TABLE `servicio_facturado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_servicio_servicio_facturado_idx` (`id_servicio`),
  ADD KEY `FK_factura_servicio_facturado_idx` (`id_factura`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `servicio_facturado`
--
ALTER TABLE `servicio_facturado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK_empresa_empleados` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `FK_empresa_factura` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_reserva_factura` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FK_empresa_reserva` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_servicio_reserva` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `FK_empresa_servicio` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `servicio_facturado`
--
ALTER TABLE `servicio_facturado`
  ADD CONSTRAINT `FK_factura_servicio_facturado` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_servicio_servicio_facturado` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
