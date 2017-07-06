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

import controllers.SocioController;
import persistence.InscripcionNormalAbm;

public class InscripcionCorpoAlta extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelNumero;
	private JLabel jLabelEstado;
	private JLabel jLabelClases;
	private JLabel jLabelEmpresa;
	private JLabel jLabelVigencia;
	
	private JTextField fieldNumero;
	private JComboBox<String> fieldEstado;
	
	private JList <String> listAct;
	private JScrollPane scrollLista;
	Vector<String> listActividades;
	
	private JTextField fieldEmpresa;
	private JTextField fieldVigencia;
	
	private JButton buttonAceptar;
	
	private SocioController sistema;

	public InscripcionCorpoAlta (SocioController s) {
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
				jLabelEstado = new JLabel();
				getContentPane().add(jLabelEstado);
				jLabelEstado.setText("Estado:");
				jLabelEstado.setBounds(21, 80, 180, 28);
				jLabelEstado.setVisible(true);
			}
			{
				jLabelClases = new JLabel();
				getContentPane().add(jLabelClases);
				jLabelClases.setText("Actividades:");
				jLabelClases.setBounds(21, 120, 180, 28);
				jLabelClases.setVisible(true);
			}
			{
				jLabelEmpresa = new JLabel();
				getContentPane().add(jLabelEmpresa);
				jLabelEmpresa.setText("Empresa:");
				jLabelEmpresa.setBounds(21, 200, 180, 28);
				jLabelEmpresa.setVisible(true);
			}
			{
				jLabelVigencia = new JLabel();
				getContentPane().add(jLabelVigencia);
				jLabelVigencia.setText("Fecha:");
				jLabelVigencia.setBounds(21, 240, 180, 28);
				jLabelVigencia.setVisible(true);
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
				ComboBoxModel<String> estadoModel = new DefaultComboBoxModel<String>(new String[] {"Activo", "Desactivo"});
				fieldEstado = new JComboBox<String>();
				getContentPane().add(fieldEstado);
				fieldEstado.setModel(estadoModel);
				fieldEstado.setBounds(200, 80, 120, 28);
			}
			{
				listActividades = InscripcionNormalAbm.getInstancia().listado();
				ComboBoxModel<String> listActModel = new DefaultComboBoxModel<String>(listActividades);
				listAct = new JList<String>();
				getContentPane().add(listAct);
				listAct.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
				scrollLista = new JScrollPane();
				scrollLista.setBounds(200, 120, 200, 80);
				scrollLista.setViewportView(listAct);
				getContentPane().add(scrollLista);
				listAct.setModel(listActModel);
				listAct.setBounds(200, 120, 120, 28);
			}
			{
				fieldEmpresa = new JTextField();
				getContentPane().add(fieldEmpresa);
				fieldEmpresa.setBounds(200, 200, 120, 28);
			}
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				fieldVigencia = new JFormattedTextField(dateFormat);
				getContentPane().add(fieldVigencia);
				fieldVigencia.setBounds(200, 240, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.setBounds(220, 280, 123, 28);
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
							String mensajeError = "¡Atención! Faltan completar campos y por ello no se puede agregar la inscripción.";
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
								Date df = new SimpleDateFormat("yyyy-MM-dd").parse(fieldVigencia.getText());
								sistema.altaInscripcionCorpo(
										Integer.parseInt(fieldNumero.getText()), 
										e, 
										idActividad, 
										fieldEmpresa.getText(), 
										df
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
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
