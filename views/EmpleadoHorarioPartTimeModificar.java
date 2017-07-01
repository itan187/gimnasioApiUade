package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.RrhhController;
import models.Empleado;

public class EmpleadoHorarioPartTimeModificar extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelNombre;
	private JLabel jLabelMail;
	private JLabel jLabelTelefono;
	private JLabel jLabelDomicilio;
	private JLabel jLabelEscalaSalarial;
	private JLabel jLabelSueldo;
	
	private JTextField fieldDocumentoBuscar;
	private JTextField fieldNombre;
	private JTextField fieldMail;
	private JTextField fieldTelefono;
	private JTextField fieldDomicilio;
	private JTextField fieldEscalaSalarial;
	private JTextField fieldSueldo;
	
	private JButton buttonBuscar;
	private JButton buttonModificar;
	
	private RrhhController sistema;
	
	public EmpleadoHorarioPartTimeModificar (RrhhController s) {
		super();
		sistema = s;
		initGui();
	}
	
	private void initGui () {
			
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			
			{
				jLabelNombre = new JLabel();
				getContentPane().add(jLabelNombre);
				jLabelNombre.setText("Nombre:");
				jLabelNombre.setBounds(21, 42, 63, 28);
				jLabelNombre.setVisible(false);
			}
			{
				fieldNombre = new JTextField();
				getContentPane().add(fieldNombre);
				fieldNombre.setBounds(119, 42, 210, 28);
				fieldNombre.setVisible(false);
			}
			{
				jLabelMail = new JLabel();
				getContentPane().add(jLabelMail);
				jLabelMail.setText("E-mail:");
				jLabelMail.setBounds(21, 82, 63, 28);
				jLabelMail.setVisible(false);
			}
			{
				fieldMail = new JTextField();
				getContentPane().add(fieldMail);
				fieldMail.setBounds(119, 82, 210, 28);
				fieldMail.setVisible(false);
			}
			{
				jLabelTelefono = new JLabel();
				getContentPane().add(jLabelTelefono);
				jLabelTelefono.setText("Teléfono:");
				jLabelTelefono.setBounds(21, 122, 63, 28);
				jLabelTelefono.setVisible(false);
			}
			{
				fieldTelefono = new JTextField();
				getContentPane().add(fieldTelefono);
				fieldTelefono.setBounds(119, 122, 210, 28);
				fieldTelefono.setVisible(false);
			}
			{
				jLabelDomicilio = new JLabel();
				getContentPane().add(jLabelDomicilio);
				jLabelDomicilio.setText("Domicilio:");
				jLabelDomicilio.setBounds(21, 162, 63, 28);
				jLabelDomicilio.setVisible(false);
			}
			{
				fieldDomicilio = new JTextField();
				getContentPane().add(fieldDomicilio);
				fieldDomicilio.setBounds(119, 162, 210, 28);
				fieldDomicilio.setVisible(false);
			}
			{
				jLabelEscalaSalarial = new JLabel();
				getContentPane().add(jLabelEscalaSalarial);
				jLabelEscalaSalarial.setText("Escala Salarial:");
				jLabelEscalaSalarial.setBounds(21, 202, 180, 28);
				jLabelEscalaSalarial.setVisible(true);
			}
			{
				fieldEscalaSalarial = new JTextField();
				getContentPane().add(fieldEscalaSalarial);
				fieldEscalaSalarial.setBounds(119, 202, 210, 28);
				fieldEscalaSalarial.setVisible(false);
			}
			{
				jLabelSueldo = new JLabel();
				getContentPane().add(jLabelSueldo);
				jLabelSueldo.setText("Sueldo Básico:");
				jLabelSueldo.setBounds(21, 242, 63, 28);
				jLabelSueldo.setVisible(false);
			}
			{
				fieldSueldo = new JTextField();
				getContentPane().add(fieldSueldo);
				fieldSueldo.setBounds(119, 242, 210, 28);
				fieldSueldo.setVisible(false);
			}
			/**
			 * Acción para modificar un empleado profesor part time
			 */
			{
				buttonModificar = new JButton();
				getContentPane().add(buttonModificar);
				buttonModificar.setText("Modificar Empleado");
				buttonModificar.setBounds(260, 217, 123, 28);
				buttonModificar.setVisible(false);
				buttonModificar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						sistema.modificarEmpleado(
								fieldNombre.getText(), 
								Integer.parseInt(fieldDocumentoBuscar.getText()), 
								fieldMail.getText(), 
								fieldTelefono.getText(),
								fieldDomicilio.getText(),
								fieldEscalaSalarial.getText(),
								Float.parseFloat(fieldSueldo.getText())
							);
						setVisible(false);
					}
				});
			}
			/**
			 * Buscar el empleado a través del documento
			 */
			{
				jLabelSearch = new JLabel();
				getContentPane().add(jLabelSearch);
				jLabelSearch.setText("Ingrese el documento:");
				jLabelSearch.setBounds(21, 7, 98, 28);
			}
			{
				fieldDocumentoBuscar = new JTextField();
				getContentPane().add(fieldDocumentoBuscar);
				fieldDocumentoBuscar.setBounds(140, 7, 147, 28);
			}
			{
				buttonBuscar = new JButton();
				getContentPane().add(buttonBuscar);
				buttonBuscar.setText("Buscar");
				buttonBuscar.setBounds(301, 7, 77, 28);
				buttonBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						Empleado admin = sistema.buscarEmpleado(Integer.parseInt(fieldDocumentoBuscar.getText()));
						
						if (admin != null) {
							jLabelNombre.setVisible(true);
							jLabelMail.setVisible(true);
							jLabelTelefono.setVisible(true);
							jLabelDomicilio.setVisible(true);
							jLabelEscalaSalarial.setVisible(true);
							jLabelSueldo.setVisible(true);
							
							fieldNombre.setVisible(true);
							fieldNombre.setEnabled(false);
							fieldMail.setVisible(true);
							fieldMail.setEnabled(false);
							fieldTelefono.setVisible(true);
							fieldTelefono.setEnabled(false);
							fieldDomicilio.setVisible(true);
							fieldDomicilio.setEnabled(false);
							jLabelEscalaSalarial.setVisible(true);
							jLabelEscalaSalarial.setEnabled(false);
							fieldSueldo.setVisible(true);
							fieldSueldo.setEnabled(false);
							
							buttonModificar.setVisible(true);
						}
					}
				});
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}