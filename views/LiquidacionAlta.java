package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.RrhhController;

public class LiquidacionAlta extends javax.swing.JFrame {	
	private static final long serialVersionUID = 1L;
	
	private JLabel jLabelNumero;
	private JLabel jLabelAnio;
	private JLabel jLabelMes;
	
	private JTextField fieldNumero;
	private JTextField fieldAnio;
	private JComboBox<String> fieldMes;
	
	private JButton buttonAceptar;
	
	private RrhhController sistema;

	public LiquidacionAlta  (RrhhController s) {
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
				jLabelAnio = new JLabel();
				getContentPane().add(jLabelAnio);
				jLabelAnio.setText("Año:");
				jLabelAnio.setBounds(21, 82, 180, 28);
				jLabelAnio.setVisible(true);
			}
			{
				jLabelMes = new JLabel();
				getContentPane().add(jLabelMes);
				jLabelMes.setText("Mes:");
				jLabelMes.setBounds(21, 122, 180, 28);
				jLabelMes.setVisible(true);
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
				fieldAnio = new JTextField();
				getContentPane().add(fieldAnio);
				fieldAnio.setBounds(200, 82, 120, 28);
				fieldAnio.setVisible(true);
			}
			{
				ComboBoxModel<String> mesesModel = new DefaultComboBoxModel<String>(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
				fieldMes = new JComboBox<String>();
				getContentPane().add(fieldMes);
				fieldMes.setModel(mesesModel);
				fieldMes.setBounds(200, 122, 120, 28);
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
						int mes = fieldMes.getSelectedIndex();
						mes++;
						if (fieldNumero.getText().equals("") || fieldAnio.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar el abono.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							if (!sistema.existeLiquidacion(Integer.parseInt(fieldAnio.getText()), mes)) {
								sistema.altaLiquidacion(Integer.parseInt(fieldNumero.getText()), Integer.parseInt(fieldAnio.getText()), mes);
							} else {
								String mensajeError = "¡Atención! La liquidación que usted quiere efectuar ya existe actualmente";
							    JOptionPane.showMessageDialog(null, mensajeError);
							}
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
