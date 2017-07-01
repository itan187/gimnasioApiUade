package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.CertificadoMedico;


public class CertificadoMedicoAbm extends CertificadoMedicoPersistence {
	private static CertificadoMedicoAbm instancia;
	
	private CertificadoMedicoAbm() {
	}
	
	public static CertificadoMedicoAbm getInstancia() {
		if (instancia == null) {
			instancia = new CertificadoMedicoAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			CertificadoMedico a = (CertificadoMedico)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".CertificadoMedico where numero = ?");
			s.setInt(1, a.getNumAptoMedico());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}
		
	}

	public void insert(Object o) {
		try {
			CertificadoMedico a = (CertificadoMedico)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".CertificadoMedico values (?,?,?,?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1, 		a.getNumAptoMedico());
			s.setDate(2, 		(Date) a.getFechaCreacion());
			s.setDate(3, 		(Date) a.getVencimiento());
			s.setString(4, 		a.getProfesional());
			s.setString(5, 		a.getObservaciones());
			s.setBoolean(6, 	a.getEstado());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
	
	}

	public Vector<Object> select(Object o) {
		return null;
	}

	public void update(Object o) {
		try {
			CertificadoMedico a = (CertificadoMedico)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".CertificadoMedico " +
					"set numero = ?," +
					"set fecha_creacion = ?," +
					"set vencimiento =?," +
					"set profesional =?," +
					"set observaciones =?," +
					"set estado =?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1, 		a.getNumAptoMedico());
			s.setDate(2, 		(Date) a.getFechaCreacion());
			s.setDate(3, 		(Date) a.getVencimiento());
			s.setString(4, 		a.getProfesional());
			s.setString(5, 		a.getObservaciones());
			s.setBoolean(6, 	a.getEstado());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}

	}
	
	public CertificadoMedico buscarAbono (int numero) {
		try {
			CertificadoMedico a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".CertificadoMedico where numero = ?");
			s.setInt(1, numero);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				//int numero 		= result.getInt(1);
				Date fechaCreacion 	= result.getDate(2);
				Date vencimiento 	= result.getDate(3);
				String profesional 	= result.getString(4);
				String observaciones 	= result.getString(5);
				boolean estado		= result.getBoolean(6);
				
				a = new CertificadoMedico(numero, fechaCreacion, vencimiento, profesional, observaciones, estado);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}

}
