package Banco;

public class Cuenta {
	private Fecha fechaCreacion;
	private char[] ccc = new char[20];
	private volatile Double saldo;

	public Cuenta(Fecha fechaCreacion, String ccc, double saldo) {
		this.fechaCreacion = fechaCreacion;
		StringBuilder aux = new StringBuilder(ccc);
		aux.setLength(20);
		this.ccc = aux.toString().toCharArray();
		this.saldo = saldo;
	}

	
	
	
	public  synchronized void integrar(double cantidad) {
		
			saldo += cantidad;
		
		
	}

	public synchronized  void reintegrar(double cantidad) throws SinSaldoException {
	
		if (cantidad > saldo) {
			throw new SinSaldoException("No tienes dinero en tu cuenta");
		}
		saldo -= cantidad;
		}
	

	public Fecha getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Fecha fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public char[] getCcc() {
		return ccc;
	}

	public void setCcc(char[] ccc) {
		this.ccc = ccc;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	private class ThreadIntegrar implements Runnable {
		private double cantidad;

		public ThreadIntegrar(double cantidad) {
			this.cantidad = cantidad;
		}

		public void run() {
			synchronized (saldo){
			integrar(cantidad);
			System.out.println(Thread.currentThread().getName()
					+ " Saldo actual: " + saldo);
			}
		}
	}

	private class ThreadReintegrar implements Runnable {
		private double cantidad;

		public ThreadReintegrar(double cantidad) {
			this.cantidad = cantidad;
		}

		public void run() {
			synchronized (saldo){
			try {
				reintegrar(cantidad);
				System.out.println(Thread.currentThread().getName()
						+ " Saldo actual: " + saldo);
			} catch (SinSaldoException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			}
		}
	}
	
	public void print(){
		System.out.println(("Fecha"));
		fechaCreacion.print();
		System.out.println(" codigo " + new String(ccc) + " saldo: " + saldo);
	}
	
public void println(){
		print();
		System.out.println();
	}

	public static void main(String args[]) {
		try {
			Cuenta c = new Cuenta(new Fecha(), "12345678901234567890", 1000);
			Thread ingresos[] = new Thread[100];
			for (int i = 0; i < 100; i++) {
			
				ingresos[i] = new Thread(c.new ThreadIntegrar(1));
				ingresos[i].start();
			}
			Thread reintegro[] = new Thread[100];
			for (int i = 0; i < 100; i++) {
				reintegro[i] = new Thread(c.new ThreadReintegrar(1));
				reintegro[i].start();
			}
			for(int i=0;i<100;i++){
				ingresos[i].join();
				reintegro[i].join();
			}
			c.println();
			
		} catch (Exception e) {
			System.err.println("Fecha incorrecta");
			e.printStackTrace();
		}

	}

}