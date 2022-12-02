# IronLibrary
## INTRODUCCIÓN
IronLibrary es un proyecto desarrollado por el grupo **Alcohodificadores-Anónimos&reg;** desarrollado en el lenguaje de programación Java el cual simula una gestión de venta de cursos. Dicho proyecto forma parte los proyectos grupales que Ironhack&copy; formula a los alumnos de sus bootcamps para que estos desarrollen el proyecto de forma grupal.

##  INSTRUCCIONES DE INSTALACIÓN DEL PROGRAMA PARA SU USO

**1.** El primer paso que deberemos seguir es descargarnos la carpeta que contiene todo el proyecto y a continuación, la descomprimimos en la ubicación que nosotros escojamos.

**2.** Una vez descargado y descomprimido el programa deberemos iniciar nuestro IDE para ejecutar el programa JAVA, buscaremos dentro del IDE la opción para abrir un archivo ya existente. Una vez abierta la carpeta de nuestro programa desde el IDE, dentro de la carpeta del proyecto, buscaremos la carpeta cuyo nombre es "src" y dentro de esta localizaremos la carpeta main que contiene una carpeta nombrada java y ahi localizaremos los archivos .java para correr el programa.

Ejemplo dirección de directorios: ../IronLibrary/.idea/src/main/java/ironlibrary

**3.** A continuación, tendremos todo listo para correr nuestro programa, deberemos acceder al archivo cuyo nombre es "Main" y ejecturar dicho archivo desde la IDE para que corra el programa.

*ATENCIÓN: Antes de ejecutar el código debemos acceder a la carpeta resources y en el properties debemos verificar que los datos Username y Root corresponden con los nuestros.*

## INSTRUCCIONES DEL PROGRAMA PARA SU USO
### Menú de inicio
[![Image from Gyazo](https://i.gyazo.com/898d72f35407ce5a30f4d9cc846bf62a.png)](https://gyazo.com/898d72f35407ce5a30f4d9cc846bf62a)
      
      Podremos escoger una de las diversas opciones que nos ofrece el programa.
      
### 1.-Add a book

[![Image from Gyazo](https://i.gyazo.com/06f18f472bd5b99bc421f0ec211e4968.png)](https://gyazo.com/06f18f472bd5b99bc421f0ec211e4968)

    1.- Nos pedirá que añadamos el Isbn, es el código identificativo del libro.
    2.- Introduciremos el título del libro que queramos añadir.
    3.- Introduciremos el nombre del autor del libro.
    4.- Introduciremos el email del autor del libro.
*Nos aparecerá un mensaje "Author saved into DB* y *Book saved into DB*

### 2.-Search book by title

[![Image from Gyazo](https://i.gyazo.com/9f0ad6d63f0a70d7c20c89df6d5935a6.png)](https://gyazo.com/9f0ad6d63f0a70d7c20c89df6d5935a6)

    Search book by title nos mostrará información relevante acerca del libro que hayamos introducido.

[![Image from Gyazo](https://i.gyazo.com/d6ba8e02d8cfd5cd65359ab98c08731d.png)](https://gyazo.com/d6ba8e02d8cfd5cd65359ab98c08731d)
    
    En el caso de que el libro no se encuentre en la base de datos el mensaje que se mostrará por pantalla es "Books not found".
    
### 3.- Search book by category

[![Image from Gyazo](https://i.gyazo.com/2dbf72e6b520c0400111300fc2b510fa.png)](https://gyazo.com/2dbf72e6b520c0400111300fc2b510fa)

    Si buscamos una categoría se mostrará un listado de libros que corresponden a dicha categoría.
    
[![Image from Gyazo](https://i.gyazo.com/68b0b4815e7f76bce570bf0540e6bc7d.png)](https://gyazo.com/68b0b4815e7f76bce570bf0540e6bc7d)

    Mensaje que se mostrará en caso de que ningún libro se corresponda con la categoría
    
 ### 4.- Seach book by author
 
 [![Image from Gyazo](https://i.gyazo.com/a49b5ae8ea68d8114ff55920bdd79dbd.png)](https://gyazo.com/a49b5ae8ea68d8114ff55920bdd79dbd)
 
    Si buscamos un libro por autor se mostrará un listado de libros que corresponden a dicho autor.
    
[![Image from Gyazo](https://i.gyazo.com/cce64154e60987178a3668338bd22e27.png)](https://gyazo.com/cce64154e60987178a3668338bd22e27)

    Mensaje que se mostrará en caso de que ningún aparezca con ese nombre en el sistema.
  
### 5.- List all books along with author

[![Image from Gyazo](https://i.gyazo.com/11bb749f79c362ab24b326aba299fd65.png)](https://gyazo.com/11bb749f79c362ab24b326aba299fd65)

    Este comando nos mostrará un listado de cada libro en el sistema con informacón relevante incluido su autor
    
### 6.- Issue book to student

[![Image from Gyazo](https://i.gyazo.com/340633a3d45c3236bebce9af9d5e0af6.png)](https://gyazo.com/340633a3d45c3236bebce9af9d5e0af6)

    La opción "Issue book to student" nos permite prestar un libro a un estudiante.
    1.- Introduciremos el USN (Número que identifica a cada estudiante).
    2.- Introduciremos el nombre del estudiante.
    3.- Introduciremos el ISBN del libro que vamos a prestar.
    
*Si el prestamo se realiza con éxito aparecerán los mensajes "Book issued" y "Return date: año-mes-día".*

[![Image from Gyazo](https://i.gyazo.com/76d5875329df185e0138f6b4d2204820.png)](https://gyazo.com/76d5875329df185e0138f6b4d2204820)
     
    Este es el mensaje que aparecerá en el caso de que queramos prestar un libro que no se encuentre en nuestra base de datos.

### 7.- List books by usn
[![Image from Gyazo](https://i.gyazo.com/e1aaee4bb1cb2af0b9c6ad3a78af4548.png)](https://gyazo.com/e1aaee4bb1cb2af0b9c6ad3a78af4548)

    Si el USN del estudiante se encuentra en la base de datos de préstamos se mostrarán por pantalla los datos referentes al libro prestado y al estudiando que tiene el préstamo

[![Image from Gyazo](https://i.gyazo.com/e20cc51e7bfbb8a90feb3056669c9248.png)](https://gyazo.com/e20cc51e7bfbb8a90feb3056669c9248)

    Si el USN no corresponde con ninguno de los registrados en la base de datos aparecerá el anterior mensaje.

### 8.- BONUS OPTION. Display books to be returned today
[![Image from Gyazo](https://i.gyazo.com/b8e44e2c95ed2ba0389087b42174c8f5.png)](https://gyazo.com/b8e44e2c95ed2ba0389087b42174c8f5)
            
    Nos devolverá un listado con los libros prestados que han de devolverse ese día.

## CRÉDITOS
Organización: Alcohodificadores-Anónimos® 2022

Raul Ruiz: https://github.com/RaulRuiz1997    LinkedIn: https://www.linkedin.com/in/ra%C3%BAl-ruiz-pedrosa-3b4135164 \
Eduard Blanco: https://github.com/eblancode   LinkedIn: https://www.linkedin.com/eduardblanco \
Manuel Orfe: https://github.com/Manuelorfe    LinkedIn: https://www.linkedin.com/in/manuel-ortega-fernandez \
Xavi Romero: https://github.com/xavi-dig      LinkedIn: https://www.linkedin.com/in/xaviromerolopez \
Cristian Pérez: https://github.com/KogeCode   LinkedIn: https://www.linkedin.com/in/cristian-p%C3%A9rez-barbero-87616478/
