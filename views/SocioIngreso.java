package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controllers.SocioController;
import models.Socio;

public class SocioIngreso extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel jLabelSearch;
    private JLabel jLabelEstado;
    private JLabel jLabelEstadoIngreso;

    private JTextField fieldDocumentoBuscar;
    private JButton buttonBuscar;

    private SocioController sistema;

    public SocioIngreso (SocioController s) {
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
                jLabelEstado.setText("Ingreso Valido:");
                jLabelEstado.setBounds(21, 40, 63, 28);
                jLabelEstado.setVisible(false);
            }
            {
                jLabelEstadoIngreso = new JLabel();
                getContentPane().add(jLabelEstadoIngreso);
                jLabelEstadoIngreso.setBounds(119, 40, 210, 28);
                jLabelEstadoIngreso.setVisible(false);
            }

            /**
             * Buscar un socio a través del documento único
             */
            {
                jLabelSearch = new JLabel();
                getContentPane().add(jLabelSearch);
                jLabelSearch.setText("Ingrese el DNI:");
                jLabelSearch.setBounds(21, 7, 98, 28);
            }
            {
                fieldDocumentoBuscar = new JTextField();
                getContentPane().add(fieldDocumentoBuscar);
                fieldDocumentoBuscar.setBounds(140, 7, 147, 28);
            }
            {
                buttonBuscar = new JButton();
                getContentPane().add(buttonBuscar);
                buttonBuscar.setText("Buscar");
                buttonBuscar.setBounds(301, 7, 77, 28);
                buttonBuscar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Socio socio = sistema.buscarSocio(Integer.parseInt(fieldDocumentoBuscar.getText()));

                        if (socio != null) {
                            jLabelEstado.setVisible(true);
                            jLabelEstadoIngreso.setVisible(true);
                            jLabelEstadoIngreso.setText((sistema.habilitadoParaIngresar(Integer.parseInt(fieldDocumentoBuscar.getText())) ? "Si" : "No"));
                        } else {
                            String mensajeError = "¡Atención! No se ha encontrado el socio que usted esta buscando!";
                            JOptionPane.showMessageDialog(null, mensajeError);
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
