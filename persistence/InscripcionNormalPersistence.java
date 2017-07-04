package persistence;

import java.util.Vector;

public abstract class InscripcionNormalPersistence {
	public abstract void insert (Object o);
	public abstract void update (Object o);
	public abstract void delete (Object d);
	public abstract Vector<String> listado ();
}
