
-- Crear db
CREATE DATABASE IF NOT EXISTS gimnasio
USE gimnasio;

-- abono
CREATE TABLE Abono (
	codigo INTEGER PRIMARY KEY,
	nombre VARCHAR(100),
	precio DECIMAL(10,2),
	vigencia DATE,
);

-- socio
CREATE TABLE Socio (
	documento INTEGER PRIMARY KEY,
	nombre VARCHAR(100),
	domicilio VARCHAR(100),
	telefono VARCHAR(100),
	mail VARCHAR(100),
	abono INTEGER,
	estado BIT DEFAULT 1,
	CONSTRAINT fk_abono FOREIGN KEY (abono) REFERENCES Abono(codigo)
);

-- empleado administrativo
CREATE TABLE EmpleadoAdministrativo (
	documento INTEGER PRIMARY KEY,
	nombre VARCHAR(100),
	mail VARCHAR(100),
	telefono VARCHAR(100),
	domicilio VARCHAR(100),
	escalaSalarial VARCHAR(100),
	sueldo DECIMAL(10,2),
);

-- empleado horario completo
CREATE TABLE EmpleadoHorarioCompleto (
	documento INTEGER PRIMARY KEY,
	nombre VARCHAR(100),
	mail VARCHAR(100),
	telefono VARCHAR(100),
	domicilio VARCHAR(100),
	escalaSalarial VARCHAR(100),
	sueldoBasico DECIMAL(10,2),
);

-- empleado part time
CREATE TABLE EmpleadoHorarioPartTime (
	documento INTEGER PRIMARY KEY,
	nombre VARCHAR(100),
	mail VARCHAR(100),
	telefono VARCHAR(100),
	domicilio VARCHAR(100),
	escalaSalarial VARCHAR(100),
	valorHora DECIMAL(10,2),
);

-- socio inscripcion
CREATE TABLE SocioInscripcion (
	documento INTEGER,
	inscripcionNumero INTEGER,
	CONSTRAINT fk_documento FOREIGN KEY (documento) REFERENCES Socio(documento)
);

-- inscripcion normal
CREATE TABLE InscripcionNormal (
	numero INTEGER PRIMARY KEY,
	estado BIT DEFAULT 1
);

-- inscripcion corporativa
CREATE TABLE InscripcionCorporativa (
	numero INTEGER PRIMARY KEY,
	estado BIT DEFAULT 1,
	empresa VARCHAR(100),
	vigencia DATE
);

-- deporte
CREATE TABLE Deporte (
   codigo INTEGER PRIMARY KEY,
   titulo VARCHAR(100),
   descripcion VARCHAR(255),
);

-- actividad
CREATE TABLE Actividad (
	numeroActividad INTEGER PRIMARY KEY,
	numeroDeporte INTEGER,
	duracion INTEGER,
	lunes INTEGER,
	martes INTEGER,
	miercoles INTEGER,
	jueves INTEGER,
	viernes INTEGER,
	sabado INTEGER,
	domingo INTEGER,

	CONSTRAINT fk_numeroDeporte FOREIGN KEY (numeroDeporte) REFERENCES Deporte(codigo)
);

-- inscripcion actividades
CREATE TABLE InscripcionActividades (
	numero INTEGER,
	numeroActividad INTEGER,
	CONSTRAINT fk_numeroActividad FOREIGN KEY (numeroActividad) REFERENCES Actividad(numeroActividad)
);

-- actividad
CREATE TABLE ActividadProfesores (
	numeroActividad INTEGER,
	documento INTEGER,
	CONSTRAINT fk_numeroActividadProfesor FOREIGN KEY (numeroActividad) REFERENCES Actividad(numeroActividad),
);

-- certificado apto medico
CREATE TABLE CertificadoMedico (
	numAptoMedico INTEGER PRIMARY KEY,
	numSocio INTEGER,
	fechaCreacion Date,
	vencimiento Date,
	profesional VARCHAR(100),
	observaciones VARCHAR(100),
	estado BIT DEFAULT 1,
	CONSTRAINT fk_numeroSocioCertificado FOREIGN KEY (numSocio) REFERENCES Socio(documento),
);

-- liquidacion
CREATE TABLE liquidacion (
	numeroLiquidacion INTEGER PRIMARY KEY,
	numEmpleado INTEGER,
	fecha DATE,
	importe DECIMAL(10,2),
)