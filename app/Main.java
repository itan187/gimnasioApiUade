/****************************************************
 * Aplicaciones Interactivas 1C 2017
 * Trabajo Práctico - Gimnasio
 * GitHub: https://github.com/tomasmalio/GimnasioApiUade
 * 
 * @author	Tomas Malio, 
 * 			Jessica Lume, 
 * 			Sabrina Cannuli,
 * 			Martin Carrera, 
 * 			Barbara Zapatero,
 * 			Yesica Moran
 * 
 ****************************************************/
package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controllers.*;
import models.Liquidacion;
import views.*;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JMenuBar jMenuBar;

	private JMenu jMenuSocios;
	private JMenuItem jMenuSocioIngreso;
	private JMenuItem jMenuSocioAlta;
	private JMenuItem jMenuSocioBaja;
	private JMenuItem jMenuSocioModificar;
	
	private JMenu jMenuDeportes;
	private JMenuItem jMenuDeporteAlta;
	private JMenuItem jMenuDeporteBaja;
	private JMenuItem jMenuDeporteModificar;
	
	private JMenu jMenuEmpleados;
	private JMenuItem jMenuEmpleadoAdmAlta;
	private JMenuItem jMenuEmpleadoProfCompletoAlta;
	private JMenuItem jMenuEmpleadoProfPartTimeAlta;
	private JMenuItem jMenuEmpleadoAdmBaja;
	private JMenuItem jMenuEmpleadoProfCompletoBaja;
	private JMenuItem jMenuEmpleadoProfPartTimeBaja;
	private JMenuItem jMenuEmpleadoAdmModificar;
	private JMenuItem jMenuEmpleadoProfCompletoModificar;
	private JMenuItem jMenuEmpleadoProfPartTimeModificar;
	
	private JMenu jMenuAbonos;
	private JMenuItem jMenuAbonoAlta;
	private JMenuItem jMenuAbonoBaja;
	private JMenuItem jMenuAbonoModificar;
	
	private JMenu jMenuInscripciones;
	private JMenuItem jMenuInscripcionNormalAlta;
	private JMenuItem jMenuInscripcionBaja;
	private JMenuItem jMenuInscripcionNormalModificar;
	private JMenuItem jMenuInscripcionCorpoAlta;
	private JMenuItem jMenuInscripcionCorpoModificar;
	
	
	private JMenu jMenuLiquidacion;
	
	private JMenu jMenuSalir;
	
	private SocioController socioController;
	private ActividadController actividadController;
	private RrhhController rrhhController;
	
	public Main() {
		super();
		socioController 		= SocioController.getInstancia();
		actividadController 	= ActividadController.getInstancia();
		rrhhController 			= RrhhController.getInstancia();
		initGUI();
	}
	
    public static void main(String[] args) {
    	Main main = new Main();
    	main.setVisible(true);
    }
   
    private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
				jMenuBar = new JMenuBar();
				setJMenuBar(jMenuBar);
				jMenuBar.setPreferredSize(new java.awt.Dimension(392, 22));
				
				/**************************************************************
				 * 						SOCIOS
				 * 1) Validar Ingreso
				 * 2) Alta
				 * 3) Baja
				 * 4) Modificar
				 **************************************************************/
				jMenuSocios = new JMenu();
				jMenuBar.add(jMenuSocios);
				jMenuSocios.setText("Socios");
				jMenuSocios.setPreferredSize(new java.awt.Dimension(70, 21));
				jMenuSocios.setVisible(true);
				jMenuSocios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
						{
						}
					});


				/**
				 * 1) Validar Ingreso
				 **/
				jMenuSocioIngreso = new JMenuItem();
				jMenuSocios.add(jMenuSocioIngreso);
				jMenuSocioIngreso.setText("Ingreso");
				jMenuSocioIngreso.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt)
					{
						if (socioController != null) {
							SocioIngreso ingresoDeSocio= new SocioIngreso(socioController);
							ingresoDeSocio.setVisible(true);
							toFront();
						}
					}
				});

				/**
				 * 2) Alta de socio
				 **/
				jMenuSocioAlta = new JMenuItem();
				jMenuSocios.add(jMenuSocioAlta);
				jMenuSocioAlta.setText("Alta");
				jMenuSocioAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (socioController != null) {
							SocioAlta altaDeSocio = new SocioAlta(socioController);
							altaDeSocio.setVisible(true);
							toFront();
						}
					}
				});
				
				/**
				 * 3) Baja de socio
				 */
				jMenuSocioBaja = new JMenuItem();
				jMenuSocios.add(jMenuSocioBaja);
				jMenuSocioBaja.setText("Baja");
				jMenuSocioBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						SocioBaja bajaDeSocio = new SocioBaja(socioController);
						bajaDeSocio.setVisible(true);
						toFront();
					}
				});
				/**
				 * 4) Modificar socio
				 */
				jMenuSocioModificar = new JMenuItem();
				jMenuSocios.add(jMenuSocioModificar);
				jMenuSocioModificar.setText("Modificar");
				jMenuSocioModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						SocioModificacion modificarSocio = new SocioModificacion(socioController);
						modificarSocio.setVisible(true);
						toFront();
					}
				});
				
				/**************************************************************
				 * 						DEPORTES
				 * 1) Alta
				 * 2) Baja
				 * 3) Modificar
				 **************************************************************/
				jMenuDeportes = new JMenu();
				jMenuBar.add(jMenuDeportes);
				jMenuDeportes.setText("Deportes");
				jMenuDeportes.setPreferredSize(new java.awt.Dimension(80, 21));
				jMenuDeportes.setVisible(true);
				jMenuDeportes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
						{
						}
					});
				/**
				 * 1) Alta de deporte
				 **/
				jMenuDeporteAlta = new JMenuItem();
				jMenuDeportes.add(jMenuDeporteAlta);
				jMenuDeporteAlta.setText("Alta");
				jMenuDeporteAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (actividadController != null) {
							DeporteAlta altaDeDeporte = new DeporteAlta(actividadController);
							altaDeDeporte.setVisible(true);
							toFront();
						}
					}
				});
				
				/**
				 * 2) Baja de deporte
				 */
				jMenuDeporteBaja = new JMenuItem();
				jMenuDeportes.add(jMenuDeporteBaja);
				jMenuDeporteBaja.setText("Baja");
				jMenuDeporteBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						DeporteBaja bajaDeDeporte = new DeporteBaja(actividadController);
						bajaDeDeporte.setVisible(true);
						toFront();
					}
				});
				/**
				 * 2) Modificar deporte
				 */
				jMenuDeporteModificar = new JMenuItem();
				jMenuDeportes.add(jMenuDeporteModificar);
				jMenuDeporteModificar.setText("Modificar");
				jMenuDeporteModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						DeporteModificar modificarDeporte = new DeporteModificar(actividadController);
						modificarDeporte.setVisible(true);
						toFront();
					}
				});
				
				/**************************************************************
				 * 						EMPLEADOS
				 * 1) Alta empleado administrativo
				 * 2) Baja empleado administrativo
				 * 3) Modificar empleado administrativo
				 * 4) Alta empleado profesor full time
				 * 5) Baja empleado profesor full time
				 * 6) Modificar empleado profesor full time
				 * 7) Alta empleado profesor part time
				 * 8) Baja empleado profesor part time
				 * 9) Modificar empleado profesor part time
				 * 
				 **************************************************************/
				jMenuEmpleados = new JMenu();
				jMenuBar.add(jMenuEmpleados);
				jMenuEmpleados.setText("Empleados");
				jMenuEmpleados.setPreferredSize(new java.awt.Dimension(100, 21));
				jMenuEmpleados.setVisible(true);
				jMenuEmpleados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
						{
						}
					});
				/**
				 * 1) Alta de empleado administativo
				 **/
				jMenuEmpleadoAdmAlta = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoAdmAlta);
				jMenuEmpleadoAdmAlta.setText("Alta Administrativo");
				jMenuEmpleadoAdmAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (rrhhController != null) {
							EmpleadoAdminAlta altaDeEmpleadoAdmin = new EmpleadoAdminAlta(rrhhController);
							altaDeEmpleadoAdmin.setVisible(true);
							toFront();
						}
					}
				});
				/**
				 * 2) Baja de empleado administrativo
				 */
				jMenuEmpleadoAdmBaja = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoAdmBaja);
				jMenuEmpleadoAdmBaja.setText("Baja Administrativo");
				jMenuEmpleadoAdmBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						EmpleadoAdminBaja bajaEmpleadoAdmin = new EmpleadoAdminBaja(rrhhController);
						bajaEmpleadoAdmin.setVisible(true);
						toFront();
					}
				});
				/**
				 * 3) Modificar de empleado administrativo
				 **/
				jMenuEmpleadoAdmModificar = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoAdmModificar);
				jMenuEmpleadoAdmModificar.setText("Modificar Administrativo");
				jMenuEmpleadoAdmModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						EmpleadoAdminModificar modificarEmpleadoAdmin = new EmpleadoAdminModificar(rrhhController);
						modificarEmpleadoAdmin.setVisible(true);
						toFront();
					}
				});
				/**
				 * 4) Alta de empleado profesor full time
				 **/
				jMenuEmpleadoProfCompletoAlta = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoProfCompletoAlta);
				jMenuEmpleadoProfCompletoAlta.setText("Alta Profesor Full-Time");
				jMenuEmpleadoProfCompletoAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (rrhhController != null) {
							EmpleadoHorarioCompletoAlta altaDeEmpleadoProfHorarioFull = new EmpleadoHorarioCompletoAlta(rrhhController);
							altaDeEmpleadoProfHorarioFull.setVisible(true);
							toFront();
						}
					}
				});
				/**
				 * 5) Baja de empleado profesor full time
				 */
				jMenuEmpleadoProfCompletoBaja = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoProfCompletoBaja);
				jMenuEmpleadoProfCompletoBaja.setText("Baja Profesor Full-Time");
				jMenuEmpleadoProfCompletoBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						EmpleadoHorarioCompletoBaja bajaProfFullTime = new EmpleadoHorarioCompletoBaja(rrhhController);
						bajaProfFullTime.setVisible(true);
						toFront();
					}
				});
				/**
				 * 6) Modificar de empleado profesor full time
				 **/
				jMenuEmpleadoProfCompletoModificar = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoProfCompletoModificar);
				jMenuEmpleadoProfCompletoModificar.setText("Modificar Prof Full Time");
				jMenuEmpleadoProfCompletoModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						EmpleadoHorarioCompletoModificar modificarEmpleadoProfFullTime = new EmpleadoHorarioCompletoModificar(rrhhController);
						modificarEmpleadoProfFullTime.setVisible(true);
						toFront();
					}
				});
				/**
				 * 7) Alta de empleado profesor part time
				 **/
				jMenuEmpleadoProfPartTimeAlta = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoProfPartTimeAlta);
				jMenuEmpleadoProfPartTimeAlta.setText("Alta Profesor Part-Time");
				jMenuEmpleadoProfPartTimeAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (rrhhController != null) {
							EmpleadoHorarioPartTimeAlta altaDeEmpleadoProfPartTime = new EmpleadoHorarioPartTimeAlta(rrhhController);
							altaDeEmpleadoProfPartTime.setVisible(true);
							toFront();
						}
					}
				});
				/**
				 * 8) Baja de empleado profesor part time
				 */
				jMenuEmpleadoProfPartTimeBaja = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoProfPartTimeBaja);
				jMenuEmpleadoProfPartTimeBaja.setText("Baja Profesor Part-Time");
				jMenuEmpleadoProfPartTimeBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						EmpleadoHorarioPartTimeBaja bajaProfPartTime = new EmpleadoHorarioPartTimeBaja(rrhhController);
						bajaProfPartTime.setVisible(true);
						toFront();
					}
				});
				/**
				 * 9) Modificar de empleado profesor part time
				 **/
				jMenuEmpleadoProfPartTimeModificar = new JMenuItem();
				jMenuEmpleados.add(jMenuEmpleadoProfPartTimeModificar);
				jMenuEmpleadoProfPartTimeModificar.setText("Modificar Prof Part Time");
				jMenuEmpleadoProfPartTimeModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						EmpleadoHorarioPartTimeModificar modificarEmpleadoProfPartTime = new EmpleadoHorarioPartTimeModificar(rrhhController);
						modificarEmpleadoProfPartTime.setVisible(true);
						toFront();
					}
				});
				
				/**************************************************************
				 * 						ABONOS
				 * 1) Alta
				 * 2) Baja
				 * 3) Modificar
				 **************************************************************/
				jMenuAbonos = new JMenu();
				jMenuBar.add(jMenuAbonos);
				jMenuAbonos.setText("Abonos");
				jMenuAbonos.setPreferredSize(new java.awt.Dimension(70, 21));
				jMenuAbonos.setVisible(true);
				jMenuAbonos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
						{
						}
					});
				/**
				 * 1) Alta de abonos
				 **/
				jMenuAbonoAlta = new JMenuItem();
				jMenuAbonos.add(jMenuAbonoAlta);
				jMenuAbonoAlta.setText("Alta");
				jMenuAbonoAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (socioController != null) {
							AbonoAlta altaDeAbono = new AbonoAlta(socioController);
							altaDeAbono.setVisible(true);
							toFront();
						}
					}
				});
				/**
				 * 2) Baja de abonos
				 */
				jMenuAbonoBaja = new JMenuItem();
				jMenuAbonos.add(jMenuAbonoBaja);
				jMenuAbonoBaja.setText("Baja");
				jMenuAbonoBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AbonoBaja bajaDeAbono = new AbonoBaja(socioController);
						bajaDeAbono.setVisible(true);
						toFront();
					}
				});
				/**
				 * 3) Modificar abono
				 */
				jMenuAbonoModificar = new JMenuItem();
				jMenuAbonos.add(jMenuAbonoModificar);
				jMenuAbonoModificar.setText("Modificar");
				jMenuAbonoModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AbonoModificar modificarAbono = new AbonoModificar(socioController);
						modificarAbono.setVisible(true);
						toFront();
					}
				});
				
				/**************************************************************
				 * 						INSCRIPCIONES
				 * 1) Alta inscripción normal
				 * 2) Alta inscripción corporativa
				 * 3) Baja inscripción (normal | corporativa)
				 * 4) Modificar inscripción normal
				 * 5) Modificar inscripción corporativa
				 * 
				 **************************************************************/
				jMenuInscripciones = new JMenu();
				jMenuBar.add(jMenuInscripciones);
				jMenuInscripciones.setText("Inscripciones");
				jMenuInscripciones.setPreferredSize(new java.awt.Dimension(120, 21));
				jMenuInscripciones.setVisible(true);
				jMenuInscripciones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
						{
							
						}
					});
				/**
				 * 1) Alta de inscripción normal
				 **/
				jMenuInscripcionNormalAlta = new JMenuItem();
				jMenuInscripciones.add(jMenuInscripcionNormalAlta);
				jMenuInscripcionNormalAlta.setText("Alta Normal");
				jMenuInscripcionNormalAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (socioController != null) {
							InscripcionNormalAlta altaDeInscripcionNormal = new InscripcionNormalAlta(socioController);
							altaDeInscripcionNormal.setVisible(true);
							toFront();
						}
					}
				});
				/**
				 * 2) Alta de inscripción corporativa
				 **/
				jMenuInscripcionCorpoAlta = new JMenuItem();
				jMenuInscripciones.add(jMenuInscripcionCorpoAlta);
				jMenuInscripcionCorpoAlta.setText("Alta Corporativa");
				jMenuInscripcionCorpoAlta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						if (socioController != null) {
							InscripcionCorpoAlta altaDeInscripcionCorpo = new InscripcionCorpoAlta(socioController);
							altaDeInscripcionCorpo.setVisible(true);
							toFront();
						}
					}
				});
				/**
				 * 3) Baja de inscripción (normal | corporativo)
				 */
				jMenuInscripcionBaja = new JMenuItem();
				jMenuInscripciones.add(jMenuInscripcionBaja);
				jMenuInscripcionBaja.setText("Baja");
				jMenuInscripcionBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						InscripcionBaja bajaInscripcionNormal = new InscripcionBaja(socioController);
						bajaInscripcionNormal.setVisible(true);
						toFront();
					}
				});
				/**
				 * 4) Modificar inscripción normal
				 */
				jMenuInscripcionNormalModificar = new JMenuItem();
				jMenuInscripciones.add(jMenuInscripcionNormalModificar);
				jMenuInscripcionNormalModificar.setText("Modificar Normal");
				jMenuInscripcionNormalModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						InscripcionNormalModificar modificarInscripcionNormal = new InscripcionNormalModificar(socioController);
						modificarInscripcionNormal.setVisible(true);
						toFront();
					}
				});
				/**
				 * 5) Modificar inscripción corporativo
				 */
				jMenuInscripcionCorpoModificar = new JMenuItem();
				jMenuInscripciones.add(jMenuInscripcionCorpoModificar);
				jMenuInscripcionCorpoModificar.setText("Modificar Corporativo");
				jMenuInscripcionCorpoModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						InscripcionCorpoModificar modificarInscripcionCorpo = new InscripcionCorpoModificar(socioController);
						modificarInscripcionCorpo.setVisible(true);
						toFront();
					}
				});
				
				
				/**************************************************************
				 * 						LIQUIDACION
				 **************************************************************/
				jMenuLiquidacion = new JMenu();
				jMenuLiquidacion.add(jMenuLiquidacion);
				jMenuLiquidacion.setText("Liquidación");
				jMenuLiquidacion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						//Liquidacion efectuarLiquidacion = new InscripcionCorpoModificar(socioController);
						//modificarInscripcionCorpo.setVisible(true);
						toFront();
					}
				});
				
				/**************************************************************
				 * 						SALIR
				 **************************************************************/
				jMenuSalir = new JMenu();
				jMenuBar.add(jMenuSalir);
				jMenuSalir.setText("Salir");
				jMenuSalir.setVisible(true);
				jMenuSalir.addMenuListener(new MenuListener() {
					public void menuSelected(MenuEvent evt) {
						System.exit(0);
					}
					public void menuDeselected(MenuEvent evt) {
							
					}
					public void menuCanceled(MenuEvent evt) {
							
						}
					});
				
			pack();
			setSize(600, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}