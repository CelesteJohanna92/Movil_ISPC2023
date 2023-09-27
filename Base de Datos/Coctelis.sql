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

create table if not exists recetas (
    id int not null auto_increment,
    id_categoria int not null,
    nombre varchar(100) not null,
    origen varchar(100) not null,
    tipo varchar(100) not null,
    ingredientes varchar(100) not null,
    imagen varchar(100) not null,
    receta varchar(1000) not null,
    primary key (id),
    foreign key (id_categoria) references categoria(id)
);
