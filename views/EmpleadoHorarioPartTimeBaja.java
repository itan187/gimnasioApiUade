package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.RrhhController;
import models.Empleado;

public class EmpleadoHorarioPartTimeBaja extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelNombre;
	private JLabel jLabelMail;
	private JLabel jLabelTelefono;
	private JLabel jLabelDomicilio;
	private JLabel jLabelValorHora;
	
	private JTextField fieldDocumentoBuscar;
	private JTextField fieldNombre;
	private JTextField fieldMail;
	private JTextField fieldTelefono;
	private JTextField fieldDomicilio;
	private JTextField fieldValorHora;
	
	private JButton buttonBuscar;
	private JButton buttonBaja;
	
	private RrhhController sistema;
	
	public EmpleadoHorarioPartTimeBaja (RrhhController s) {
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
				jLabelValorHora = new JLabel();
				getContentPane().add(jLabelValorHora);
				jLabelValorHora.setText("Valor Hora:");
				jLabelValorHora.setBounds(21, 162, 63, 28);
				jLabelValorHora.setVisible(false);
			}
			{
				fieldValorHora = new JTextField();
				getContentPane().add(fieldValorHora);
				fieldValorHora.setBounds(119, 162, 210, 28);
				fieldValorHora.setVisible(false);
			}
			/**
			 * Acción para dar de baja a un empleado administrativo
			 */
			{
				buttonBaja = new JButton();
				getContentPane().add(buttonBaja);
				buttonBaja.setText("Eliminar Deporte");
				buttonBaja.setBounds(260, 217, 123, 28);
				buttonBaja.setVisible(false);
				buttonBaja.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						sistema.bajaEmpleados(Integer.parseInt(fieldDocumentoBuscar.getText()));
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
						
						Empleado profPartTime = sistema.buscarEmpleado(Integer.parseInt(fieldDocumentoBuscar.getText()));
						
						if (profPartTime != null) {
							jLabelNombre.setVisible(true);
							jLabelMail.setVisible(true);
							jLabelTelefono.setVisible(true);
							jLabelDomicilio.setVisible(true);
							jLabelValorHora.setVisible(true);
							
							fieldNombre.setVisible(true);
							fieldNombre.setEnabled(false);
							fieldMail.setVisible(true);
							fieldMail.setEnabled(false);
							fieldTelefono.setVisible(true);
							fieldTelefono.setEnabled(false);
							fieldDomicilio.setVisible(true);
							fieldDomicilio.setEnabled(false);
							fieldValorHora.setVisible(true);
							fieldValorHora.setEnabled(false);
							
							buttonBaja.setVisible(true);
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
