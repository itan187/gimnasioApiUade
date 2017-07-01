package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;

public class SocioAlta extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelNombre;
	private JLabel jLabelDomicilio;
	private JLabel jLabelTelefono;
	private JLabel jLabelEmail;
	private JLabel jLabelDocumento;
	
	private JTextField fieldNombre;
	private JTextField fieldDomicilio;
	private JTextField fieldTelefono;
	private JTextField fieldEmail;
	private JTextField fieldDocumento;	
	
	private JButton buttonAceptar;
	
	private SocioController sistema;
	
	public SocioAlta (SocioController s) {
		super();
		sistema = s;
		initGui();
	}
	
	private void initGui () {
		
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			/**************************************************************
			 *						LABEL's
			**************************************************************/
			{
				jLabelNombre = new JLabel();
				getContentPane().add(jLabelNombre);
				jLabelNombre.setText("Nombre y Apellido:");
				jLabelNombre.setBounds(21, 40, 180, 28);
				jLabelNombre.setVisible(true);
			}
			{
				jLabelDomicilio = new JLabel();
				getContentPane().add(jLabelDomicilio);
				jLabelDomicilio.setText("Domicilio:");
				jLabelDomicilio.setBounds(21, 80, 180, 28);
				jLabelDomicilio.setVisible(true);
			}
			{
				jLabelTelefono = new JLabel();
				getContentPane().add(jLabelTelefono);
				jLabelTelefono.setText("Teléfono:");
				jLabelTelefono.setBounds(21, 120, 180, 28);
				jLabelTelefono.setVisible(true);
			}
			{
				jLabelEmail = new JLabel();
				getContentPane().add(jLabelEmail);
				jLabelEmail.setText("E-mail:");
				jLabelEmail.setBounds(21, 160, 180, 28);
				jLabelEmail.setVisible(true);
			}
			{
				jLabelDocumento = new JLabel();
				getContentPane().add(jLabelDocumento);
				jLabelDocumento.setText("Documento:");
				jLabelDocumento.setBounds(21, 200, 180, 28);
				jLabelDocumento.setVisible(true);
			}
			/**************************************************************
			 *						FIELDS
			**************************************************************/
			{
				fieldNombre = new JTextField();
				getContentPane().add(fieldNombre);
				fieldNombre.setBounds(200, 40, 120, 28);
				fieldNombre.setVisible(true);
			}
			{
				fieldDomicilio = new JTextField();
				getContentPane().add(fieldDomicilio);
				fieldDomicilio.setBounds(200, 80, 120, 28);
			}
			{
				fieldTelefono = new JTextField();
				getContentPane().add(fieldTelefono);
				fieldTelefono.setBounds(200, 120, 120, 28);
			}
			{
				fieldEmail = new JTextField();
				getContentPane().add(fieldEmail);
				fieldEmail.setBounds(200, 160, 120, 28);
			}
			{
				fieldDocumento = new JTextField();
				getContentPane().add(fieldDocumento);
				fieldDocumento.setBounds(200, 200, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.setBounds(220, 240, 123, 28);
				buttonAceptar.setVisible(true);
				buttonAceptar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						if (fieldNombre.getText().equals("") || fieldDomicilio.getText().equals("") || fieldDocumento.getText().equals("") || fieldTelefono.getText().equals("") || fieldEmail.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar el socio.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							sistema.altaSocio(fieldNombre.getText(), fieldDomicilio.getText(), fieldTelefono.getText(), fieldEmail.getText(), Integer.parseInt(fieldDocumento.getText()));
						}
						setVisible(false);
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
