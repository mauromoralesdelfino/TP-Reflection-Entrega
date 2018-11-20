package Servicios;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Anotaciones.*;
import Anotaciones.Tabla;
import Utilidades.Conexion;
import Utilidades.UBean;





public class Consulta {
	
	private static List<Field> misCampos;
	private static String nombreTabla;
	private static String atributos;
	private static String miQuery;
	private static String values;
	private static String id;
	private static String myid;	
	public static void ejecutar(String consulta) throws ClassNotFoundException, SQLException {
		try
		{
			Connection c =	Conexion.AbrirConexion();
		PreparedStatement ps = c.prepareStatement(consulta);
		ps.execute();
		c.close();}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void guardar(Object o) throws ClassNotFoundException, SQLException
	{
		
	misCampos=UBean.obtenerAtributos(o);
	atributos = "";
	values = "";
	miQuery="";
	nombreTabla = 	o.getClass().getAnnotation(Tabla.class).nombre(); 
				
				for(Field f : misCampos)
				{
				if((f.getAnnotation(Id.class)) == null)
					{
						f.getAnnotation(Columna.class).nombre();
						
						atributos += "`" + f.getAnnotation(Columna.class).nombre() + "`,";
						values += "'" + UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre())+ "',";
					}
				}
				
				atributos = atributos.substring(0, atributos.length()-1);
				values = values.substring(0, values.length()-1);
				
				miQuery += "Insert into " + nombreTabla +"("+ atributos +")"+ " VALUES " + "(" + values + ")" ;
			//"Insert into ejemplo(Nombre,Apellido,Edad,Dni) value (?,?,?,?)")
				System.out.println(miQuery);
			Consulta.ejecutar(miQuery);
				
		
	}

	public static void modificar(Object o) throws ClassNotFoundException, SQLException
	{
		
		
		misCampos=UBean.obtenerAtributos(o);
		atributos = "";
		values = "";
		miQuery="";
		id="";
			
				nombreTabla = 	o.getClass().getAnnotation(Tabla.class).nombre(); 
		
		
		for(Field f : misCampos)
		{
		if((f.getAnnotation(Id.class)) != null)
			{
			
				f.getAnnotation(Columna.class).nombre();
				id += "`" + f.getAnnotation(Columna.class).nombre() + "`,";
				
			}
		else
			{
				f.getAnnotation(Columna.class).nombre();
				values += "`" + f.getAnnotation(Columna.class).nombre() + "` = '" +  UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre()) + "',";
				//values += "'" + UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre())+ "',";
				//id += "`" + f.getAnnotation(Columna.class).nombre() + "` = '" +  UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre()) +   "'";
			}
		}
		
		id = id.substring(0, id.length()-1);
		values = values.substring(0, values.length()-1);
		System.out.println(id);
		System.out.println(values);
		//Insert into Persona(`nombre`,`apellido`,`dni`,`edad`) VALUES ('Pedro','Gomildez','12345678','55')
		//("Update ejemplo SET nombre=?, apellido=?, edad=?, dni=? where nombre=?");
		miQuery+= "Update " + nombreTabla + " SET " + values + " WHERE " + id + " = 2 ";
		System.out.println(miQuery);
		
		//Consulta.ejecutar(miQuery);
		
	}
	
		public static void eliminar(Object o) throws ClassNotFoundException, SQLException 
	{
		misCampos = UBean.obtenerAtributos(o);
		atributos = "";
		values = "";
		miQuery="";
		id="";
		
		
		nombreTabla = o.getClass().getAnnotation(Tabla.class).nombre();
		
		for(Field f : misCampos)
		{
		if((f.getAnnotation(Id.class)) != null)
			{
			
				f.getAnnotation(Columna.class).nombre();
				id += "`" + f.getAnnotation(Columna.class).nombre() + "`,";
				
			}
		else
			{
				f.getAnnotation(Columna.class).nombre();
				values += "`" + f.getAnnotation(Columna.class).nombre() + "` = '" +  UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre()) + "'AND";
			
			}
		}
		
		id = id.substring(0, id.length()-1);
		values = values.substring(0, values.length()-3);
		System.out.println(id);
		System.out.println(values);
	 
		miQuery+= "Delete from " + nombreTabla + " where " + values ;
		System.out.println(miQuery);
		
		//Consulta.ejecutar(miQuery);
		
	}
		public static Object ObtenerPorId(Class c, Object id) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
	
			Constructor<?>[] cons = c.getConstructors();
			/*for(Constructor<?>ca : cons)
			{
				System.out.println(ca.getParameterTypes());
			}*/
			
			Object obj = cons[0].newInstance();
			//obj.getClass().toString();
			
			
			
			misCampos = UBean.obtenerAtributos(obj);
			atributos = "";
			values = "";
			miQuery="";
			myid="";
			
			
			nombreTabla = obj.getClass().getAnnotation(Tabla.class).nombre();
			
			for(Field f : misCampos)
			{
			if((f.getAnnotation(Id.class)) != null)
				{
				
					f.getAnnotation(Columna.class).nombre();
					myid += f.getAnnotation(Columna.class).nombre() + " = " + id;
					
				}
		
			}
			System.out.println(myid);
			miQuery = "SELECT * FROM " + nombreTabla + " WHERE " + myid;
			System.out.println(miQuery);
			try
			{
				Connection conex =	Conexion.AbrirConexion();
			PreparedStatement ps = conex.prepareStatement(miQuery);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()) {
				obj = cons[1].newInstance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			conex.close();}	
			catch(Exception e)
			{
				e.printStackTrace();
			}
			/* PreparedStatement st = c.prepareStatement("Select * From ejemplo where id=?");
			 * "Select * From ejemplo where id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				p = new Persona();
				p.setNombre(rs.getString(1));
				p.setApellido(rs.getString(2));
				p.setEdad(rs.getInt(3));
				p.setDni(rs.getString(4)); 
				
				
				
			}
			 * */
			
			
			
			return obj;
		}
	
	
}
