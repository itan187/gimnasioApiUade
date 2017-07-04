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
	
	public void delete(Object d) {
		try {
			Liquidacion a = (Liquidacion)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Liquidacion where anio = ? and mes = ?");
			s.setInt(1, a.getAnio());
			s.setInt(2, a.getMes());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insert(Object o) {
		try {
			Liquidacion a = (Liquidacion)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".Liquidacion values (?,?)");
			/**
			 * Agregando los campos
			 */
			s.setInt(1,a.getAnio());
			s.setInt(2, a.getMes());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Liquidacion buscarLiquidacion (int anio, int mes) {
		try {
			Liquidacion a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Liquidacion where anio = ? and mes = ?");
			s.setInt(2, anio);
			s.setInt(3, mes);
			ResultSet result = s.executeQuery();
			
			Vector<Empleado> empleados = new Vector<Empleado>();
			
			while (result.next()) {
				int num = result.getInt(1);
				
				/**
				 * Buscar todas las liquidaciones de los empleados
				 * a = new Liquidacion();
				 */
				
				PreparedStatement ss = con.prepareStatement("select * from " + PoolConnection.dbName + ".LiquidacionEmpleado where numLiquidacion = ?");
				s.setInt(1, num);
				ResultSet res = ss.executeQuery();
				
				while (res.next()) {
					Administrativo admin = EmpleadoAdminAbm.getInstancia().buscarEmpleado(res.getInt(2));
					if (admin != null) empleados.add(admin);
					
					HorarioCompleto fulltime = EmpleadoHorarioCompletoAbm.getInstancia().buscarEmpleado(res.getInt(2));
					if (fulltime != null) empleados.add(fulltime);
					
					Particular parttime = EmpleadoHorarioPartAbm.getInstancia().buscarEmpleado(res.getInt(2));
					if (parttime != null) empleados.add(parttime);
				}
			}
			a = new Liquidacion(anio, mes, empleados);
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
