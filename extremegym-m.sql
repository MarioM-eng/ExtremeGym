-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.18 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para extremegym
CREATE DATABASE IF NOT EXISTS `extremegym` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `extremegym`;

-- Volcando estructura para procedimiento extremegym.actualizarAdmin
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarAdmin`(
	IN `ide` VARCHAR(30),
	IN `nom` VARCHAR(30),
	IN `ape` VARCHAR(30),
	IN `usuario` VARCHAR(30)
)
BEGIN
UPDATE administrador SET nombre=nom, apellido=ape, identificador=usuario WHERE idadministrador=ide;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.actualizarAdminClave
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarAdminClave`(IN id INT,IN  valor VARCHAR(30))
UPDATE administrador SET clave=valor WHERE idadministrador=id;//
DELIMITER ;

-- Volcando estructura para tabla extremegym.administrador
CREATE TABLE IF NOT EXISTS `administrador` (
  `idadministrador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL DEFAULT '0',
  `apellido` varchar(30) NOT NULL DEFAULT '0',
  `identificador` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  PRIMARY KEY (`idadministrador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.administrador: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT IGNORE INTO `administrador` (`idadministrador`, `nombre`, `apellido`, `identificador`, `clave`) VALUES
	(1, 'Jesús', 'Aviléz', 'admin', 'admin');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;

-- Volcando estructura para procedimiento extremegym.agregarCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarCliente`(
	IN `nombre` VARCHAR(30),
	IN `apellido` VARCHAR(30),
	IN `celular` VARCHAR(10),
	IN `est` INT,
	IN `regis` DATE,
	IN `id_admin` INT(4)





)
BEGIN
    Insert into cliente(nombre,apellido,celular,estado,registrado_en,administrador_idadministrador) 
	 values(nombre,apellido,celular,est,regis,id_admin);
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.agregarPago
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarPago`(
	IN `idadmin` INT,
	IN `idcliente` INT,
	IN `valor` INT,
	IN `fechainicial` DATE ,
	IN `fechafinal` DATE,
	IN `tipopago` INT
)
BEGIN
    Insert into pago(administrador_idadministrador,cliente_id,valor,fechainicial,fechafinal,tipodepago_idtipodepago,estado) 
	 values(idadmin,idcliente,valor,fechainicial,fechafinal,tipopago,1);
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.buscar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar`(IN valor VARCHAR(30))
BEGIN
SELECT * FROM cliente WHERE nombre LIKE CONCAT('%',valor,'%') OR apellido LIKE CONCAT('%',valor,'%') OR celular LIKE valor;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.buscarPorNombreYapellido
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarPorNombreYapellido`(
	IN `valor` VARCHAR(30)

)
BEGIN
SET @cadena = valor;/*Se guarda la cadena en una variable*/
SET @resul = INSTR(@cadena,' ');/*Se busca un espacio en la cadena*/
SET @ape = SUBSTRING(@cadena,@resul);/*Se guarda la parte de la cadena que va despues del espacio*/
SET @ap = LTRIM(@ape);/*Se elimina el espacio*/
SET @nom = LEFT(@cadena,@resul-1);/*Se recorre la cadena hasta antes del espacio. Ese sería el nombre*/
SELECT * FROM cliente 
	WHERE nombre LIKE CONCAT(@nom,'%') && apellido LIKE CONCAT('%',@ap,'%');
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.buscarunoCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarunoCliente`(
	IN `valor` INT
)
BEGIN
SELECT * FROM cliente WHERE id LIKE valor;
END//
DELIMITER ;

-- Volcando estructura para tabla extremegym.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `celular` varchar(10) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `registrado_en` date NOT NULL,
  `administrador_idadministrador` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cliente_administrador1_idx` (`administrador_idadministrador`),
  KEY `FK_cliente_estado_cliente` (`estado`),
  CONSTRAINT `FK_cliente_estado_cliente` FOREIGN KEY (`estado`) REFERENCES `estado_cliente` (`id`),
  CONSTRAINT `fk_cliente_administrador1` FOREIGN KEY (`administrador_idadministrador`) REFERENCES `administrador` (`idadministrador`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.cliente: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT IGNORE INTO `cliente` (`id`, `nombre`, `apellido`, `celular`, `estado`, `registrado_en`, `administrador_idadministrador`) VALUES
	(53, 'Guillo', 'Avilez', '3002329659', 1, '2020-05-29', 1),
	(54, 'Jesús', 'Avilez', '3006548976', 1, '2020-05-29', 1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Volcando estructura para procedimiento extremegym.ConsultarPago
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `ConsultarPago`(IN fec DATE)
BEGIN
SELECT pago.cliente_id, cliente.nombre AS nombreCliente,cliente.apellido AS apellidoCliente, pago.valor,pago.fechainicial, pago.fechafinal,pago.tipodepago_idtipodepago, tipodepago.nombre AS tipoPago 
	FROM pago INNER JOIN cliente ON pago.cliente_id=cliente.id INNER JOIN tipodepago ON pago.tipodepago_idtipodepago=tipodepago.idtipodepago
		WHERE fechainicial = fec ORDER  BY cliente.nombre;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.eliminarCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarCliente`(
	IN `dato` INT(10)

,
	IN `est` INT
)
BEGIN
UPDATE cliente SET estado=est WHERE id=dato;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.estadoPago
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `estadoPago`(IN fecha DATE)
BEGIN
UPDATE pago SET estado = IF(fechafinal<=fecha,2,1);
END//
DELIMITER ;

-- Volcando estructura para tabla extremegym.estado_cliente
CREATE TABLE IF NOT EXISTS `estado_cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.estado_cliente: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `estado_cliente` DISABLE KEYS */;
INSERT IGNORE INTO `estado_cliente` (`id`, `estado`) VALUES
	(1, 'Activo'),
	(2, 'Inactivo');
/*!40000 ALTER TABLE `estado_cliente` ENABLE KEYS */;

-- Volcando estructura para tabla extremegym.estado_pago
CREATE TABLE IF NOT EXISTS `estado_pago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.estado_pago: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `estado_pago` DISABLE KEYS */;
INSERT IGNORE INTO `estado_pago` (`id`, `nombre`) VALUES
	(1, 'en proceso'),
	(2, 'Vencido'),
	(3, 'congelado');
/*!40000 ALTER TABLE `estado_pago` ENABLE KEYS */;

-- Volcando estructura para procedimiento extremegym.listarAdmin
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarAdmin`()
BEGIN
    SELECT * FROM administrador;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.listarCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarCliente`()
BEGIN
SELECT * FROM cliente;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.listarEstadoCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarEstadoCliente`()
BEGIN
    SELECT * FROM estado_cliente;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.listarEstadoClientePorId
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarEstadoClientePorId`(IN ide INT)
BEGIN
    SELECT * FROM estado_cliente WHERE id=ide;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.listarTipoPago
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarTipoPago`()
BEGIN
SELECT * FROM tipodepago;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.listarTipoReportesDePago
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `listarTipoReportesDePago`()
BEGIN
SELECT * FROM tipoReportesDePago;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.modificarCliente
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCliente`(
	IN `ide` VARCHAR(30),
	IN `nom` VARCHAR(30),
	IN `ape` VARCHAR(30),
	IN `cel` VARCHAR(30),
	IN `est` INT
)
BEGIN
UPDATE cliente SET nombre=nom, apellido=ape, celular=cel,estado=est WHERE id=ide;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.modificarValorTpago
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarValorTpago`(IN id INT, IN val INT)
BEGIN
UPDATE tipodepago SET valor = val WHERE idtipodepago = id;
END//
DELIMITER ;

-- Volcando estructura para tabla extremegym.pago
CREATE TABLE IF NOT EXISTS `pago` (
  `idpago` int(11) NOT NULL AUTO_INCREMENT,
  `administrador_idadministrador` int(11) NOT NULL DEFAULT '0',
  `cliente_id` int(11) NOT NULL,
  `valor` int(11) NOT NULL,
  `fechainicial` date DEFAULT NULL,
  `fechafinal` date DEFAULT NULL,
  `tipodepago_idtipodepago` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idpago`),
  KEY `FK_pago_cliente` (`cliente_id`),
  KEY `FK_pago_tipodepago` (`tipodepago_idtipodepago`),
  KEY `FK_pago_administrador` (`administrador_idadministrador`),
  KEY `FK_pago_estado_pago` (`estado`),
  CONSTRAINT `FK_pago_administrador` FOREIGN KEY (`administrador_idadministrador`) REFERENCES `administrador` (`idadministrador`),
  CONSTRAINT `FK_pago_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK_pago_estado_pago` FOREIGN KEY (`estado`) REFERENCES `estado_pago` (`id`),
  CONSTRAINT `FK_pago_tipodepago` FOREIGN KEY (`tipodepago_idtipodepago`) REFERENCES `tipodepago` (`idtipodepago`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.pago: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT IGNORE INTO `pago` (`idpago`, `administrador_idadministrador`, `cliente_id`, `valor`, `fechainicial`, `fechafinal`, `tipodepago_idtipodepago`, `estado`) VALUES
	(35, 1, 53, 1200, '2020-05-30', '2020-05-30', 1, 2),
	(36, 1, 54, 1200, '2020-05-30', '2020-05-30', 1, 2),
	(37, 1, 53, 50000, '2020-06-01', '2020-07-01', 2, 1),
	(38, 1, 54, 1200, '2020-06-01', '2020-06-01', 1, 1),
	(39, 1, 54, 1200, '2020-05-31', '2020-05-31', 1, 2);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;

-- Volcando estructura para procedimiento extremegym.reportePagoAnioMes
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportePagoAnioMes`(
	IN `fec` VARCHAR(10)
)
BEGIN
    SELECT cliente.nombre AS nombreCliente,cliente.apellido AS apellidoCliente,COUNT(cliente.id) AS asistencias,SUM(pago.valor) AS 'totalPago' FROM pago INNER JOIN cliente ON pago.cliente_id=cliente.id WHERE fechainicial LIKE CONCAT(fec,'%') GROUP  BY cliente.nombre ORDER  BY cliente.nombre;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.reportePagoDiario
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportePagoDiario`(IN fec DATE)
BEGIN
    SELECT administrador.nombre AS 'Admin.',cliente.nombre AS nombreCliente,cliente.apellido AS apellidoCliente,pago.valor AS valor FROM pago INNER JOIN administrador ON pago.administrador_idadministrador=administrador.idadministrador INNER JOIN cliente ON pago.cliente_id=cliente.id WHERE fechainicial = fec ORDER  BY cliente.nombre;
END//
DELIMITER ;

-- Volcando estructura para procedimiento extremegym.reportePagoMensual
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `reportePagoMensual`(
	IN `fec` VARCHAR(10)
)
BEGIN
    SELECT administrador.nombre AS 'Admin.',cliente.nombre AS nombreCliente,cliente.apellido AS apellidoCliente,pago.valor AS valor FROM pago INNER JOIN administrador ON pago.administrador_idadministrador=administrador.idadministrador INNER JOIN cliente ON pago.cliente_id=cliente.id WHERE fechainicial LIKE CONCAT(fec,'%') ORDER  BY cliente.nombre;
END//
DELIMITER ;

-- Volcando estructura para tabla extremegym.reportpersonmensual
CREATE TABLE IF NOT EXISTS `reportpersonmensual` (
  `idreportpersonmensual` int(11) NOT NULL AUTO_INCREMENT,
  `npersonas` int(11) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `administrador_idadministrador` int(11) NOT NULL,
  PRIMARY KEY (`idreportpersonmensual`),
  KEY `fk_reportpersonmensual_administrador1_idx` (`administrador_idadministrador`),
  CONSTRAINT `fk_reportpersonmensual_administrador1` FOREIGN KEY (`administrador_idadministrador`) REFERENCES `administrador` (`idadministrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.reportpersonmensual: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `reportpersonmensual` DISABLE KEYS */;
/*!40000 ALTER TABLE `reportpersonmensual` ENABLE KEYS */;

-- Volcando estructura para tabla extremegym.tipocliente
CREATE TABLE IF NOT EXISTS `tipocliente` (
  `idtipocliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipocliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.tipocliente: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tipocliente` DISABLE KEYS */;
INSERT IGNORE INTO `tipocliente` (`idtipocliente`, `nombre`) VALUES
	(1, 'Cliente con pago diario'),
	(2, 'Cliente con pago mensual');
/*!40000 ALTER TABLE `tipocliente` ENABLE KEYS */;

-- Volcando estructura para tabla extremegym.tipodepago
CREATE TABLE IF NOT EXISTS `tipodepago` (
  `idtipodepago` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `valor` int(11) NOT NULL,
  PRIMARY KEY (`idtipodepago`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.tipodepago: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tipodepago` DISABLE KEYS */;
INSERT IGNORE INTO `tipodepago` (`idtipodepago`, `nombre`, `valor`) VALUES
	(1, 'Pago diario', 1200),
	(2, 'Pago mensual', 50000);
/*!40000 ALTER TABLE `tipodepago` ENABLE KEYS */;

-- Volcando estructura para tabla extremegym.tiporeportesdepago
CREATE TABLE IF NOT EXISTS `tiporeportesdepago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla extremegym.tiporeportesdepago: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `tiporeportesdepago` DISABLE KEYS */;
INSERT IGNORE INTO `tiporeportesdepago` (`id`, `nombre`) VALUES
	(1, 'Reporte de pago diario'),
	(2, 'Reporte de pago mensual'),
	(3, 'Reporte de pago anual');
/*!40000 ALTER TABLE `tiporeportesdepago` ENABLE KEYS */;

-- Volcando estructura para procedimiento extremegym.verificarCel
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificarCel`(IN valor VARCHAR(30))
BEGIN
SELECT * FROM cliente WHERE celular LIKE valor;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
