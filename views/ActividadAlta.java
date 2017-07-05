
package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import controllers.ActividadController;
import persistence.DeporteAbm;
import persistence.EmpleadoHorarioCompletoAbm;
import persistence.EmpleadoHorarioPartAbm;

public class ActividadAlta extends javax.swing.JFrame {

private static final long serialVersionUID = 1L;
	
	private JLabel jLabelNumero;
	private JLabel jLabelDescripcion;
	private JLabel jLabelDeporte;
	private JLabel jLabelDuracion;
	private JLabel jLabelDia;
	private JLabel jLabelHorarioDeInicio;
	private JLabel jLabelProfesorFullTime;
	private JLabel jLabelProfesorPartTime;
	
	private JTextField fieldNumero;
	private JTextField fieldDescripcion;
	private JComboBox<String> listadoDeporte;
	Vector<String> listadoDeDeportes;
	private JTextField fieldDuracion;
	private JComboBox<String> fieldDia;
	private JTextField fieldHorarioDeInicio;
	
	/** Profesor Full Time **/
	private JScrollPane scrollListProfFull;
	private JList <String> listPFull;
	Vector<String> listProfFull;
	
	/** Profesor Part Time **/
	private JScrollPane scrollListProfPart;
	private JList <String> listPPart;
	Vector<String> listProfPart;
	
	private JButton buttonAceptar;
	
	private ActividadController sistema;

	public ActividadAlta (ActividadController s) {
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
				jLabelDescripcion = new JLabel();
				getContentPane().add(jLabelDescripcion);
				jLabelDescripcion.setText("Descripción:");
				jLabelDescripcion.setBounds(21, 80, 180, 28);
				jLabelDescripcion.setVisible(true);
			}
			{
				jLabelDeporte = new JLabel();
				getContentPane().add(jLabelDeporte);
				jLabelDeporte.setText("Deporte:");
				jLabelDeporte.setBounds(21, 120, 180, 28);
				jLabelDeporte.setVisible(true);
			}
			{
				jLabelDuracion = new JLabel();
				getContentPane().add(jLabelDuracion);
				jLabelDuracion.setText("Duración:");
				jLabelDuracion.setBounds(21, 160, 180, 28);
				jLabelDuracion.setVisible(true);
			}
			{
				jLabelDia = new JLabel();
				getContentPane().add(jLabelDia);
				jLabelDia.setText("Día:");
				jLabelDia.setBounds(21, 200, 180, 28);
				jLabelDia.setVisible(true);
			}
			{
				jLabelHorarioDeInicio = new JLabel();
				getContentPane().add(jLabelHorarioDeInicio);
				jLabelHorarioDeInicio.setText("Horario de Inicio:");
				jLabelHorarioDeInicio.setBounds(21, 240, 180, 28);
				jLabelHorarioDeInicio.setVisible(true);
			}
			{
				jLabelProfesorFullTime = new JLabel();
				getContentPane().add(jLabelProfesorFullTime);
				jLabelProfesorFullTime.setText("Profesor Full-Time:");
				jLabelProfesorFullTime.setBounds(21, 280, 180, 28);
				jLabelProfesorFullTime.setVisible(true);
			}
			{
				jLabelProfesorPartTime = new JLabel();
				getContentPane().add(jLabelProfesorPartTime);
				jLabelProfesorPartTime.setText("Profesor Part-Time:");
				jLabelProfesorPartTime.setBounds(21, 350, 180, 28);
				jLabelProfesorPartTime.setVisible(true);
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
				fieldDescripcion = new JTextField();
				getContentPane().add(fieldDescripcion);
				fieldDescripcion.setBounds(200, 80, 120, 28);
			}
			{
				listadoDeDeportes = DeporteAbm.getInstancia().listado();
				ComboBoxModel<String> depModel = new DefaultComboBoxModel<String>(listadoDeDeportes);
				listadoDeporte = new JComboBox<String>();
				getContentPane().add(listadoDeporte);
				listadoDeporte.setModel(depModel);
				listadoDeporte.setBounds(200, 122, 120, 28);
			}
			{
				fieldDuracion = new JTextField();
				getContentPane().add(fieldDuracion);
				fieldDuracion.setBounds(200, 162, 120, 28);
			}
			{
				ComboBoxModel<String> diasModel = new DefaultComboBoxModel<String>(new String[] {"Lunes", "Martes", "Miércoles", "Jueves", "Mayo", "Viernes", "Sábado", "Domingo"});
				fieldDia = new JComboBox<String>();
				getContentPane().add(fieldDia);
				fieldDia.setModel(diasModel);
				fieldDia.setBounds(200, 202, 120, 28);
			}
			{
				fieldHorarioDeInicio = new JTextField();
				getContentPane().add(fieldHorarioDeInicio);
				fieldHorarioDeInicio.setBounds(200, 242, 120, 28);
			}
			{
				listProfFull = EmpleadoHorarioCompletoAbm.getInstancia().listado();
				ComboBoxModel<String> listProfFullModel = new DefaultComboBoxModel<String>(listProfFull);
				listPFull = new JList<String>();
				getContentPane().add(listPFull);
				listPFull.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
				scrollListProfFull = new JScrollPane();
				scrollListProfFull.setBounds(200, 282, 220, 50);
				scrollListProfFull.setViewportView(listPFull);
				getContentPane().add(scrollListProfFull);
				listPFull.setModel(listProfFullModel);
				listPFull.setBounds(200, 282, 120, 28);
			}
			{
				listProfPart = EmpleadoHorarioPartAbm.getInstancia().listado();
				ComboBoxModel<String> listProfPartModel = new DefaultComboBoxModel<String>(listProfPart);
				listPPart = new JList<String>();
				getContentPane().add(listPPart);
				listPPart.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
				scrollListProfPart = new JScrollPane();
				scrollListProfPart.setBounds(200, 350, 220, 50);
				scrollListProfPart.setViewportView(listPPart);
				getContentPane().add(scrollListProfPart);
				listPPart.setModel(listProfPartModel);
				listPPart.setBounds(200, 322, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.setBounds(220, 430, 123, 28);
				buttonAceptar.setVisible(true);
				buttonAceptar.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{	
						int dia = fieldDia.getSelectedIndex();
						dia++;
						String dep = (String)listadoDeporte.getSelectedItem();
						String[] deportes = dep.split(" - ");
						
						
						List<String> fProfesoresFull = listPFull.getSelectedValuesList();
						Vector<Integer> idProfesores = new Vector<Integer>();
						for (String f: fProfesoresFull) {
							String[] division = f.split(" - ");
							idProfesores.add(Integer.parseInt(division[0]));
						}
						
						List<String> fProfesoresPart = listPPart.getSelectedValuesList();
						for (String p: fProfesoresPart) {
							String[] division = p.split(" - ");
							idProfesores.add(Integer.parseInt(division[0]));
						}
						
						
						if (fieldNumero.getText().equals("") || fieldDescripcion.getText().equals("") || fieldHorarioDeInicio.getText().equals("") || fieldDuracion.getText().equals("")) {
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar la actividad.";
						    JOptionPane.showMessageDialog(null, mensajeError);
						} else {
							sistema.altaActividad(
									Integer.parseInt(fieldNumero.getText()), 
									fieldDescripcion.getText(),
									Integer.parseInt(deportes[0]),
									idProfesores,
									Integer.parseInt(fieldDuracion.getText()),
									dia,
									Integer.parseInt(fieldHorarioDeInicio.getText())
								);
						}
						setVisible(false);
					}
				});
			}
			
			pack();
			setSize(450, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}			
}