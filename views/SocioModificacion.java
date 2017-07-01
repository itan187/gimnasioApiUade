package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;
import models.Socio;

public class SocioModificacion extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelNombre;
	private JLabel jLabelDomicilio;
	private JLabel jLabelTelefono;
	private JLabel jLabelEmail;
	private JLabel jLabelEstado;
	
	private JTextField fieldDocumentoBuscar;
	private JTextField fieldNombre;
	private JTextField fieldDomicilio;
	private JTextField fieldTelefono;
	private JTextField fieldEmail;
	private JTextField fieldEstado;
	
	private JButton buttonBuscar;
	private JButton buttonModificar;
	
	private SocioController sistema;
	
	public SocioModificacion (SocioController s) {
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
				jLabelNombre.setBounds(21, 40, 63, 28);
				jLabelNombre.setVisible(false);
			}
			{
				fieldNombre = new JTextField();
				getContentPane().add(fieldNombre);
				fieldNombre.setBounds(119, 40, 210, 28);
				fieldNombre.setVisible(false);
			}
			{
				jLabelDomicilio = new JLabel();
				getContentPane().add(jLabelDomicilio);
				jLabelDomicilio.setText("Domicilio:");
				jLabelDomicilio.setBounds(21, 90, 63, 28);
				jLabelDomicilio.setVisible(false);
			}
			{
				fieldDomicilio = new JTextField();
				getContentPane().add(fieldDomicilio);
				fieldDomicilio.setBounds(119, 90, 210, 28);
				fieldDomicilio.setVisible(false);
			}
			{
				jLabelTelefono = new JLabel();
				getContentPane().add(jLabelTelefono);
				jLabelTelefono.setText("Teléfono:");
				jLabelTelefono.setBounds(21, 140, 63, 28);
				jLabelTelefono.setVisible(false);
			}
			{
				fieldTelefono = new JTextField();
				getContentPane().add(fieldTelefono);
				fieldTelefono.setBounds(119, 140, 210, 28);
				fieldTelefono.setVisible(false);
			}
			{
				jLabelEmail = new JLabel();
				getContentPane().add(jLabelEmail);
				jLabelEmail.setText("E-mail:");
				jLabelEmail.setBounds(21, 190, 63, 28);
				jLabelEmail.setVisible(false);
			}
			{
				fieldEmail = new JTextField();
				getContentPane().add(fieldEmail);
				fieldEmail.setBounds(119, 190, 210, 28);
				fieldEmail.setVisible(false);
			}
			{
				jLabelEstado = new JLabel();
				getContentPane().add(jLabelEstado);
				jLabelEstado.setText("Estado:");
				jLabelEstado.setBounds(21, 240, 63, 28);
				jLabelEstado.setVisible(false);
			}
			{
				fieldEstado = new JTextField();
				getContentPane().add(fieldEstado);
				fieldEstado.setBounds(119, 240, 210, 28);
				fieldEstado.setVisible(false);
			}
			/**
			 * Acción para dar de modificar un socio
			 */
			{
				buttonModificar = new JButton();
				getContentPane().add(buttonModificar);
				buttonModificar.setText("Modificar Socio");
				buttonModificar.setBounds(260, 217, 123, 28);
				buttonModificar.setVisible(false);
				buttonModificar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						sistema.modificarSocio(
								fieldNombre.getText(), 
								fieldDomicilio.getText(), 
								fieldTelefono.getText(), 
								fieldEmail.getText(), 
								Integer.parseInt(fieldDocumentoBuscar.getText()),
								Boolean.parseBoolean(fieldEstado.getText())
						);
						setVisible(false);
					}
				});
			}
			
			/**
			 * Buscar un socio a través del documento único
			 */
			{
				jLabelSearch = new JLabel();
				getContentPane().add(jLabelSearch);
				jLabelSearch.setText("Ingrese el número de documento:");
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
						
						Socio socio = sistema.buscarSocio(Integer.parseInt(fieldDocumentoBuscar.getText()));
						
						if (socio != null) {
							jLabelNombre.setVisible(true);
							jLabelDomicilio.setVisible(true);
							jLabelTelefono.setVisible(true);
							jLabelEmail.setVisible(true);
							
							fieldNombre.setVisible(true);
							fieldNombre.setEnabled(false);
							fieldDomicilio.setVisible(true);
							fieldDomicilio.setEnabled(false);
							fieldTelefono.setVisible(true);
							fieldTelefono.setEnabled(false);
							fieldEmail.setVisible(true);
							fieldEmail.setEnabled(false);
							
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
