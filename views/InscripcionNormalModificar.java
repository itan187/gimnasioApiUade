package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;
import models.Inscripcion;

public class InscripcionNormalModificar extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelEstado;
	private JLabel jLabelClases;
	
	private JTextField fieldNumeroBuscar;
	private JTextField fieldEstado;
	private JTextField fieldClases;
	
	private JButton buttonBuscar;
	private JButton buttonModificar;
	
	private SocioController sistema;
	
	public InscripcionNormalModificar (SocioController s) {
		super();
		sistema = s;
		initGui();
	}
	
	private void initGui () {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			
			{
				jLabelEstado = new JLabel();
				getContentPane().add(jLabelEstado);
				jLabelEstado.setText("Estado:");
				jLabelEstado.setBounds(21, 91, 63, 28);
				jLabelEstado.setVisible(false);
			}
			{
				fieldEstado = new JTextField();
				getContentPane().add(fieldEstado);
				fieldEstado.setBounds(119, 91, 210, 28);
				fieldEstado.setVisible(false);
			}
			{
				jLabelClases = new JLabel();
				getContentPane().add(jLabelClases);
				jLabelClases.setText("Clases:");
				jLabelClases.setBounds(21, 91, 63, 28);
				jLabelClases.setVisible(false);
			}
			{
				fieldClases = new JTextField();
				getContentPane().add(fieldClases);
				fieldClases.setBounds(119, 91, 210, 28);
				fieldClases.setVisible(false);
			}
			/**
			 * Acción para dar de baja a una inscripción
			 */
			{
				buttonModificar = new JButton();
				getContentPane().add(buttonModificar);
				buttonModificar.setText("Eliminar Inscripción");
				buttonModificar.setBounds(260, 217, 123, 28);
				buttonModificar.setVisible(false);
				buttonModificar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						/*sistema.modificarInscripcionNormal(
								Integer.parseInt(fieldNumeroBuscar.getText()), 
								Boolean.parseBoolean(fieldEstado.getText()), 
								fieldClases.getText()
						);*/
						setVisible(false);
					}
				});
			}
			
			/**
			 * Buscar un inscripto a través de un código
			 */
			{
				jLabelSearch = new JLabel();
				getContentPane().add(jLabelSearch);
				jLabelSearch.setText("Ingrese el código de la inscripción:");
				jLabelSearch.setBounds(21, 7, 98, 28);
			}
			{
				fieldNumeroBuscar = new JTextField();
				getContentPane().add(fieldNumeroBuscar);
				fieldNumeroBuscar.setBounds(140, 7, 147, 28);
			}
			{
				buttonBuscar = new JButton();
				getContentPane().add(buttonBuscar);
				buttonBuscar.setText("Buscar");
				buttonBuscar.setBounds(301, 7, 77, 28);
				buttonBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						Inscripcion inscripcion = sistema.buscarInscripcion(Integer.parseInt(fieldNumeroBuscar.getText()));
						
						if (inscripcion != null) {
							jLabelEstado.setVisible(true);
							jLabelClases.setVisible(true);
							
							fieldEstado.setVisible(true);
							fieldEstado.setEnabled(false);
							fieldClases.setVisible(true);
							fieldClases.setEnabled(false);
							
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
