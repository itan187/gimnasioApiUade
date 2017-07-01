package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;
import models.Socio;

public class SocioBaja extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelNombre;
	private JLabel jLabelDomicilio;
	private JLabel jLabelTelefono;
	private JLabel jLabelEmail;
	
	private JTextField fieldDocumentoBuscar;
	private JTextField fieldNombre;
	private JTextField fieldDomicilio;
	private JTextField fieldTelefono;
	private JTextField fieldEmail;
	
	private JButton buttonBuscar;
	private JButton buttonBaja;
	
	private SocioController sistema;
	
	public SocioBaja (SocioController s) {
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
				jLabelDomicilio = new JLabel();
				getContentPane().add(jLabelDomicilio);
				jLabelDomicilio.setText("Domicilio:");
				jLabelDomicilio.setBounds(21, 91, 63, 28);
				jLabelDomicilio.setVisible(false);
			}
			{
				fieldDomicilio = new JTextField();
				getContentPane().add(fieldDomicilio);
				fieldDomicilio.setBounds(119, 91, 210, 28);
				fieldDomicilio.setVisible(false);
			}
			{
				jLabelTelefono = new JLabel();
				getContentPane().add(jLabelTelefono);
				jLabelTelefono.setText("Teléfono:");
				jLabelTelefono.setBounds(21, 42, 63, 28);
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
				jLabelEmail.setBounds(21, 42, 63, 28);
				jLabelEmail.setVisible(false);
			}
			{
				fieldEmail = new JTextField();
				getContentPane().add(fieldEmail);
				fieldEmail.setBounds(119, 189, 210, 28);
				fieldEmail.setVisible(false);
			}
			
			/**
			 * Acción para dar de baja a un socio
			 */
			{
				buttonBaja = new JButton();
				getContentPane().add(buttonBaja);
				buttonBaja.setText("Eliminar Socio");
				buttonBaja.setBounds(260, 217, 123, 28);
				buttonBaja.setVisible(false);
				buttonBaja.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						sistema.bajaSocio(Integer.parseInt(fieldDocumentoBuscar.getText()));
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
