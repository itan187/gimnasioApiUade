package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import app.Utiles;
import controllers.ActividadController;
import models.Actividad;

public class ActividadBaja extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jLabelSearch;
	private JLabel jLabelDescripcion;
	private JLabel jLabelDeporte;
	private JLabel jLabelDuracion;
	private JLabel jLabelDia;
	private JLabel jLabelHorario;
	
	private JTextField fieldCodigoBuscar;
	private JTextField fieldDescripcion;
	private JTextField fieldDeporte;
	private JTextField fieldDuracion;
	private JTextField fieldDia;
	private JTextField fieldHorario;
	
	private JButton buttonBuscar;
	private JButton buttonBaja;
	
	private ActividadController sistema;
	
	public ActividadBaja (ActividadController s) {
		super();
		sistema = s;
		initGui();
	}
	
	private void initGui () {
		
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);

			{
				jLabelDescripcion = new JLabel();
				getContentPane().add(jLabelDescripcion);
				jLabelDescripcion.setText("Descripción:");
				jLabelDescripcion.setBounds(21, 42, 63, 28);
				jLabelDescripcion.setVisible(false);
			}
			{
				fieldDescripcion = new JTextField();
				getContentPane().add(fieldDescripcion);
				fieldDescripcion.setBounds(200, 42, 120, 28);
				fieldDescripcion.setVisible(false);
			}
			{
				jLabelDeporte = new JLabel();
				getContentPane().add(jLabelDeporte);
				jLabelDeporte.setText("Deporte:");
				jLabelDeporte.setBounds(21, 82, 63, 28);
				jLabelDeporte.setVisible(false);
			}
			{
				fieldDeporte = new JTextField();
				getContentPane().add(fieldDeporte);
				fieldDeporte.setBounds(200, 82, 120, 28);
				fieldDeporte.setVisible(false);
			}
			{
				jLabelDuracion = new JLabel();
				getContentPane().add(jLabelDuracion);
				jLabelDuracion.setText("Duración:");
				jLabelDuracion.setBounds(21, 120, 63, 28);
				jLabelDuracion.setVisible(false);
			}
			{
				fieldDuracion = new JTextField();
				getContentPane().add(fieldDuracion);
				fieldDuracion.setBounds(200, 120, 120, 28);
				fieldDuracion.setVisible(false);
			}
			{
				jLabelDia = new JLabel();
				getContentPane().add(jLabelDia);
				jLabelDia.setText("Día:");
				jLabelDia.setBounds(21, 160, 63, 28);
				jLabelDia.setVisible(false);
			}
			{
				fieldDia = new JTextField();
				getContentPane().add(fieldDia);
				fieldDia.setBounds(200, 160, 120, 28);
				fieldDia.setVisible(false);
			}
			{
				jLabelHorario = new JLabel();
				getContentPane().add(jLabelHorario);
				jLabelHorario.setText("Horario:");
				jLabelHorario.setBounds(21, 200, 63, 28);
				jLabelHorario.setVisible(false);
			}
			{
				fieldHorario = new JTextField();
				getContentPane().add(fieldHorario);
				fieldHorario.setBounds(200, 200, 120, 28);
				fieldHorario.setVisible(false);
			}
			
			/**
			 * Acción para dar de baja a una actividad
			 */
			{
				buttonBaja = new JButton();
				getContentPane().add(buttonBaja);
				buttonBaja.setText("Eliminar");
				buttonBaja.setBounds(260, 240, 123, 28);
				buttonBaja.setVisible(false);
				buttonBaja.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						sistema.bajaActividad(Integer.parseInt(fieldCodigoBuscar.getText()));
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
				jLabelSearch.setText("Ingrese el código de la actividad:");
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

						Actividad actividad = sistema.buscarActividad(Integer.parseInt(fieldCodigoBuscar.getText()));

						if (actividad != null) {
							jLabelSearch.setVisible(true);
							jLabelDescripcion.setVisible(true);
							jLabelDeporte.setVisible(true);
							jLabelDia.setVisible(true);
							jLabelDuracion.setVisible(true);
							jLabelHorario.setVisible(true);

							fieldDescripcion.setVisible(true);
							fieldDescripcion.setEnabled(false);
							fieldDescripcion.setText(actividad.getDescription());
							
							fieldDeporte.setVisible(true);
							fieldDeporte.setEnabled(false);
							fieldDeporte.setText(actividad.getDeporte().getTitulo());
							
							fieldDuracion.setVisible(true);
							fieldDuracion.setEnabled(false);
							fieldDuracion.setText(String.valueOf(actividad.getDuracion()));

							fieldDia.setVisible(true);
							fieldDia.setEnabled(false);
							fieldDia.setText(Utiles.getDiaDeLaSemana(actividad.getDia()));
							
							fieldHorario.setVisible(true);
							fieldHorario.setEnabled(false);
							fieldHorario.setText(String.valueOf(actividad.getHoraDeInicio()));

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
