package persistence;

import java.util.Vector;

public abstract class CertificadoMedicoPersistence {
	public abstract void insert (Object o);
	public abstract void update (Object o);
	public abstract void delete (Object d);
	public abstract Vector<Object> select (Object o);
}
