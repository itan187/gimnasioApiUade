package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import controllers.SocioController;
import models.Abono;

public class AbonoBaja extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelCodigo;
	private JLabel jLabelNombre;
	private JLabel jLabelPrecio;
	private JLabel jLabelVigencia;
	
	private JTextField fieldCodigoBuscar;
	private JTextField fieldNombre;
	private JTextField fieldPrecio;
	private JTextField fieldVigencia;
	
	private JButton buttonBuscar;
	private JButton buttonBaja;
	
	private SocioController sistema;
	
	public AbonoBaja (SocioController s) {
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
				jLabelPrecio = new JLabel();
				getContentPane().add(jLabelPrecio);
				jLabelPrecio.setText("Precio:");
				jLabelPrecio.setBounds(21, 91, 63, 28);
				jLabelPrecio.setVisible(false);
			}
			{
				fieldPrecio = new JTextField();
				getContentPane().add(fieldPrecio);
				fieldPrecio.setBounds(119, 91, 210, 28);
				fieldPrecio.setVisible(false);
			}
			{
				jLabelVigencia = new JLabel();
				getContentPane().add(jLabelVigencia);
				jLabelVigencia.setText("Vigencia:");
				jLabelVigencia.setBounds(21, 160, 63, 28);
				jLabelVigencia.setVisible(false);
			}
			{
				DateFormat dateFormat = new SimpleDateFormat("Y-M-D");
				fieldVigencia = new JFormattedTextField(dateFormat);
				getContentPane().add(fieldVigencia);
				fieldVigencia.setBounds(200, 160, 120, 28);
				fieldVigencia.setVisible(false);
			}
			
			/**
			 * Acción para dar de baja a un abono
			 */
			{
				buttonBaja = new JButton();
				getContentPane().add(buttonBaja);
				buttonBaja.setText("Eliminar Abono");
				buttonBaja.setBounds(260, 217, 123, 28);
				buttonBaja.setVisible(false);
				buttonBaja.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						sistema.bajaAbono(Integer.parseInt(fieldCodigoBuscar.getText()));
						setVisible(false);
					}
				});
			}

			/**
			 * Buscar un abono a través del código
			 */
			{
				jLabelSearch = new JLabel();
				getContentPane().add(jLabelSearch);
				jLabelSearch.setText("Ingrese el código del abono:");
				jLabelSearch.setBounds(21, 7, 98, 28);
			}
			{
				fieldCodigoBuscar = new JTextField();
				getContentPane().add(fieldCodigoBuscar);
				fieldCodigoBuscar.setBounds(140, 7, 147, 28);
			}
			{
				buttonBuscar = new JButton();
				getContentPane().add(buttonBuscar);
				buttonBuscar.setText("Buscar");
				buttonBuscar.setBounds(301, 7, 77, 28);
				buttonBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						Abono abono = sistema.buscarAbono(Integer.parseInt(fieldCodigoBuscar.getText()));

						if (abono != null) {
							jLabelSearch.setVisible(true);
							jLabelNombre.setVisible(true);
							jLabelPrecio.setVisible(true);
							jLabelVigencia.setVisible(true);

							fieldNombre.setVisible(true);
							fieldNombre.setEnabled(false);
							fieldNombre.setText(abono.getNombre());
							fieldPrecio.setVisible(true);
							fieldPrecio.setEnabled(false);
							fieldPrecio.setText(String.valueOf(abono.getPrecio()));

							DateFormat df = new SimpleDateFormat("Y-M-D");
							fieldVigencia.setVisible(true);
							fieldVigencia.setEnabled(false);
							fieldVigencia.setText(df.format(abono.getVigencia()));

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
