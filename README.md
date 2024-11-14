# ***Cifrado de Login***


*Funcionalidad de login para una plataforma. Este programa debe permitir gestionar los nombres de usuario y contraseñas de una plataforma cualquiera. Debe tener dos tipos de usuarios: el administrador (debe haber solamente uno) y los usuarios comunes. El usuario administrador debe poder consultar los nombres de los usuarios existentes, eliminar un usuario o poner en blanco la contraseña de un usuario. Los usuarios comunes deben poder consultar su última fecha/hora de login, y cambiar su contraseña. Las contraseñas deben almacenarse en un archivo de texto o base de datos, empleando salt. Se sugiere investigar y emplear el algoritmo PBKDF2 para el hashing de las contraseñas.*

## ***Instrucciones para correr el programa*** 📓

*Front-end (NextJS):*

```bash
npm run dev
```

*Back-end (Java Springboot):*

```powershell
run:mvn-spring-boot    
```

## ***Hecho con*** 🛠️

*El sistema tiene un backend desarrollado en **Java** utilizando **Spring Boot** y un frontend en **Typescript** con **Next.js**.*

***Backend:***
- ***Controladores**: Los controladores gestionan las solicitudes HTTP y definen las rutas de la API. Incluyen métodos para obtener y eliminar usuarios, realizar inicio de sesión, registrar nuevos usuarios y cambiar contraseñas.*
- ***DAO (Data Access Object)**: El DAO se encarga de interactuar con la base de datos para acceder y manipular la información almacenada.*
- ***Utilidades**: La clase `JWTUtil` proporciona métodos para generar y validar tokens JWT, esenciales en los procesos de autenticación y autorización.*

***Frontend:***
- ***Axios**: En el frontend, `axios.ts` es un servicio de usuario que maneja el almacenamiento del token JWT en `LocalStorage` y lo adjunta automáticamente en los encabezados de las solicitudes HTTP.*

***Roles y Permisos**:*
*El sistema está diseñado con dos interfaces de usuario específicas, una para administradores y otra para usuarios comunes, cada una con permisos y funcionalidades adaptadas a su rol.*

<p align="left">
    <a href="https://code.visualstudio.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/2ae2a900d2f041da66e950e4d48052658d850630/icons/vscode/vscode-original.svg" height="60" width = "60"></a>
    <a href="https://spring.io/projects/spring-boot/" target="_blank"> <img src="https://raw.githubusercontent.com/tandpfun/skill-icons/65dea6c4eaca7da319e552c09f4cf5a9a8dab2c8/icons/Spring-Dark.svg" height="60" width = "60"></a>
    <a href="[https://code.visualstudio.com/](https://gradle.org/install/)](https://www.java.com/es/)" target="_blank"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" height="60" width = "60"></a>
        <a href="https://nextjs.org/" target="_blank"> <img src="https://raw.githubusercontent.com/tandpfun/skill-icons/65dea6c4eaca7da319e552c09f4cf5a9a8dab2c8/icons/NextJS-Dark.svg" height="60" width = "60"></a>
            <a href="https://www.w3schools.com/html/" target="_blank"> <img src="https://raw.githubusercontent.com/tandpfun/skill-icons/65dea6c4eaca7da319e552c09f4cf5a9a8dab2c8/icons/HTML.svg" height="60" width = "60"></a>
                <a href="[https://nextjs.org/](https://www.w3schools.com/css/)" target="_blank"> <img src="https://raw.githubusercontent.com/tandpfun/skill-icons/65dea6c4eaca7da319e552c09f4cf5a9a8dab2c8/icons/CSS.svg" height="60" width = "60"></a>
</p>

## ***Desplegar de forma local*** 📦

*Para correr la pagina web se debe seguir los siguientes pasos:*

1️⃣ *Clonar o descagar el repositorio*

2️⃣ *Abrir el proyecto en tu IDE (debe de tener instalado Java 17)*

5️⃣ *Correr el proyecto en el IDE*

## ***Versionamiento*** 📌

<p align="left">
     <a href="https://git-scm.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/2ae2a900d2f041da66e950e4d48052658d850630/icons/git/git-original.svg" height="60" width = "60"></a>
    <a href="https://github.com/" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/2ae2a900d2f041da66e950e4d48052658d850630/icons/github/github-original.svg" height="60" width = "60"></a>
</p>

*Para todas las actualizaciones del software, consulte las versiones [aquí](https://github.com/danielaolartebo/PI1-RBE/tags).*


## ***Dificultades***

### *Back-end:*

🔸*La configuración y aplicación de medidas de seguridad, como el uso de hashing con PBKDF2 y el almacenamiento seguro de contraseñas con salt, representaron un desafío debido a la importancia de proteger adecuadamente la información sensible y al manejo de herramientas nuevas para el equipo.*

🔸*La decisión sobre dónde almacenar las contraseñas de forma segura y la configuración del sistema para emplear archivos de texto o una base de datos segura con PBKDF2 representaron dificultades iniciales en la estructuración del proyecto.*

### *Front-end:*

🔸*La implementación de solicitudes HTTP a través de Axios para gestionar la autenticación y el cambio de contraseñas supuso un desafío debido a la falta de experiencia previa del equipo, lo que implicó un proceso de aprendizaje.*

🔸*Diseñar la interfaz para que los usuarios recibieran un feedback claro sobre el cambio de contraseñas y el registro de la última fecha/hora de login resultó desafiante, dado que fue necesario integrar respuestas en tiempo real desde el backend.*


## ***Conclusiones***

*Este proyecto de autenticación y gestión de usuarios nos permitió familiarizarnos con prácticas clave de seguridad en aplicaciones, como el uso de PBKDF2 con salt para un almacenamiento seguro de contraseñas. La integración de Spring Boot en el backend y Axios con TypeScript en el frontend facilitó la creación de una aplicación organizada y segura. En resumen, fue una valiosa oportunidad para profundizar en la seguridad de datos y en la integración eficiente entre backend y frontend.*



## ***Autores*** ✒️

<p align="left">
  <a href="https://github.com/danielaolartebo" target="_blank"> <img src="https://images.weserv.nl/?url=avatars.githubusercontent.com/u/53228651?v=4&h=60&w=60&fit=cover&mask=circle"</a>
  <a href="https://github.com/JPSanin" target="_blank"> <img src="https://images.weserv.nl/?url=avatars.githubusercontent.com/u/53494529?v=4&h=60&w=60&fit=cover&mask=circle"</a>

    
</p>

---

[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/for-you.svg)](https://forthebadge.com)
