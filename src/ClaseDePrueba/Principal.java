package ClaseDePrueba;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import Servicios.Consulta;
import Utilidades.*;

public class Principal {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		// TODO Auto-generated method stub
		
		Persona p = new Persona();
		List<Field> atributos = UBean.obtenerAtributos(p);

		/*for(Field f : atributos) 
		{
			System.out.println(f.getName());
		}*/
		
		UBean.ejecutarSet(p,"nombre","Hugo");
		UBean.ejecutarSet(p,"apellido","Gomez");
		UBean.ejecutarSet(p,"dni","12345678");
		UBean.ejecutarSet(p,"edad",55);
		/*System.out.println(UBean.ejecutarGet(p, "nombre"));
		System.out.println(UBean.ejecutarGet(p, "apellido"));
		System.out.println(UBean.ejecutarGet(p, "dni"));
		System.out.println(UBean.ejecutarGet(p, "edad"));*/
		
		try{
			
			//Consulta.guardar(p);
			//Consulta.modificar(p);
			
		    //Consulta.eliminar(p);
			System.out.println(Consulta.ObtenerPorId(p.getClass(), 4).toString());
			//Consulta.ObtenerPorId(p.getClass(), 4);
		/*Class c = Persona.class;
		Method[] m = c.getDeclaredMethods();

			for(Method mt : m)
			{
				if(mt.getAnnotation(Anotacion1.class)!=null)
				{
					//System.out.println(mt.getName());
					mt.invoke(c.newInstance(), null);
				} 
			}*/
			
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
			
			

	}
	
		

}
