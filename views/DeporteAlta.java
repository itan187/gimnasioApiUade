package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.ActividadController;

public class DeporteAlta extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelCodigo;
	private JLabel jLabelTitulo;
	private JLabel jLabelDescripcion;
	
	private JTextField fieldCodigo;
	private JTextField fieldTitulo;
	private JTextField fieldDescripcion;
	
	private JButton buttonAceptar;
	
	private ActividadController sistema;

	public DeporteAlta  (ActividadController s) {
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
				jLabelCodigo = new JLabel();
				getContentPane().add(jLabelCodigo);
				jLabelCodigo.setText("Código:");
				jLabelCodigo.setBounds(21, 42, 180, 28);
				jLabelCodigo.setVisible(true);
			}
			{
				jLabelTitulo = new JLabel();
				getContentPane().add(jLabelTitulo);
				jLabelTitulo.setText("Título:");
				jLabelTitulo.setBounds(21, 80, 180, 28);
				jLabelTitulo.setVisible(true);
			}
			{
				jLabelDescripcion = new JLabel();
				getContentPane().add(jLabelDescripcion);
				jLabelDescripcion.setText("Descripcion:");
				jLabelDescripcion.setBounds(21, 120, 180, 28);
				jLabelDescripcion.setVisible(true);
			}
			
			
			/**************************************************************
			 *						FIELDS
			**************************************************************/
			{
				fieldCodigo = new JTextField();
				getContentPane().add(fieldCodigo);
				fieldCodigo.setBounds(200, 42, 120, 28);
				fieldCodigo.setVisible(true);
			}
			{
				fieldTitulo = new JTextField();
				getContentPane().add(fieldTitulo);
				fieldTitulo.setBounds(200, 80, 120, 28);
			}
			{
				fieldDescripcion = new JTextField();
				getContentPane().add(fieldDescripcion);
				fieldDescripcion.setBounds(200, 122, 120, 28);
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
						if (fieldCodigo.getText().equals("") || fieldTitulo.getText().equals("") || fieldDescripcion.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar el deporte.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							sistema.altaDeporte(Integer.parseInt(fieldCodigo.getText()), fieldTitulo.getText(), fieldDescripcion.getText());
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
