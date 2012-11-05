package multiproceso;

import java.io.*;
import java.util.Vector;

//captura la salida (stdio) de un proceso externo

public class Execute {
	public static void main(String args[]) {
		try {
			String linea;

			Vector<Process> procesos = new Vector<Process>();
			Vector<BufferedReader> flujosEntrada = new Vector<BufferedReader>();
			Vector<BufferedReader> flujosErrores = new Vector<BufferedReader>();

			for (int i = 0; i < args.length; i++) {
				procesos.add(Runtime.getRuntime().exec(args[i]));
				flujosEntrada.add(new BufferedReader(
						new InputStreamReader(procesos.elementAt(i)
								.getInputStream())));
				flujosErrores.add(new BufferedReader(
						new InputStreamReader(procesos.elementAt(i)
								.getErrorStream())));

			}

			for (int i = 0; i < args.length; i++) {
				// std input
				while ((linea = flujosEntrada.elementAt(i).readLine()) != null) {
					System.out.println(linea);
				}
				flujosEntrada.elementAt(i).close();

				// stderr

				while ((linea = flujosErrores.elementAt(i).readLine()) != null) {
					System.out.println(linea);
				}
				flujosErrores.elementAt(i).close();

				// dormir
				Thread.sleep(10000);

				// destruir

				procesos.elementAt(i).destroy();
				
				System.out.println("Nombre del Comando " + args[i] + " Salida"+ procesos.elementAt(i).exitValue());
				
				// esperar
				procesos.elementAt(i).waitFor();
			}

		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
