package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	
	public static Connection AbrirConexion() throws ClassNotFoundException, SQLException
	{
		Connection c = null;
		
		try{
			
				Class.forName("com.mysql.jdbc.Driver");
				 c =	DriverManager.getConnection("JDBC:mysql://localhost:3306/test");
				System.out.println("Conectó");
			}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return c;
		
	}
	
	public static void CerrarConexion(Connection c)
	{
		try{
			
			c.close();
			}
		catch(Exception ex)
		   {
			ex.printStackTrace();
		   }
		
	}
}
