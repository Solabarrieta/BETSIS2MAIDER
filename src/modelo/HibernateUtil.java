package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
			// Nota: forma de obtener el Session Factory en Hibernate 3
			// Crea una instancia de SessionFactory con los datos de configuración
			// de hibernate.cfg.xml, que debe estar situado en el subdirectorio “src”
			// Si se creara la instancia de SessionFactory de la siguiente manera:
			// new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
			// entonces, habría que colocar el fichero en el directorio raíz del proyecto
			// Y podría tener un nombre diferente a "hibernate.cfg.xml"
		}catch (Throwable ex) {
			System.err.println("Fallo creando el SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);}}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;} 
}


