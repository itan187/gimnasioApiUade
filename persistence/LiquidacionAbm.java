package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Administrativo;
import models.HorarioCompleto;
import models.Liquidacion;
import models.Particular;

public class LiquidacionAbm extends LiquidacionPersistence {
	private static LiquidacionAbm instancia;
	
	public static LiquidacionAbm getInstancia() {
		if (instancia == null) {
			instancia = new LiquidacionAbm();
		}
		return instancia;
	}
	
	public void insert(Object o) {
		try {
			Liquidacion a = (Liquidacion)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".Liquidacion values (?,?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1,	a.getNumero());
			s.setInt(2, a.getAnio());
			s.setInt(3, a.getMes());
			s.execute();
			
			/**
			 * Recorremos todos los empleados liquidandolos
			 * 1) Administrativos
			 * 2) Profesores
			 * 		a) Horario completo
			 * 		b) Particular
			 */
			Vector<Administrativo> admin = EmpleadoAdminAbm.getInstancia().select();
			
			for (Administrativo ad : admin) {
				PreparedStatement sad = con.prepareStatement("insert into " + PoolConnection.dbName + ".LiquidacionEmpleado values (?,?,?)");
				sad.setInt(1, 		a.getNumero());
				sad.setInt(2, 		ad.getDocumento());
				sad.setFloat(3, 	ad.getCalcularSueldo());
				sad.execute();
			}
			
			Vector<HorarioCompleto> full = EmpleadoHorarioCompletoAbm.getInstancia().select();
			
			for (HorarioCompleto f : full) {
				PreparedStatement sf = con.prepareStatement("insert into " + PoolConnection.dbName + ".LiquidacionEmpleado values (?,?,?)");
				sf.setInt(1, 		a.getNumero());
				sf.setInt(2, 		f.getDocumento());
				sf.setFloat(3, 		f.getCalcularSueldo());
				sf.execute();
			}
			
			Vector<Particular> part = EmpleadoHorarioPartAbm.getInstancia().select();
			
			for (Particular p : part) {
				PreparedStatement sp = con.prepareStatement("insert into " + PoolConnection.dbName + ".LiquidacionEmpleado values (?,?,?)");
				sp.setInt(1, 		a.getNumero());
				sp.setInt(2, 		p.getDocumento());
				sp.setFloat(3, 		p.getCalcularSueldo());
				sp.execute();
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean existeLiquidacion (int anio, int mes) {
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Liquidacion where anio = ? and mes = ?");
			s.setInt(1, anio);
			s.setInt(2, mes);
			ResultSet result = s.executeQuery();
			
			while (result.next()) {
				return true;
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
