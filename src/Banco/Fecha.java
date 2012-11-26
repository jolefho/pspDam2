package Banco;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fecha {
	private int dia;
	private int mes;
	private int año;
	private static final Pattern fechaPattern = Pattern
			.compile("(\\d{1,2})[-./]?(\\d{1,2})[-./]?(\\d{1,4})");

	public Fecha(int dia, int mes, int año) throws Exception {
		set(dia, mes, año);
	}

	public Fecha() throws Exception {
		this(new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH),
				new GregorianCalendar().get(GregorianCalendar.MONTH) + 1,
				new GregorianCalendar().get(GregorianCalendar.YEAR));
	}

	public void set(int dia, int mes, int anio) throws Exception {
		String fecha = dia + "" + mes + "" + anio;
		Matcher m = fechaPattern.matcher(fecha);
		if (m.matches()) {
			if (mes < 1 || mes > 12) {
				throw new Exception("Mes erroneo");
			}
			if (anio == 0) {
				throw new Exception("Año 0");
			}
			if (dia < 1 || dia > 31) {
				throw new Exception("Día negativo");
			}

			switch (mes) {
			case 2:
				if (año % 4 != 0) {
					if (dia > 28) {
						throw new Exception("Día erroneo");
					}
				} else {
					if (año % 100 == 0) {
						if (dia > 28) {
							throw new Exception("Día erroneo");
						}
					} else {
						if (dia > 29) {
							throw new Exception("Día erroneo");
						}
					}
				}
				break;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				break;
			default:
				if (dia > 30) {
					throw new Exception("Día erroneo");
				}
				break;
			}
			this.dia = dia;
			this.mes = mes;
			año = anio;
		} else {
			throw new Exception("Esta mal");
		}

	}

	public void print() {
		System.out.print(dia + "/" + mes + "/" + año);
	}

	public void println() {
		print();
		System.out.println();
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

	public void setDia(int dia) {
		this.dia = dia;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public static void main(String[] args) {
		try {
			Fecha f = new Fecha(21, 1, 1989);
			f.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}