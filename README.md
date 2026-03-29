# Servidor P2P

Proyecto cliente/servidor con manejo de usuarios, biblioteca, descargas P2P y concurrencia.

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
mvn clean package
```

## Ejecutar

Abrir dos terminales:

Terminal 1:

```bash
mvn exec:java -Dexec.mainClass="servidor.Servidor"
```

Terminal 2:

```bash
mvn exec:java -Dexec.mainClass="cliente.Cliente"
```
