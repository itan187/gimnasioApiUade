package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import models.CertificadoMedico;
import models.Actividad;
import models.Corporativa;
import models.Inscripcion;
import models.Normal;
//import models.CertificadoMedico;
import models.Socio;

public class SocioAbm extends SocioPersistence {
	private static SocioAbm instancia;
	
	private SocioAbm() {
	}
	
	public static SocioAbm getInstancia() {
		if (instancia == null) {
			instancia = new SocioAbm();
		}
		return instancia;
	}

	public void delete(Object d) {
		try {
			Socio a = (Socio)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("delete from " + PoolConnection.dbName + ".Socio where documento = ?");
			s.setInt(1, a.getDocumento());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}
		
	}

	public void insert(Object o) {
		try {
			Socio a = (Socio)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".Socio (documento, nombre, domicilio, telefono, mail, estado) values (?,?,?,?,?,?)");
			
			/**
			 * Agregando los campos
			 */
			s.setInt(1,		a.getDocumento());
			s.setString(2, 	a.getNombre());
			s.setString(3,	a.getDomicilio());
			s.setString(4, 	a.getTelefono());
			s.setString(5, 	a.getEmail());
			s.setInt(6, 	(a.getEstado()) ? 1 : 0);
			s.execute();
			
			/**
			 * Recorremos las inscripciones del socio y las vamos guardando en
			 * SocioInscripciones
			 */
			/*
			for (int i = 0; i < a.getInscripciones().size(); i++) {
				PreparedStatement sx = con.prepareStatement("insert into "+ PoolConnection.dbName + ".SocioInscripcion values (?,?)");
				
				sx.setInt(1, a.getDocumento());
				sx.setInt(2, a.getInscripciones().elementAt(i).getNumero());
				sx.execute();
			}
			*/
			/**
			 * Recorremos las aptos médicos del socio y las vamos guardando en
			 * SocioAptosMedicos
			 */
			/*
			for (int i = 0; i < a.getAptosMedicos().size(); i++) {
				PreparedStatement sx = con.prepareStatement("insert into "+ PoolConnection.dbName + ".SocioAptosMedicos values (?,?)");
				
				sx.setInt(1, a.getDocumento());
				sx.setInt(2, a.getAptosMedicos().elementAt(i).getNumAptoMedico());
				sx.execute();
			}*/
			
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
			Socio a = (Socio)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update " + PoolConnection.dbName + ".Socio " +
					"set documento = ?," +
					"set nombre = ?," +
					"set domicilio = ?," +
					"set telefono = ?," +
					"set email = ?," +
					"set abono = ?," +
					"set estado = ?)"
			);

			/**
			 * Agregando los campos
			 */
			s.setInt(1,a.getDocumento());
			s.setString(2, a.getNombre());
			s.setString(3,a.getDomicilio());
			s.setString(4, a.getTelefono());
			s.setString(5, a.getEmail());
			s.setInt(6, a.getAbono());
			s.setInt(7, (a.getEstado()) ? 1 : 0);
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println();
		}

	}
	
	public Socio buscarSocio (int documento) {
		try {
			Socio a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from Socio where documento = ?");
			s.setInt(1, documento);
			ResultSet result = s.executeQuery();
			
			while (result.next()) {
				int doc 			= result.getInt(1);
				String nombre 		= result.getString(2);
				String domicilio 	= result.getString(3);
				String telefono 	= result.getString(4);
				String email	 	= result.getString(5);
				int abono 			= result.getInt(6);
				boolean estado 		= (result.getInt(7) == 1) ? true : false;
				
				/**
				 * Recorró buscando las inscripciones 
				 * de un Socio en SocioInscripcion
				 */
				
				PreparedStatement si = con.prepareStatement("select * from " + PoolConnection.dbName + ".SocioInscripcion where documento = ?");
				si.setInt(1, documento);
				ResultSet res = si.executeQuery();
				
				Vector<Inscripcion> inscripciones = null;
				
				while (res.next()) {
					int inscripcionNumero 	= res.getInt(2);
					boolean inscripcionEstado;
					Vector<Actividad> inscripcionClases;
					String inscripcionEmpresa;
					Date inscripcionVigencia;
					
					Normal i = InscripcionNormalAbm.getInstancia().buscarInscripcion(inscripcionNumero);
					Corporativa in = InscripcionCorporativoAbm.getInstancia().buscarInscripcion(inscripcionNumero);
					
					if (i != null) {
						inscripcionEstado = i.getEstado();
						inscripcionClases = i.getClases();
						
						Normal inscripcion = new Normal(inscripcionEstado, inscripcionNumero, inscripcionClases);
						inscripciones.add(inscripcion);
						
					} else if (in != null) {
						inscripcionEstado = in.getEstado();
						inscripcionClases = in.getClases();
						inscripcionEmpresa = in.getEmpresa();
						inscripcionVigencia = in.getVigencia();
						
						Corporativa inscripcion = new Corporativa(inscripcionEstado, inscripcionNumero, inscripcionClases, inscripcionEmpresa, inscripcionVigencia);
						inscripciones.add(inscripcion);
					}
					
				}
				
				/**
				 * Recorró los Aptos Médicos
				 * de Socio en SocioAptosMedicos
				 */
				PreparedStatement sap = con.prepareStatement("select * from " + PoolConnection.dbName + ".SocioAptosMedicos where documento = ?");
				si.setInt(1, documento);
				ResultSet resu = sap.executeQuery();
				
				Vector<CertificadoMedico> aptosMedicos = null;
				
				while (resu.next()) {
					int aptoNumero = resu.getInt(2);
					
					CertificadoMedico cert = CertificadoMedicoAbm.getInstancia().buscarCertificado(aptoNumero);
					
					if (cert != null) {
						CertificadoMedico certificado = new CertificadoMedico(
								aptoNumero, 
								cert.getFechaCreacion(), 
								cert.getVencimiento(), 
								cert.getProfesional(), 
								cert.getObservaciones(), 
								cert.getEstado()
							);
						aptosMedicos.add(certificado);
					}
				}
				
				a = new Socio(doc, nombre, domicilio, telefono, email, abono, inscripciones, aptosMedicos, estado);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			System.out.println("Error");
		}
		return null;
	}

}
