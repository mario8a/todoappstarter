# TODO APP Android App

![Android](https://img.shields.io/badge/Platform-Android-green)
![Android](https://img.shields.io/badge/Platform-android-blue?logo=android)
![Android](https://img.shields.io/badge/Platform-android?logo=android&logoColor=white)

Todo App es una aplicación de ejemplo que muestra un listado de tareas y permite agregar nuevas tareas.

## 🚀 Características

- Interfaz moderna construida con Jetpack Compose.
- Uso de Hilt para inyección de dependencias.
- Manejo de datos con Room.
- Compatible con dispositivos desde SDK 26

---

## 📂 Estructura del Proyecto (Android View)

```
todoappstarter/
├── app/                   # Módulo principal de la aplicación
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/mario8a/todoappstarter
│   │   │   │   ├── components/     # Componentes de interfaz de usuario
│   │   │   │   ├── data/           # Capa de datos (repositorios, fuentes de datos)
│   │   │   │   ├── di/             # Configuración de Hilt
│   │   │   │   ├── domain/         # Modelos y lógica de negocio
│   │   │   │   ├── presentation/   # Capa de presentación
│   │   │   │   ├── ui/theme        # Estilos de interfaz de usuario
│   │   │   │   ├── MainActivity.kt          # Punto de entrada de la aplicación
│   │   │   │   ├── TodoApplication.kt       # Hilt Application
│   │   │   ├── res/                # Recursos de la aplicación (layouts, drawables, strings)
│   │   │   ├── AndroidManifest.xml # Configuración del manifiesto
├── build.gradle           # Configuración del proyecto
├── settings.gradle        # Configuración de módulos
```

---

## 📦 Instalación

1. **Clona el repositorio:**
   ```bash
   git clone 
   ```

2. **Abre el proyecto en Android Studio**

- Asegúrate de tener instalado Android Studio Arctic Fox o superior.

3. **Configura las dependencias**
- Gradle descargará automáticamente las dependencias al abrir el proyecto.

---

## 🛠️ Uso

### Ejecución Local

Ejecución en un dispositivo/emulador:

1. Configura un dispositivo o emulador en Android Studio.
2. Compila y ejecuta la aplicación

---


## 🛠️ Tecnologías Utilizadas

- **Kotlin**: Lenguaje principal de desarrollo.
- **Jetpack compose**: Llamadas HTTP.
- **Room**: Gestión de base de datos local.
- **Hilt**: Inyección de dependencias.

## Screenshots de la Aplicación

<p align="center">
  <img src="/Screenshots/AddTaskScreen.png" alt="Tasks" width="45%"/>
  <img src="/Screenshots/homeScreen.png" alt="Add Tasks" width="45%"/>
</p>