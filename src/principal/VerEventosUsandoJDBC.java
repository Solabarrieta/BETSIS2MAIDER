package principal;
import java.sql.*;
import java.util.Date;

public class VerEventosUsandoJDBC {
	public static void main(String[] args) {
		Connection c;
		Statement s;
		ResultSet rs;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/eventos","root","2000");
			System.out.println(c.getCatalog());
			s = c.createStatement();
			rs = s.executeQuery("CREATE TABLE PRUEBA(varcahr2(20) ID)");
			System.out.println("prueba (ID)");
			while (rs.next()){
				Long id = rs.getLong("ID");
				System.out.println(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

