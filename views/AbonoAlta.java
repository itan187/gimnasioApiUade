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

public class AbonoAlta extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel jLabelCodigo;
	private JLabel jLabelNombre;
	private JLabel jLabelPrecio;
	private JLabel jLabelVigencia;
	
	private JTextField fieldCodigo;
	private JTextField fieldNombre;
	private JTextField fieldPrecio;
	private JFormattedTextField fieldVigencia;
	
	private JButton buttonAceptar;
	
	private SocioController sistema;

	public AbonoAlta  (SocioController s) {
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
				jLabelNombre = new JLabel();
				getContentPane().add(jLabelNombre);
				jLabelNombre.setText("Nombre:");
				jLabelNombre.setBounds(21, 80, 180, 28);
				jLabelNombre.setVisible(true);
			}
			{
				jLabelPrecio = new JLabel();
				getContentPane().add(jLabelPrecio);
				jLabelPrecio.setText("Precio:");
				jLabelPrecio.setBounds(21, 120, 180, 28);
				jLabelPrecio.setVisible(true);
			}
			{
				jLabelVigencia = new JLabel();
				getContentPane().add(jLabelVigencia);
				jLabelVigencia.setText("Vigencia:");
				jLabelVigencia.setBounds(21, 160, 180, 28);
				jLabelVigencia.setVisible(true);
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
				fieldNombre = new JTextField();
				getContentPane().add(fieldNombre);
				fieldNombre.setBounds(200, 80, 120, 28);
			}
			{
				fieldPrecio = new JTextField();
				getContentPane().add(fieldPrecio);
				fieldPrecio.setBounds(200, 122, 120, 28);
			}
			{
				DateFormat dateFormat = new SimpleDateFormat("Y-M-D");
				fieldVigencia = new JFormattedTextField(dateFormat);
				getContentPane().add(fieldVigencia);
				fieldVigencia.setBounds(200, 160, 120, 28);
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
						if (fieldCodigo.getText().equals("") || fieldNombre.getText().equals("") || fieldPrecio.getText().equals("") || fieldVigencia.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar el abono.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							DateFormat df = new SimpleDateFormat("Y-M-D");
							try {
								sistema.altaAbono(Integer.parseInt(fieldCodigo.getText()), fieldNombre.getText(), Float.parseFloat(fieldPrecio.getText()), df.parse(fieldVigencia.getText()));
							} catch (NumberFormatException | ParseException e) {
								e.printStackTrace();
							};
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