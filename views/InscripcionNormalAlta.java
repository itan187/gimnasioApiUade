package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.BitSet;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import controllers.SocioController;
import persistence.InscripcionNormalAbm;

public class InscripcionNormalAlta extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelNumero;
	private JLabel jLabelEstado;
	private JLabel jLabelClases;
	
	private JTextField fieldNumero;
	private JComboBox<String> fieldEstado;
	/*private JComboBox<String> listAct;*/
	private JList <String> listAct; //yo
	private DefaultListModel modelo;//declaramos el Modelo
	private JScrollPane scrollLista;
	private JTextField fieldClases;
	
	private JButton buttonAceptar;
	
	Vector<String> listActividades;
	
	private SocioController sistema;

	public InscripcionNormalAlta (SocioController s) {
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
				jLabelEstado = new JLabel();
				getContentPane().add(jLabelEstado);
				jLabelEstado.setText("Estado:");
				jLabelEstado.setBounds(21, 80, 180, 28);
				jLabelEstado.setVisible(true);
			}
			{
				jLabelClases = new JLabel();
				getContentPane().add(jLabelClases);
				jLabelClases.setText("Actividad:");
				jLabelClases.setBounds(21, 120, 180, 28);
				jLabelClases.setVisible(true);
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
				scrollLista.setBounds(200, 120,220, 80);
				scrollLista.setViewportView(listAct);
				getContentPane().add(scrollLista);
				listAct.setModel(listActModel);
				listAct.setBounds(200, 122, 120, 28);
			}
			{
				buttonAceptar = new JButton();
				getContentPane().add(buttonAceptar);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.setBounds(240, 200, 123, 28);
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
							boolean e;
							if (fEstado.equals("Activo")) {
								e = true;
							} else {
								e = false;
							}
							sistema.altaInscripcionNormal(Integer.parseInt(fieldNumero.getText()), e, idActividad);
						}
						setVisible(false);
					}
				});
			}
			
			pack();
			setSize(500, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
