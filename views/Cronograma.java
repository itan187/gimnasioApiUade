package views;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import persistence.ActividadAbm;

public class Cronograma extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JScrollPane jScrollPane;
	private JTable table;
	
	public Cronograma  () {
		super();
		initGui();
	}
	
	private void initGui () {
		
		try {
			jScrollPane = new JScrollPane();
			getContentPane().add(jScrollPane, BorderLayout.CENTER);
			
			{
				TableModel tableModel = new DefaultTableModel(
								new String[][] { { "One", "Two" }, { "Three", "Four" } },
								new String[] { "","LUN","MAR","MIE","JUE","VIE","SAB","DOM" });
				
				table = new JTable();
				jScrollPane.setViewportView(table);
				table.setModel(tableModel);
				table.setEnabled(false);
				table.setOpaque(false);

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				int[] dias = new int[] {1,2,3,4,5,6,7};
				int[] horas = new int[] {8,9,10,11,12,13,14,15,16,17,18,19,20};
				
				for(int hora: horas) {
					
					String[] row = new String[] { "","","","","","","","" }; 
					
					row[0] = hora + ":00";
					
					for (int dia: dias) {
						
						Vector<String> actividades = ActividadAbm.getInstancia().listarActividad(dia, hora * 100);
						for (String d: actividades) {
							row[dia] =  d;
						}
							
					}
					model.addRow(row);
				}
				
			}
			
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
