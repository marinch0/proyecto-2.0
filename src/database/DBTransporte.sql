-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: transporte
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aseguradora`
--

DROP TABLE IF EXISTS `aseguradora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aseguradora` (
  `codigo_aseg` int(11) NOT NULL,
  `nombre_aseg` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_aseg` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo_aseg`),
  KEY `id_func_idx` (`id_func`),
  CONSTRAINT `id_func` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aseguradora`
--

LOCK TABLES `aseguradora` WRITE;
/*!40000 ALTER TABLE `aseguradora` DISABLE KEYS */;
INSERT INTO `aseguradora` VALUES (123,'Aseguradora Sultamarchan','123',1);
/*!40000 ALTER TABLE `aseguradora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistente`
--

DROP TABLE IF EXISTS `asistente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asistente` (
  `id_asis` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_asis` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_asis` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `celular_asis` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `correo_asis` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion_asis` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ruta_foto_asis` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_asis` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_asis`),
  KEY `id_func_asis_idx` (`id_func`),
  CONSTRAINT `id_func_asis` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistente`
--

LOCK TABLES `asistente` WRITE;
/*!40000 ALTER TABLE `asistente` DISABLE KEYS */;
INSERT INTO `asistente` VALUES ('123','Sebastian','Obando','3291293','Chinga@gmail.com','su casa',NULL,'123',1);
/*!40000 ALTER TABLE `asistente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conductor`
--

DROP TABLE IF EXISTS `conductor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conductor` (
  `id_cond` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_cond` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_cond` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ruta_foto_cond` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `celular_cond` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `correo_cond` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion_cond` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `categoria_licencia` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `vigencia_licencia` date DEFAULT NULL,
  `ciudad_licencia` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `restriccion_licencia` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ruta_foto_licencia` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_cond` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_cond`),
  KEY `id_func_cond_idx` (`id_func`),
  CONSTRAINT `id_func_cond` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conductor`
--

LOCK TABLES `conductor` WRITE;
/*!40000 ALTER TABLE `conductor` DISABLE KEYS */;
INSERT INTO `conductor` VALUES ('123','Juan Jose ','Marin',NULL,'31299','Bla@gmail.com','caicedonia','A1','2018-11-09','Armenia','topu',NULL,'123',1);
/*!40000 ALTER TABLE `conductor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ruta`
--

DROP TABLE IF EXISTS `detalle_ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_ruta` (
  `codigo_ruta` varchar(4) COLLATE latin1_spanish_ci DEFAULT NULL,
  `codigo_est` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  KEY `cod_ruta_det_idx` (`codigo_ruta`),
  KEY `cod_est_det_idx` (`codigo_est`),
  CONSTRAINT `cod_est_det` FOREIGN KEY (`codigo_est`) REFERENCES `estudiante` (`codigo_est`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cod_ruta_det` FOREIGN KEY (`codigo_ruta`) REFERENCES `ruta` (`codigo_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ruta`
--

LOCK TABLES `detalle_ruta` WRITE;
/*!40000 ALTER TABLE `detalle_ruta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estudiante` (
  `codigo_est` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `id_est` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nombre_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `celular_est` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `correo_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `grado_est` varchar(4) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ciudad_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `acudiente_est` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `celular_acu_est` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `correo_acu_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion_acu_est` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_est` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo_est`),
  KEY `id_func_est_idx` (`id_func`),
  CONSTRAINT `id_func_est` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id_func` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `celular_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `correo_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccion_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `usuario_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `clave_func` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_func` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_func`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES ('123','Juan Manuel','Villanueva','3195429193','jesmol.soul@gmail.com','El Caimo Conjunto Campestre la Cecilia','dronixRS','123',1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruta` (
  `codigo_ruta` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `periodo_ruta` int(11) DEFAULT NULL,
  `zona_ruta` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `placa_veh` varchar(7) COLLATE latin1_spanish_ci DEFAULT NULL,
  `recorrido_veh` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `hora_inicio_ruta` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `hora_final_ruta` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cupos_asignados_ruta` int(11) DEFAULT NULL,
  `cupos_disponibles_ruta` int(11) DEFAULT NULL,
  `tiempo_recorrido_ruta` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `num_paradas_ruta` int(11) DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_ruta` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo_ruta`),
  KEY `placa_veh_ruta_idx` (`placa_veh`),
  KEY `id_func_ruta_idx` (`id_func`),
  CONSTRAINT `id_func_ruta` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `placa_veh_ruta` FOREIGN KEY (`placa_veh`) REFERENCES `vehiculo` (`placa_veh`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soat`
--

DROP TABLE IF EXISTS `soat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soat` (
  `codigo_soat` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `codigo_aseg_soat` int(11) DEFAULT NULL,
  `fecha_soat` date DEFAULT NULL,
  `fecha_vigencia_soat` date DEFAULT NULL,
  `ruta_foto_soat` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_soat` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo_soat`),
  KEY `codigo_aseg_soat_idx` (`codigo_aseg_soat`),
  KEY `id_func_soat_idx` (`id_func`),
  CONSTRAINT `codigo_aseg_soat` FOREIGN KEY (`codigo_aseg_soat`) REFERENCES `aseguradora` (`codigo_aseg`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_func_soat` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soat`
--

LOCK TABLES `soat` WRITE;
/*!40000 ALTER TABLE `soat` DISABLE KEYS */;
INSERT INTO `soat` VALUES ('123',123,'2018-09-18','2018-09-18',NULL,'123',1);
/*!40000 ALTER TABLE `soat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnomecanica`
--

DROP TABLE IF EXISTS `tecnomecanica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecnomecanica` (
  `codigo_tecno` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `centro_diagnostico_tecno` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fecha_tecno` date DEFAULT NULL,
  `fecha_vigencia_tecno` date DEFAULT NULL,
  `ruta_foto_tecno` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_tecno` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo_tecno`),
  KEY `id_func_tecno_idx` (`id_func`),
  CONSTRAINT `id_func_tecno` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnomecanica`
--

LOCK TABLES `tecnomecanica` WRITE;
/*!40000 ALTER TABLE `tecnomecanica` DISABLE KEYS */;
INSERT INTO `tecnomecanica` VALUES ('123','Diagnostico Sultamarchan','2018-09-18','2018-09-18',NULL,'123',1);
/*!40000 ALTER TABLE `tecnomecanica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiculo` (
  `placa_veh` varchar(7) COLLATE latin1_spanish_ci NOT NULL,
  `numero_veh` int(11) DEFAULT NULL,
  `modelo_veh` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `tipo_veh` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `capacidad_veh` int(11) DEFAULT NULL,
  `empresa_veh` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `marca_veh` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ruta_foto_veh` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_cond` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_asis` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cod_soat` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cod_tecno` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_func` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado_veh` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`placa_veh`),
  KEY `id_func_veh_idx` (`id_func`),
  KEY `id_cond_veh_idx` (`id_cond`),
  KEY `id_asis_veh_idx` (`id_asis`),
  KEY `cod_soat_veh_idx` (`cod_soat`),
  KEY `cod_tecno_veh_idx` (`cod_tecno`),
  CONSTRAINT `cod_soat_veh` FOREIGN KEY (`cod_soat`) REFERENCES `soat` (`codigo_soat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cod_tecno_veh` FOREIGN KEY (`cod_tecno`) REFERENCES `tecnomecanica` (`codigo_tecno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_asis_veh` FOREIGN KEY (`id_asis`) REFERENCES `asistente` (`id_asis`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_cond_veh` FOREIGN KEY (`id_cond`) REFERENCES `conductor` (`id_cond`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_func_veh` FOREIGN KEY (`id_func`) REFERENCES `funcionario` (`id_func`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-08 15:20:05
