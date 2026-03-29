# P5.2-easy

Esta carpeta contiene una versión lista para ejecutar de P5.2 para portfolio.

## Descripción

- Proyecto cliente/servidor con manejo de usuarios, biblioteca, descargas P2P y concurrencia.

## Requisitos

- Java JDK 8 o superior.
- Maven 3.6+.

## Estructura

- `src/main/java/conc`: implementación de locks y monitores (concurrencia).
- `src/main/java/mensaje`: tipos de mensajes entre cliente/servidor.
- `src/main/java/cliente`: cliente, GUI, oyente.
- `src/main/java/servidor`: servidor, registros, usuarios, biblioteca.
- `pom.xml`: definición de proyecto y dependencias.

## Compilar

```bash
cd PC/P5.2-easy
mvn clean package
```

## Ejecutar

Abrir dos terminales:

Terminal 1:

```bash
cd PC/P5.2-easy
mvn exec:java -Dexec.mainClass="servidor.Servidor"
```

Terminal 2:

```bash
cd PC/P5.2-easy
mvn exec:java -Dexec.mainClass="cliente.Cliente"
```

## Dependencias

- `json-simple:1.1.1` (gestionado automáticamente en `pom.xml`).

## Referencia

- `compile.txt` tiene los pasos de compilación originales que se migraron a Maven.
