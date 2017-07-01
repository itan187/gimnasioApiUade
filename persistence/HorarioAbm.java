package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Vector;

import models.Horario;

public class HorarioAbm extends HorarioPersistence {

	private static HorarioAbm instancia;
	
	private HorarioAbm() {
	}
	
	public static HorarioAbm getInstancia() {
		if (instancia == null) {
			instancia = new HorarioAbm();
		}
		return instancia;
	}
	
	public void delete(Object d) {
		try {
			Horario a = (Horario)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Horarios where horario = ?");
			s.setTime(2, a.getHorario());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}
		
	}
	
	public void insert(Object o) {
		try {
			Horario a = (Horario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into "+ PoolConnection.dbName + ".Horarios values (?)");
			/**
			 * Agregando los campos
			 */
			s.setTime(2, a.getHorario());
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
			Horario a = (Horario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".Horarios " +
					"set horario = ?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setTime(2, a.getHorario());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}

	}
	
	/**
	 * Buscamos un horario puntual para retornar la ubicación
	 * 
	 * @param horario
	 * @return int
	 */
	public int buscarHorario (Time horario) {
		try {
			int numeroHorario = 0;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Horarios where horario = ?");
			s.setTime(2, horario);
			ResultSet res = s.executeQuery();
			
			while (res.next()) {
				numeroHorario = res.getInt(1);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return numeroHorario;
		} catch (Exception e) {
			System.out.println();
		}
		return 0;
	}
}