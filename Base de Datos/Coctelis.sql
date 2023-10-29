BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT
);
CREATE TABLE IF NOT EXISTS "usuario" (
	"id"	integer,
	"usuario"	text,
	"password"	text,
	"nombre"	text,
	"apellido"	text,
	"estado_sesion"	integer,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "receta" (
	"id"	integer,
	"id_categoria"	integer,
	"id_usuario"	integer,
	"nombre"	text,
	"ingredientes"	text,
	"instrucciones"	text,
	"imagen"	text,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("id_usuario") REFERENCES "usuario"("id"),
	FOREIGN KEY("id_categoria") REFERENCES "categoria"("id")
);
CREATE TABLE IF NOT EXISTS "categoria" (
	"id"	integer,
	"nombre"	text,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "android_metadata" VALUES ('es_US');
INSERT INTO "categoria" VALUES (1,'Con alcohol');
INSERT INTO "categoria" VALUES (2,'Sin alcohol');
COMMIT;
