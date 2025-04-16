# Practica 5 - Programación Concurrente

## Trading Card System (TCS)

### Design

##### Usuario

- Tiene un ID de Usuario.
- Direccion de IP.
- Una lista con las cartas y precios de intercambio.
- **Tiene una cantida de monedas**.

##### Cliente

- Empieza por leer el id del Usuario (Log-in).
- Consulta: pide al servidor las cartas disponibles de y su respectivo precio. Recibe la lista con carta + precio + usuario que la tiene y conectados.
- Descarga: da la carta y el usuario y descarga la carta. Ajusta de acuerdo con la transaccion de la base de datos, o sea, reduce correctamente las monedas y cartas.
- **Toda modificación se hace manualmente en sus respectivos JSON.**
- Fin de sessión.
- Tiene un JSON con TODAS las cartas local.

##### Servidor

- Tiene un JSON lista con las listas de información compartida de todos usuarios y si están off or online.
- Toda modificación debe estar reflejada en ambos JSON (Cliente y Servidor).

### Tasks

- Crear REPO (Hibjan).
- Hacer la P4.
- Hacer la P5.1.
- Incorporar en un Proyecto universal el package con las P2, P3 y P4.

##### Implementación de Clases

- Servidor.
    - Oyente Cliente.
- Cliente.
    - Usuario.
    - Oyente Servidor.
    - **Oyente Cliente.**
