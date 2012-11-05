package prueba;
import prueba.RuedaPinchadaException;

public class Rueda {

	private int diametroPulgadas;
	private int anchuraNominalMm;
	private int ratioAspectoPc;
	private static final int limiteKm = 60000;
	private int rodaduraKm = 0;
	private boolean pinchada = false;
	private boolean cambiar = false;
	private float presionPSI;
	private float temperaturaC;
	
	
		
	

	Rueda(int diametroPulgadas, int anchuraNominalMm,
			int ratioAspectoPc,float presionPSI,float temperaturaC) {
		this.diametroPulgadas = diametroPulgadas;
		this.anchuraNominalMm = anchuraNominalMm;
		this.ratioAspectoPc = ratioAspectoPc;
		this.presionPSI=presionPSI;
		this.temperaturaC=temperaturaC;
	}

	// Rueda Estándar
	Rueda() {
		this(16,205,55,3,25);
	}

	public void rodar(int km) throws RuedaPinchadaException {
		if (! pinchada) {
			this.rodaduraKm += km;
			if (rodaduraKm >= limiteKm) {
				cambiar = true;
			}
		} else
			throw new RuedaPinchadaException("Esta pinchada, no puedes rodar!", presionPSI,65);
	}
	
	public void rodar(int km, float temperaturaC) throws RuedaPinchadaException {
		if (! pinchada) {
			this.rodaduraKm += km;
			if (rodaduraKm >= limiteKm) {
				cambiar = true;
			}
		} else
			throw new RuedaPinchadaException("Esta pinchada, no puedes rodar!", presionPSI,temperaturaC );
	}

	public void pinchar() {
		pinchada = true;
	}

	public void reparar() {
		pinchada = false;
	}
	
	public void print() {
		int difKm=limiteKm-rodaduraKm;
		System.out.println("Diámetro: "+diametroPulgadas+'"');
		System.out.println("Anchura: "+anchuraNominalMm+" mm");
		System.out.println("Relación de Aspecto: "+ratioAspectoPc+'%');
		if ( (difKm) > 0)
			System.out.println("Km: "+rodaduraKm+" restan "+difKm);
		else
			System.out.println("Km: "+rodaduraKm+" excedidos "+ (-difKm));
		System.out.println("Pinchada: "+pinchada);
		System.out.println("Cambiar: "+cambiar);
		System.out.println("Presion" + presionPSI);
		System.out.println("Temperatura " + temperaturaC + " grados");
	}
	
	public void println() {
		this.print();
		System.out.print('\n');
	}
	
	public static void main(String[] args) {
		Rueda r1=new Rueda(), r2=new Rueda(165,14,175,2,90);
		try {
			r1.rodar(75000);
			r1.pinchar();
			r2.rodar(34500);
			r1.rodar(5);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		r1.println();
		r2.println();
	}
}