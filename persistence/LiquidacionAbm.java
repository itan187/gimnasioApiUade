package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import models.Administrativo;
import models.Empleado;
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
				PreparedStatement ss = con.prepareStatement("insert into " + PoolConnection.dbName + ".LiquidacionEmpleado values (?,?,?)");
				ss.setInt(1, 		a.getNumero());
				ss.setInt(2, 		ad.getDocumento());
				ss.setFloat(3, 		ad.getCalcularSueldo());
			}
			
			Vector<HorarioCompleto> full = EmpleadoHorarioCompletoAbm.getInstancia().select();
			
			for (Administrativo ad : admin) {
				PreparedStatement ss = con.prepareStatement("insert into " + PoolConnection.dbName + ".LiquidacionEmpleado values (?,?,?)");
				ss.setInt(1, 		a.getNumero());
				ss.setInt(2, 		ad.getDocumento());
				ss.setFloat(3, 		ad.getCalcularSueldo());
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
