create database if not exists Coctelis;

use Coctelis;

create table if not exists usuario (
	id int not null auto_increment,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    correo varchar(100) not null,
    contraseña varchar(100) not null,
    primary key (id)
);

create table if not exists categoria (
	id int not null auto_increment,
    nombre varchar(100) not null,
    primary key (id)
);
