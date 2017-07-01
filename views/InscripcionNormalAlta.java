package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;

public class InscripcionNormalAlta extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelNumero;
	private JLabel jLabelEstado;
	private JLabel jLabelClases;
	
	private JTextField fieldNumero;
	private JTextField fieldEstado;
	private JTextField fieldClases;
	
	private JButton buttonAceptar;
	
	private SocioController sistema;

	public InscripcionNormalAlta (SocioController s) {
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
				jLabelNumero = new JLabel();
				getContentPane().add(jLabelNumero);
				jLabelNumero.setText("Número:");
				jLabelNumero.setBounds(21, 42, 180, 28);
				jLabelNumero.setVisible(true);
			}
			{
				jLabelEstado = new JLabel();
				getContentPane().add(jLabelEstado);
				jLabelEstado.setText("Estado:");
				jLabelEstado.setBounds(21, 80, 180, 28);
				jLabelEstado.setVisible(true);
			}
			{
				jLabelClases = new JLabel();
				getContentPane().add(jLabelClases);
				jLabelClases.setText("Clases:");
				jLabelClases.setBounds(21, 120, 180, 28);
				jLabelClases.setVisible(true);
			}
			
			/**************************************************************
			 *						FIELDS
			**************************************************************/
			{
				fieldNumero = new JTextField();
				getContentPane().add(fieldNumero);
				fieldNumero.setBounds(200, 42, 120, 28);
				fieldNumero.setVisible(true);
			}
			{
				fieldEstado = new JTextField();
				getContentPane().add(fieldEstado);
				fieldEstado.setBounds(200, 80, 120, 28);
			}
			{
				fieldClases = new JTextField();
				getContentPane().add(fieldClases);
				fieldClases.setBounds(200, 122, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.setBounds(220, 200, 123, 28);
				buttonAceptar.setVisible(true);
				buttonAceptar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						if (fieldNumero.getText().equals("") || fieldEstado.getText().equals("") || fieldClases.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar la inscripción.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							sistema.altaInscripcionNormal(Integer.parseInt(fieldNumero.getText()), Boolean.parseBoolean(fieldEstado.getText()), fieldClases.getText());
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
