package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UBean {

		
		
		public static ArrayList<Field> obtenerAtributos(Object o)
		{
			ArrayList<Field> campos = new ArrayList<Field>();
			Field[] fields = o.getClass().getDeclaredFields();
			
			for(int i=0; i< fields.length;i++)
			{
			campos.add(fields[i]);
			}
			return campos;
			
		}
		
		
		
		public static void ejecutarSet(Object o, String att, Object valor)
		{
			try
			{
				
				
				Method[] metodos = o.getClass().getDeclaredMethods();
				String aux = att.substring(0, 1).toUpperCase() +att.substring(1);
				String nombre = "set"+aux;
				
				for(Method misMet : metodos)
				{
					if(nombre.equals(misMet.getName()))
					{
						misMet.invoke(o, valor);
					}
				}
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		public static Object ejecutarGet(Object o,String att)
		{
			Object obj = null;

			try
			{
				Method[] metodos = o.getClass().getDeclaredMethods();
				String aux = att.substring(0, 1).toUpperCase() +att.substring(1);
				String nombre = "get"+aux;
					
				
				for(Method misMet : metodos)
				{
					if(nombre.equals(misMet.getName()))
					{
						obj = misMet.invoke(o, new Object[0]);
					}
				}
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return obj;
			
		}
		
		
}
