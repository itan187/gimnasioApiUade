package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import models.CertificadoMedico;
import models.Abono;
import models.Actividad;
import models.Corporativa;
import models.Inscripcion;
import models.Normal;
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
			e.printStackTrace();
		}
		
	}

	public void insert(Object o) {
		try {
			Socio a = (Socio)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into " + PoolConnection.dbName + ".Socio (documento, nombre, domicilio, telefono, mail, abono, estado) values (?,?,?,?,?,?,?)");
			
			/**
			 * Agregando los campos
			 */
			s.setInt(1,		a.getDocumento());
			s.setString(2, 	a.getNombre());
			s.setString(3,	a.getDomicilio());
			s.setString(4, 	a.getTelefono());
			s.setString(5, 	a.getEmail());
			s.setInt(6, 	a.getAbono().getCodigo());
			s.setInt(7, 	(a.getEstado()) ? 1 : 0);
			s.execute();
			
			/**
			 * Recorremos las inscripciones del socio y las vamos guardando en
			 * SocioInscripciones
			 */
			PreparedStatement sx = con.prepareStatement("insert into "+ PoolConnection.dbName + ".SocioInscripcion values (?,?)");
			
			sx.setInt(1, a.getDocumento());
			sx.setInt(2, a.getInscripcion().getNumero());
			sx.execute();
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
			e.printStackTrace();
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
					"set nombre = ?, " +
					"domicilio = ?, " +
					"telefono = ?, " +
					"mail = ?, " +
					//"set abono = ?," +
					"estado = ? where documento = " + a.getDocumento()
			);

			/**
			 * Agregando los campos
			 */
			s.setString(1, 	a.getNombre());
			s.setString(2,	a.getDomicilio());
			s.setString(3, 	a.getTelefono());
			s.setString(4, 	a.getEmail());
			//s.setInt(5, 	(a.getAbono() != null) ? a.getAbono().getCodigo() : null);
			s.setInt(5, 	(a.getEstado()) ? 1 : 0);
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Buscar socio 
	 * 
	 * @param documento
	 * @return Socio
	 */
	public Socio buscarSocio (int documento) {
		try {
			Socio a = null;
			
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from " + PoolConnection.dbName + ".Socio where documento = ?");
			s.setInt(1, documento);
			ResultSet result = s.executeQuery();
			
			while (result.next()) {
				int doc 				= result.getInt(1);
				String nombre 			= result.getString(2);
				String domicilio 		= result.getString(3);
				String telefono 		= result.getString(4);
				String email	 		= result.getString(5);
				Integer abono 			= result.getInt(6);
				boolean estado 			= (result.getInt(7) == 1) ? true : false;
				
				/**
				 * Recorró buscando las inscripciones 
				 * de un Socio en SocioInscripcion
				 */
				PreparedStatement si = con.prepareStatement("select * from " + PoolConnection.dbName + ".SocioInscripcion where documento = ?");
				si.setInt(1, documento);
				ResultSet res = si.executeQuery();
				
				//Vector<Inscripcion> inscripciones = new Vector<Inscripcion>();
				
				while (res.next()) {
					int inscripcionNumero 	= res.getInt(2);
					
					boolean inscripcionEstado;
					
					Vector<Actividad> inscripcionActividades = new Vector<Actividad>();
					String inscripcionEmpresa;
					Date inscripcionVigencia;
					
					Normal i = InscripcionNormalAbm.getInstancia().buscarInscripcion(inscripcionNumero);
					Corporativa in = InscripcionCorporativoAbm.getInstancia().buscarInscripcion(inscripcionNumero);
					
					if (i != null) {
						inscripcionEstado = i.getEstado();
						inscripcionActividades = i.getActividades();
						
						//Normal inscripcion = new Normal(inscripcionEstado, inscripcionNumero, inscripcionActividades);
						//inscripciones.add(inscripcion);
						
					} else if (in != null) {
						inscripcionEstado = in.getEstado();
						inscripcionActividades = in.getActividades();
						inscripcionEmpresa = in.getEmpresa();
						inscripcionVigencia = in.getVigencia();
						
						//Corporativa inscripcion = new Corporativa(inscripcionEstado, inscripcionNumero, inscripcionActividades, inscripcionEmpresa, inscripcionVigencia);
						//inscripciones.add(inscripcion);
					}
					
					//Inscripcion inscripcion = new Inscripcion(inscripcionEstado, inscripcionNumero, inscripcionActividades);
				}
				/**
				 * Recorró los Aptos Médicos
				 * de Socio en SocioAptosMedicos
				 */
				PreparedStatement sap = con.prepareStatement("select * from " + PoolConnection.dbName + ".CertificadoMedico where numSocio = ? and estado = 1");
				sap.setInt(1, documento);
				ResultSet resu = sap.executeQuery();
				
				Vector<CertificadoMedico> aptosMedicos = new Vector<CertificadoMedico>();
				
				while (resu.next()) {
					int aptoNumero = resu.getInt(2);
					
					CertificadoMedico cert = CertificadoMedicoAbm.getInstancia().buscarCertificado(aptoNumero);
					
					if (cert != null) {
						CertificadoMedico certificado = new CertificadoMedico(
								aptoNumero, 
								cert.getSocio(),
								cert.getFechaCreacion(), 
								cert.getVencimiento(), 
								cert.getProfesional(), 
								cert.getObservaciones(), 
								cert.getEstado()
							);
						aptosMedicos.add(certificado);
					}
					
				}
				Abono ab = AbonoAbm.getInstancia().buscarAbono(abono);

				//a = new Socio(doc, nombre, domicilio, telefono, email, ab, inscripcion, aptosMedicos, estado);
				
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
