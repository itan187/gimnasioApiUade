package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.RrhhController;

public class EmpleadoHorarioCompletoAlta extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelNombre;
	private JLabel jLabelDocumento;
	private JLabel jLabelMail;
	private JLabel jLabelTelefono;
	private JLabel jLabelDomicilio;
	private JLabel jLabelEscalaSalarial;
	private JLabel jLabelSueldo;
	
	private JTextField fieldNombre;
	private JTextField fieldDocumento;
	private JTextField fieldMail;
	private JTextField fieldTelefono;
	private JTextField fieldDomicilio;
	private JTextField fieldEscalaSalarial;
	private JTextField fieldSueldo;
	
	private JButton buttonAceptar;
	
	private RrhhController sistema;
	
	public EmpleadoHorarioCompletoAlta (RrhhController s) {
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
				jLabelNombre.setText("Nombre:");
				jLabelNombre.setBounds(21, 40, 180, 28);
				jLabelNombre.setVisible(true);
			}
			{
				jLabelDocumento = new JLabel();
				getContentPane().add(jLabelDocumento);
				jLabelDocumento.setText("Documento:");
				jLabelDocumento.setBounds(21, 80, 180, 28);
				jLabelDocumento.setVisible(true);
			}
			{
				jLabelMail = new JLabel();
				getContentPane().add(jLabelMail);
				jLabelMail.setText("Email:");
				jLabelMail.setBounds(21, 120, 180, 28);
				jLabelMail.setVisible(true);
			}
			{
				jLabelTelefono = new JLabel();
				getContentPane().add(jLabelTelefono);
				jLabelTelefono.setText("Teléfono:");
				jLabelTelefono.setBounds(21, 160, 180, 28);
				jLabelTelefono.setVisible(true);
			}
			{
				jLabelDomicilio = new JLabel();
				getContentPane().add(jLabelDomicilio);
				jLabelDomicilio.setText("Domicilio:");
				jLabelDomicilio.setBounds(21, 200, 180, 28);
				jLabelDomicilio.setVisible(true);
			}
			{
				jLabelEscalaSalarial = new JLabel();
				getContentPane().add(jLabelEscalaSalarial);
				jLabelEscalaSalarial.setText("Escala Salarial:");
				jLabelEscalaSalarial.setBounds(21, 240, 180, 28);
				jLabelEscalaSalarial.setVisible(true);
			}
			{
				jLabelSueldo = new JLabel();
				getContentPane().add(jLabelSueldo);
				jLabelSueldo.setText("Sueldo:");
				jLabelSueldo.setBounds(21, 280, 180, 28);
				jLabelSueldo.setVisible(true);
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
				fieldDocumento = new JTextField();
				getContentPane().add(fieldDocumento);
				fieldDocumento.setBounds(200, 80, 120, 28);
			}
			{
				fieldMail = new JTextField();
				getContentPane().add(fieldMail);
				fieldMail.setBounds(200, 120, 120, 28);
			}
			{
				fieldTelefono = new JTextField();
				getContentPane().add(fieldTelefono);
				fieldTelefono.setBounds(200, 160, 120, 28);
			}
			{
				fieldDomicilio = new JTextField();
				getContentPane().add(fieldDomicilio);
				fieldDomicilio.setBounds(200, 200, 120, 28);
			}
			{
				fieldEscalaSalarial = new JTextField();
				getContentPane().add(fieldEscalaSalarial);
				fieldEscalaSalarial.setBounds(200, 240, 120, 28);
			}
			{
				fieldSueldo = new JTextField();
				getContentPane().add(fieldSueldo);
				fieldSueldo.setBounds(200, 280, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.setBounds(220, 320, 123, 28);
				buttonAceptar.setVisible(true);
				buttonAceptar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						if (fieldNombre.getText().equals("") || fieldDocumento.getText().equals("") || fieldMail.getText().equals("") || fieldTelefono.getText().equals("") || fieldDomicilio.getText().equals("") || fieldEscalaSalarial.getText().equals("") || fieldSueldo.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar el profesor de tiempo completo.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							sistema.altaEmpleadoProfFull(
									fieldNombre.getText(), 
									Integer.parseInt(fieldDocumento.getText()),
									fieldMail.getText(),
									fieldTelefono.getText(),
									fieldDomicilio.getText(),
									fieldEscalaSalarial.getText(),
									Float.parseFloat(fieldSueldo.getText())
								);
						}
						setVisible(false);
					}
				});
			}
			
			pack();
			setSize(400, 380);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
