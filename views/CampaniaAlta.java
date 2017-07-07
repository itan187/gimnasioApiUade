package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import controllers.CampaniaController;
import persistence.ActividadAbm;

public class CampaniaAlta extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel jLabelNumero;
	private JLabel jLabelAsunto;
	private JLabel jLabelFechaDeEnvio;
	private JLabel jLabelActividades;
	private JLabel jLabelEstado;
	
	private JTextField fieldNumero;
	private JTextField fieldAsunto;
	private JTextField fieldFechaDeEnvio;
	
	private JComboBox<String> fieldEstado;
	
	private JList <String> listAct;
	private JScrollPane scrollLista;
	Vector<String> listActividades;
	
	private JButton buttonAceptar;
	
	private CampaniaController sistema;

	public CampaniaAlta (CampaniaController s) {
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
				jLabelNumero.setBounds(21, 40, 180, 28);
				jLabelNumero.setVisible(true);
			}
			{
				jLabelAsunto = new JLabel();
				getContentPane().add(jLabelAsunto);
				jLabelAsunto.setText("Asunto:");
				jLabelAsunto.setBounds(21, 80, 180, 28);
				jLabelAsunto.setVisible(true);
			}
			{
				jLabelFechaDeEnvio = new JLabel();
				getContentPane().add(jLabelFechaDeEnvio);
				jLabelFechaDeEnvio.setText("Fecha y hora de Envio:");
				jLabelFechaDeEnvio.setBounds(21, 120, 180, 28);
				jLabelFechaDeEnvio.setVisible(true);
			}
			{
				jLabelActividades = new JLabel();
				getContentPane().add(jLabelActividades);
				jLabelActividades.setText("Actividad/es:");
				jLabelActividades.setBounds(21, 160, 180, 28);
				jLabelActividades.setVisible(true);
			}
			{
				jLabelEstado = new JLabel();
				getContentPane().add(jLabelEstado);
				jLabelEstado.setText("Estado:");
				jLabelEstado.setBounds(21, 250, 180, 28);
				jLabelEstado.setVisible(true);
			}
			
			/**************************************************************
			 *						FIELDS
			**************************************************************/
			{
				fieldNumero = new JTextField();
				getContentPane().add(fieldNumero);
				fieldNumero.setBounds(200, 40, 120, 28);
				fieldNumero.setVisible(true);
			}
			{
				fieldAsunto = new JTextField();
				getContentPane().add(fieldAsunto);
				fieldAsunto.setBounds(200, 80, 120, 28);
				fieldAsunto.setVisible(true);
			}
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fieldFechaDeEnvio = new JFormattedTextField(dateFormat);
				getContentPane().add(fieldFechaDeEnvio);
				fieldFechaDeEnvio.setBounds(200, 120, 120, 28);
			}
			{
				listActividades = ActividadAbm.getInstancia().listado();
				ComboBoxModel<String> listActModel = new DefaultComboBoxModel<String>(listActividades);
				listAct = new JList<String>();
				getContentPane().add(listAct);
				listAct.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
				scrollLista = new JScrollPane();
				scrollLista.setBounds(200, 160, 200, 80);
				scrollLista.setViewportView(listAct);
				getContentPane().add(scrollLista);
				listAct.setModel(listActModel);
				listAct.setBounds(200, 160, 150, 28);
			}
			{
				ComboBoxModel<String> estadoModel = new DefaultComboBoxModel<String>(new String[] {"Activo", "Desactivo"});
				fieldEstado = new JComboBox<String>();
				getContentPane().add(fieldEstado);
				fieldEstado.setModel(estadoModel);
				fieldEstado.setBounds(200, 250, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Generar");
				buttonAceptar.setBounds(200, 280, 123, 28);
				buttonAceptar.setVisible(true);
				buttonAceptar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						String fEstado = (String)fieldEstado.getSelectedItem();
						
						List<String> fActividad = listAct.getSelectedValuesList();
						Vector<Integer> idActividad = new Vector<Integer>();
						for (String f: fActividad) {
							String[] division = f.split(" - ");
							idActividad.add(Integer.parseInt(division[0]));
						}
						
						if (fieldNumero.getText().equals("") || fEstado.equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar la campania.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							/**
							 * Estado
							 */
							boolean e;
							if (fEstado.equals("Activo")) {
								e = true;
							} else {
								e = false;
							}
							
							try {
								Date df = new SimpleDateFormat("yyyy-MM-dd").parse(fieldFechaDeEnvio.getText());
								
								sistema.altaCampania(
										Integer.parseInt(fieldNumero.getText()), 
										fieldAsunto.getText(), 
										df, 
										idActividad, 
										e
									);
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}
						setVisible(false);
					}
				});
			}
			
			pack();
			setSize(450, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
