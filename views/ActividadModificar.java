package views;

import controllers.ActividadController;
import models.Actividad;
import models.Profesor;
import persistence.DeporteAbm;
import persistence.EmpleadoHorarioCompletoAbm;
import persistence.EmpleadoHorarioPartAbm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ActividadModificar extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel jLabelSearch;
    private JLabel jLabelDescripcion;
    private JLabel jLabelDeporte;
    private JLabel jLabelDuracion;
    private JLabel jLabelDia;
    private JLabel jLabelHorarioDeInicio;
    private JLabel jLabelProfesorFullTime;
    private JLabel jLabelProfesorPartTime;

    private JTextField fieldCodigoBuscar;
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

    private JButton buttonBuscar;
    private JButton buttonModificar;

    private ActividadController sistema;

    public ActividadModificar (ActividadController s) {
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
                jLabelDescripcion = new JLabel();
                getContentPane().add(jLabelDescripcion);
                jLabelDescripcion.setText("Descripción:");
                jLabelDescripcion.setBounds(21, 80, 180, 28);
                jLabelDescripcion.setVisible(false);
            }
            {
                jLabelDeporte = new JLabel();
                getContentPane().add(jLabelDeporte);
                jLabelDeporte.setText("Deporte:");
                jLabelDeporte.setBounds(21, 120, 180, 28);
                jLabelDeporte.setVisible(false);
            }
            {
                jLabelDuracion = new JLabel();
                getContentPane().add(jLabelDuracion);
                jLabelDuracion.setText("Duración:");
                jLabelDuracion.setBounds(21, 160, 180, 28);
                jLabelDuracion.setVisible(false);
            }
            {
                jLabelDia = new JLabel();
                getContentPane().add(jLabelDia);
                jLabelDia.setText("Día:");
                jLabelDia.setBounds(21, 200, 180, 28);
                jLabelDia.setVisible(false);
            }
            {
                jLabelHorarioDeInicio = new JLabel();
                getContentPane().add(jLabelHorarioDeInicio);
                jLabelHorarioDeInicio.setText("Horario de Inicio:");
                jLabelHorarioDeInicio.setBounds(21, 240, 180, 28);
                jLabelHorarioDeInicio.setVisible(false);
            }
            {
                jLabelProfesorFullTime = new JLabel();
                getContentPane().add(jLabelProfesorFullTime);
                jLabelProfesorFullTime.setText("Profesor Full-Time:");
                jLabelProfesorFullTime.setBounds(21, 280, 180, 28);
                jLabelProfesorFullTime.setVisible(false);
            }
            {
                jLabelProfesorPartTime = new JLabel();
                getContentPane().add(jLabelProfesorPartTime);
                jLabelProfesorPartTime.setText("Profesor Part-Time:");
                jLabelProfesorPartTime.setBounds(21, 350, 180, 28);
                jLabelProfesorPartTime.setVisible(false);
            }

            /**
             * Acción para modificar una actividad
             */
            {
                buttonModificar = new JButton();
                getContentPane().add(buttonModificar);
                buttonModificar.setText("Modificar Actividad");
                buttonModificar.setBounds(220, 430, 123, 28);
                buttonModificar.setVisible(false);
                buttonModificar.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        try {
//                            sistema.modificar(Integer.parseInt(fieldCodigoBuscar.getText()), fieldNombre.getText(), Float.parseFloat(fieldPrecio.getText()), df.parse(fieldVigencia.getText()));
//                            sistema.modificarDeporte(Integer.parseInt(fieldCodigoBuscar.getText()), );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        setVisible(false);
                    }
                });
            }
            /**
             * Buscar una actividad a través del código
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
            /**************************************************************
             *						FIELDS
             **************************************************************/
            {
                fieldDescripcion = new JTextField();
                getContentPane().add(fieldDescripcion);
                fieldDescripcion.setBounds(200, 80, 120, 28);
                fieldDescripcion.setVisible(false);
            }
            {
                listadoDeDeportes = DeporteAbm.getInstancia().listado();
                ComboBoxModel<String> depModel = new DefaultComboBoxModel<String>(listadoDeDeportes);
                listadoDeporte = new JComboBox<String>();
                getContentPane().add(listadoDeporte);
                listadoDeporte.setModel(depModel);
                listadoDeporte.setBounds(200, 122, 120, 28);
                listadoDeporte.setVisible(false);
            }
            {
                fieldDuracion = new JTextField();
                getContentPane().add(fieldDuracion);
                fieldDuracion.setBounds(200, 162, 120, 28);
                fieldDuracion.setVisible(false);
            }
            {
                ComboBoxModel<String> diasModel = new DefaultComboBoxModel<String>(new String[] {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"});
                fieldDia = new JComboBox<String>();
                getContentPane().add(fieldDia);
                fieldDia.setModel(diasModel);
                fieldDia.setBounds(200, 202, 120, 28);
                fieldDia.setVisible(false);
            }
            {
                fieldHorarioDeInicio = new JTextField();
                getContentPane().add(fieldHorarioDeInicio);
                fieldHorarioDeInicio.setBounds(200, 242, 120, 28);
                fieldHorarioDeInicio.setVisible(false);
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
                listPFull.setVisible(false);
            }
            {
                listProfPart = EmpleadoHorarioPartAbm.getInstancia().listado();
                ComboBoxModel<String> listProfPartModel = new DefaultComboBoxModel<String>(listProfPart);
                listPPart = new JList<String>();
                getContentPane().add(listPPart);
                listPPart.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                scrollListProfPart = new JScrollPane();
                scrollListProfPart.setBounds(200, 350, 220, 50);
                scrollListProfPart.setViewportView(listPPart);
                getContentPane().add(scrollListProfPart);
                listPPart.setModel(listProfPartModel);
                listPPart.setBounds(200, 322, 120, 28);
                listPPart.setVisible(false);
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
                            jLabelDeporte.setVisible(true);
                            jLabelDescripcion.setVisible(true);
                            jLabelDia.setVisible(true);
                            jLabelDuracion.setVisible(true);
                            jLabelHorarioDeInicio.setVisible(true);
                            jLabelProfesorFullTime.setVisible(true);
                            jLabelProfesorPartTime.setVisible(true);

                            fieldDescripcion.setText(actividad.getDescription());
                            fieldDescripcion.setEditable(true);
                            fieldDescripcion.setVisible(true);
                            fieldDia.setSelectedIndex(actividad.getDia() - 1);
                            fieldDia.setEditable(true);
                            fieldDia.setVisible(true);
                            fieldDuracion.setText(String.valueOf(actividad.getDuracion()));
                            fieldDuracion.setEditable(true);
                            fieldDuracion.setVisible(true);
                            fieldHorarioDeInicio.setText(String.valueOf(actividad.getHoraDeInicio()));
                            fieldHorarioDeInicio.setVisible(true);
                            fieldHorarioDeInicio.setEditable(true);

                            String deporteText = actividad.getDeporte().getCodigo() + " - " + actividad.getDeporte().getTitulo();
                            listadoDeporte.setSelectedItem(deporteText);
                            listadoDeporte.setEditable(true);
                            listadoDeporte.setVisible(true);

                            listPFull.clearSelection();
                            listPPart.clearSelection();
                            for (Profesor p : actividad.getProfesores()) {
                                String profesorText = p.getDocumento() + " - " + p.getNombre();
                                listPPart.setSelectedValue(profesorText, false);
                                listPFull.setSelectedValue(profesorText, false);
                            }
                            listPFull.setVisible(true);
                            listPPart.setVisible(true);
                            buttonModificar.setVisible(true);
                        }
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
