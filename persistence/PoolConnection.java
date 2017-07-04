/*********************************************
 * 			PoolConnection
 * 
 * @author: Paula Sarasa
 * 
 * Ejemplo ConfigDB.txt:
 * 
 * 		jdbc=jdbc:sqlserver://
 * 		servidor=192.168.0.1:1433
 * 		usuario=User
 * 		password=Password
 * 		conexiones=3
 * 		db_name=DbName
 * 
 */
package persistence;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class PoolConnection {
	private Vector <Connection> connections = new Vector<Connection>();
	protected String jdbc;
	protected String servidor;
	protected String usuario;
	protected String password;
	protected int cantCon;
	protected static String dbName;
	
	private static PoolConnection pool;
	
	private PoolConnection() {
//		getConfiguration();
		PoolConnection.dbName = "gimnasio";
		for (int i= 0; i< cantCon; i++) {
			connections.add(connect());
		}
	}
	
	public static PoolConnection getPoolConnection() {
		if (pool == null) {
			pool = new PoolConnection();
		}
		return pool;
	}
	
	private Connection connect() {
		try {
			/**
			 * Setting driver configuration
			 */
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://35.162.112.46:3306/gimnasio?useSSL=false", "gym", "uade.gym");
			/*
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbConnectString = jdbc + servidor; 
            Connection con = DriverManager.getConnection (dbConnectString, usuario, password);
            */
            return con;
		} catch (SQLException e) {
			System.out.println("Mensaje Error: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
			return null;
		} catch (Exception ex) {
			System.out.println("Mensaje Error: " + ex.getMessage());
			System.out.println("Stack Trace: " + ex.getStackTrace());
			return null;
		}
	}
	
	private void getConfiguration() {
		String configuracion = "ConfigBD.txt";
	    Properties propiedades;
	 
	    /**
	     * Cargando el fichero de propiedades
	     */
	    try {
	       FileInputStream f = new FileInputStream(configuracion);	 
	       propiedades = new Properties();
	       propiedades.load(f);
	       f.close();
	 
	       /**
	        * Leemos los valores de configuración que obtenemos
	        * línea por línea de nuestro archivo de configuración
	        * ConfigDB.txt
	        */
	       jdbc 		= propiedades.getProperty("jdbc"); 
	       servidor 	= propiedades.getProperty("servidor");
	       usuario 		= propiedades.getProperty("usuario");
	       password 	= propiedades.getProperty("password");
	       cantCon 		= Integer.parseInt(propiedades.getProperty("conexiones"));
	       dbName		= propiedades.getProperty("db_name");
	     } catch (Exception e) {
				System.out.println("Mensaje Error: " + e.getMessage());
				System.out.println("Stack Trace: " + e.getStackTrace());
	     }
	}
	
	public void closeConnections() {
		for (int i=0; i<connections.size();i++) {
			try {
				connections.elementAt(i).close();
			} catch(Exception e) {
				System.out.println("Mensaje Error: " + e.getMessage());
				System.out.println("Stack Trace: " + e.getStackTrace());
			}
		}
	}
	
	public Connection getConnection() {
		Connection c = null;
		if (connections.size()>0) {
			c = connections.remove(0);
		} else {
			c = connect();
			System.out.println("Se ha creado una nueva conexion fuera de los parametros de configuracion");
		}
		return c;
	}
	
	public void realeaseConnection(Connection c) {
		connections.add(c);
	}
	
}
