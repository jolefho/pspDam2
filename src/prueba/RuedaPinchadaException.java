package prueba;

public class RuedaPinchadaException extends Exception {

	private float presionPSI;
	private float temperaturaC;
	
	public RuedaPinchadaException(String mensaje) {
	super(mensaje);
		}
	
	public RuedaPinchadaException(String mensaje , float presionPSI , float temperaturaC){
		super(mensaje);
		this.presionPSI = presionPSI;
		this.temperaturaC = temperaturaC;
	}

	public float getPresionPSI() {
		return presionPSI;
	}

	public void setPresionPSI(float presionPSI) {
		this.presionPSI = presionPSI;
	}

	public float getTemperaturaC() {
		return temperaturaC;
	}

	public void setTemperaturaC(float temperaturaC) {
		this.temperaturaC = temperaturaC;
	}
	public String toString(){
		return"";
	}
}
