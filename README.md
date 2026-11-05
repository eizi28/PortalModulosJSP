# PortalModulosJSP

## 1. Descripción

Proyecto académico desarrollado en Java y JSP
para practicar plantillas web, condicionales,
bucles y programación orientada a objetos.

La aplicación permite registrar usuarios,
iniciar sesión y acceder a un panel educativo
con diferentes módulos de aprendizaje.

## 2. Objetivo

Desarrollar una aplicación web utilizando
Java, JSP, Servlets y HTML, aplicando estructuras
condicionales y repetitivas para mostrar
contenido dinámico.

## 3. Tecnologías utilizadas

- Java
- JavaServer Pages (JSP)
- Jakarta Servlets
- HTML5
- CSS3
- Apache Maven
- Apache Tomcat 10.1
- Git y GitHub

## 4. Funcionalidades

### Registro de usuarios

Permite registrar usuarios mediante un formulario
que solicita nombre, correo y contraseña.

Incluye validaciones para:
- Evitar campos obligatorios vacíos.
- Comprobar el formato del correo.
- Evitar correos duplicados.
- Confirmar que las contraseñas coincidan.

### Inicio de sesión

El usuario puede ingresar con su correo
y contraseña previamente registrados.

Las contraseñas se protegen mediante
PBKDF2 con sal aleatoria.

### Panel principal

Después de iniciar sesión, el usuario
puede visualizar:

- Bienvenida personalizada.
- Correo electrónico registrado.
- Cantidad de módulos disponibles.
- Seis módulos educativos.

### Cierre de sesión

Permite finalizar la sesión y restringir
el acceso al panel sin autenticación.

## 5. Condicionales y bucles

### Condicionales

Se utilizan instrucciones if y else para:

- Validar los formularios.
- Comprobar las credenciales.
- Verificar la sesión del usuario.
- Mostrar mensajes de error o éxito.
- Comprobar si existen módulos disponibles.

### Bucles

Se utiliza un bucle for en dashboard.jsp
para recorrer la lista de módulos y generar
las tarjetas HTML automáticamente.

También se utilizan estructuras repetitivas
en las clases Java del proyecto.

## 6. Módulos educativos

1. HTML
2. CSS
3. JavaScript
4. Java
5. Bases de Datos
6. JSP y Servlets

## 7. Organización del proyecto

- modelo: clases Usuario y Modulo.
- servicio: gestión de usuarios y módulos.
- controlador: ControladorGeneral.
- webapp: páginas JSP y archivos CSS.
- WEB-INF/vistas: panel protegido.
- pom.xml: configuración Maven.

## 8. Cómo ejecutar

Requisitos:

- JDK 17 o superior.
- Apache Maven.
- Apache Tomcat 10.1.

Pasos:

1. Descargar o clonar este repositorio.
2. Abrirlo en Visual Studio Code.
3. Ejecutar Maven con el comando:

   mvn clean package

4. Copiar target/PortalModulosJSP.war
   a la carpeta webapps de Tomcat.
5. Iniciar el servidor Tomcat.
6. Abrir en el navegador:

   http://localhost:8080/PortalModulosJSP/ingresar

## 9. Consideraciones

Los usuarios se almacenan temporalmente
en memoria. Al reiniciar la aplicación,
los registros se pierden.

Es un proyecto académico de demostración.
No está diseñado para producción y requiere
medidas adicionales de seguridad para ese uso.

## 10. Conclusión

Este proyecto permite comprender cómo
se integran Java, JSP, HTML y Servlets
para desarrollar aplicaciones web dinámicas.

También demuestra la utilidad de los
condicionales y bucles para controlar
el funcionamiento y la presentación de datos.
