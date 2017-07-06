package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;

public class AltaCertificado extends javax.swing.JFrame {

	/**
	 * Columns:
numAptoMedico int(11) PK 
numSocio int(11) 
fechaCreacion date 
vencimiento date 
profesional varchar(100) 
observaciones varchar(100) 
estado bit(1)
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel jLabelNroCerti;
	private JTextField fieldNroCerti;
	private JLabel jLabelDocumento;
	private JTextField fieldDocumento;	
	private JLabel jLabelFechaEntrega;
	private JTextField fieldFechaEntrega;
	private JLabel jLabelNombreMedico;
	private JTextField fieldNombreMedico;	
	private JLabel jLabelObs;
	private JTextField fieldObs;
	private JButton buttonAceptar;
	
	private SocioController sistema;
	
	public AltaCertificado (SocioController s) {
		super();
		sistema = s;
		initGui();
	}
	
	private void initGui () {
		
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);

			
				jLabelNroCerti = new JLabel();
				getContentPane().add(jLabelNroCerti);
				jLabelNroCerti.setText("Numero de Certificado");
				jLabelNroCerti.setBounds(21, 40, 180, 28);
				jLabelNroCerti.setVisible(true);

				fieldNroCerti = new JTextField();
				getContentPane().add(fieldNroCerti);
				fieldNroCerti.setBounds(200, 40, 120, 28);
				fieldNroCerti.setVisible(true);
				
				jLabelDocumento = new JLabel();
				getContentPane().add(jLabelDocumento);
				jLabelDocumento.setText("Numero de Socio");
				jLabelDocumento.setBounds(21, 80, 180, 28);
				jLabelDocumento.setVisible(true);

				fieldDocumento = new JTextField();
				getContentPane().add(fieldDocumento);
				fieldDocumento.setBounds(200, 120, 120, 28);
				fieldDocumento.setVisible(true);

				jLabelNombreMedico = new JLabel();
				getContentPane().add(jLabelNombreMedico);
				jLabelNombreMedico.setText("Nombre y Apellido Medico");
				jLabelNombreMedico.setBounds(21, 120, 180, 28);
				jLabelNombreMedico.setVisible(true);

				fieldNombreMedico = new JTextField();
				getContentPane().add(fieldNombreMedico);
				fieldNombreMedico.setBounds(200, 80, 120, 28);
				fieldNombreMedico.setVisible(true);
				
				jLabelFechaEntrega = new JLabel();
				getContentPane().add(jLabelFechaEntrega);
				jLabelFechaEntrega.setText("Nombre y Apellido Medico");
				jLabelFechaEntrega.setBounds(21, 120, 180, 28);
				jLabelFechaEntrega.setVisible(true);

				fieldFechaEntrega = new JTextField();
				getContentPane().add(fieldFechaEntrega);
				fieldFechaEntrega.setBounds(200, 80, 120, 28);
				fieldFechaEntrega.setVisible(true);
				/*
				private JLabel jLabelFechaEntrega;
				private JTextField fieldFechaEntrega;
	
				private JLabel jLabelObs;
				private JTextField fieldObs;
				private JButton buttonAceptar;
				*/
				
				
				
			}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
