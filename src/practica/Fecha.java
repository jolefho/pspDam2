package practica;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fecha {

	public int dia;
	public int mes;
	public int año;
	private static final Pattern fechaPattern = Pattern.compile("(\\d{1,2})[-.]?({1,2})[-./]?(\\d{1,4})");

	Fecha(int dia, int mes, int año) {
		set(dia, mes, año);
	}

	private void set(int dia, int mes, int año) {
		String fecha = dia +""+ mes + ""+ año;
		Matcher m = fechaPattern.matcher(fecha);
		if (m.matches()){
			if (dia<1 || dia >31){
				trow new Exception("Mes Incorrecto");				
			}
			
			if (mes < 1 || mes >12){
				trow new Exception("Mes Incorrecto");
			}
			
			if (año = 0){
				trow new Exception("Año Incorrecto");
			}
		}
				
		
	}
	

	public Fecha(){
		this(new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH),
		new GregorianCalendar().get(GregorianCalendar.MONTH),
		new GregorianCalendar().get(GregorianCalendar.YEAR));
		
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAño() {
		return año;
	}

	public static void main(String[] args) {
		try{
			Fecha f = new Fecha(2,3,1984);
			f.println();
		}catch (Exception e){
			e.printStackTrace()
		}

	}

}
