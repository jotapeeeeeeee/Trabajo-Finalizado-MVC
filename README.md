# ☕ Sistema de Gestión con Arquitectura MVC (Java)

## 📖 Descripción
Aplicación de escritorio desarrollada en **Java** siguiendo el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**. Permite la gestión de productos y usuarios con persistencia en archivos de texto.

## 🛠️ Tecnologías utilizadas
- **Java** (JDK 17+)
- **Swing** (Interfaz gráfica)
- **Patrón MVC**
- **Persistencia en archivos de texto** (.txt)

## 📂 Estructura del Proyecto
- **Modelo**: Clases `Producto`, `Usuario`, `GestorDatos` (lógica de negocio y persistencia).
- **Vista**: Interfaces de usuario con `JFrame`, `JPanel`, `JTable`.
- **Controlador**: Manejo de eventos y comunicación entre modelo y vista.

## 🚀 Instrucciones de Ejecución
1. Clonar el repositorio.
2. Abrir el proyecto en **IntelliJ IDEA**, **Eclipse** o cualquier IDE con soporte Java.
3. Ejecutar la clase principal `Main.java`.

## 🎯 Funcionalidades principales
- **CRUD de productos**: Alta, baja, modificación y listado.
- **Gestión de usuarios**: Roles de administrador y usuario.
- **Persistencia**: Guardado automático en archivos de texto.
- **Validaciones**: Control de stock, precios negativos y formatos incorrectos.

## 🔮 Mejoras futuras
- Migrar a una base de datos **MySQL** o **SQLite**.
- Agregar reportes en **PDF**.
- Mejorar la interfaz con **JavaFX**.
