# GimnasioApiUade
Trabajo practico Gimnasio para Aplicaciones Interactivas UADE

## Tabla de contenidos

1. [Socios](#socios)
2. [Deportes](#deportes)
3. [Abonos](#abonos)
4. [Cronogramas](#cronogramas)
5. [Empleados](#empleados)
6. [Liquidación de sueldos](#liquidación-de-sueldos)
7. [Validar Ingreso](#validar-ingreso)
8. [Notificaciones](#notificaciones)
9. [Consulta de cronogramas](#consulta-de-cronogramas)
10. [Inscripción](#inscripción)


## Sistema de Administración de Gimnasio

### Socios:
* El sistema debe permitir la administración de socios (alta, baja y modificación). De los mismos se debe guardar la siguiente información: nombre, domicilio, teléfono y mail. 
* Los socios se inscriben en distintos deportes y concurren a las correspondientes clases.
* Cada cierto tiempo los socios deben presentar su apto medico el cual queda registrado en el sistema. (fecha certificado, nombre profesional firmante, observaciones).

### Deportes
* El sistema debe permitir la administración de deportes (alta, baja y modificación). De los mismos se debe guardar: código, titulo, descripción.
* Cada deporte tiene sus actividades y  asociadas los distintos horarios en que se dicta. Dependiendo del deporte pueden encontrarse clases con profesores u horarios habilitados para que los socios concurran. Por ejemplo: clase de zumba, pileta libre, gimnasio mecanizado.

### Abonos
* El sistema debe permitir administrar los distintos abonos que ofrece el gimnasio. De cada abono se registra: código, nombre, precio, periodo vigencia.

### Cronogramas
* El sistema debe permitir generar los cronogramas en donde se detalle por día y por horario las distintas actividades que se llevan a cabo en el gimnasio.

### Profesores
* El sistema debe permitir administrar los profesores que trabajan en el gimnasio. Se registran los datos personales.
* Los profesores están agrupados en dos tipos: los que cumplen horario completo y los que solo asisten a dar clases en particular. Los primeros cobran un sueldo básico con sus respectivas deducciones y retenciones, y los segundos tienen un valor hora y cobrar por las horas trabajadas.

### Empleados
* El sistema debe permitir administrar los datos personales de los empleados del gimnasio.
* Los empleados al igual que los profesores de horario completo, tienen un puesto y cobran un sueldo básico con sus deducciones y retenciones.

### Liquidación de sueldos
* Mensualmente el sistema realiza la liquidación de sueldos a los empleados y profesores.
* El sistema debe registrar  las escalas salariales  y sus novedades.

### Validar ingreso
* A cada socio se le entrega una credencial al asociarse la cual debe pasar por los molinetes de ingreso cada vez que llega al gimnasio.
  * El sistema debe validar que:
  * El abono del socio este al día
  * El apto medico no esté vencido
  * El socio este habilitado para ingresar en ese horario en base a sus actividades contratadas.

### Notificaciones
* El sistema debe permitir enviar mails masivos a todos sus socios para informarles las distintas novedades.
* El sistema debe permitir el envío de mails a todos los socios o bien realizar filtros para notificar a cierto grupo. Por ejemplo, por suspensión de una clase envío de mail solamente a los socios inscriptos en esa actividad.

### Consulta de cronogramas
* El sistema debe permitir consultar los cronogramas a los socios para informarse de los distintos horarios de las actividades.
* El sistema debe permitir el acceso a través de multiples plataformas,  es decir: acceso web, terminales de consulta en el gimnasio, aplicaciones móviles.

### Inscripción
* El sistema debe permitir inscribir a un socio en distintas actividades. El procedimiento será: dar de alta al socio, asociarle un abono, registrar las actividades que llevara a cabo.
* El sistema debe permitir manejar dos tipos de inscripciones:
  * Normales: las comunes que realiza cualquier interesado
  * Corporativas: son aquellos convenios que se realizan con empresas que otorgan beneficios a sus empleados. En este caso, los abonos los paga la empresa y las inscripciones tienen un periodo de vigencia. Una vez finalizado ese periodo la inscripción queda dada de baja.

### Se pide:
Documentar el diseño del sistema e implementar utilizando la metodología de UP en base a las siguientes fases:

* Primera Fase: 
  * Administracion del gimnasio
* Segunda Fase:
  * Liquidacion de sueldos
  * Consulta de cronogramas.

Notificaciones
Actualizacion de BD schedulado.
