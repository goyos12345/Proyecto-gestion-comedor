# Proyecto-gestion-comedor
Proyecto hecho por: Yamila Da Silva, Valentín Batista y Gregorio Kiolegian Lees 
Pongan los comandos en mySql:
create database comedor;
use comedor;
create table proveedor(
ID int auto_increment primary key,
Nombre varchar(50),
teléfono varchar(50)
);
create table Objeto(
ID int auto_increment primary key,
Nombre varchar(50), 
Contenedor varchar(50),
Cantidad int
);
create table Lote_Ingredientes(
ID int auto_increment primary key,
Nombre varchar(50), 
Contenedor varchar(50),
Cantidad int,
Vencimiento date,
Marca Varchar(50),
ID_Proveedor int,
);
create table Comida(
ID int auto_increment primary key,
Nombre varchar(50), 
Contenedor varchar(50),
Cantidad int,
freezado boolean, 
preparado boolean
);
