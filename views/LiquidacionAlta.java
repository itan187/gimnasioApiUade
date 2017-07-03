package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;

public class LiquidacionAlta extends javax.swing.JFrame {	
	private static final long serialVersionUID = 1L;
	
	private JLabel jLabelAnio;
	private JLabel jLabelMes;
	
	private JTextField fieldAnio;
	private JTextField fieldMes;
	
	private JButton buttonAceptar;
	
	private SocioController sistema;

	public LiquidacionAlta  (SocioController s) {
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
				jLabelAnio = new JLabel();
				getContentPane().add(jLabelAnio);
				jLabelAnio.setText("Año:");
				jLabelAnio.setBounds(21, 42, 180, 28);
				jLabelAnio.setVisible(true);
			}
			{
				jLabelMes = new JLabel();
				getContentPane().add(jLabelMes);
				jLabelMes.setText("Mes:");
				jLabelMes.setBounds(21, 80, 180, 28);
				jLabelMes.setVisible(true);
			}
			
			/**************************************************************
			 *						FIELDS
			**************************************************************/
			{
				fieldAnio = new JTextField();
				getContentPane().add(fieldAnio);
				fieldAnio.setBounds(200, 42, 120, 28);
				fieldAnio.setVisible(true);
			}
			{
				fieldMes = new JTextField();
				getContentPane().add(fieldMes);
				fieldMes.setBounds(200, 80, 120, 28);
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
						if (fieldAnio.getText().equals("") || fieldMes.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar el abono.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							//sistema.altaAbono(Integer.parseInt(fieldCodigo.getText()), fieldNombre.getText(), Float.parseFloat(fieldPrecio.getText()), df.parse(fieldVigencia.getText()));
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
