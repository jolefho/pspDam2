package BufferCircular;

public class Consumidor implements Runnable{

	
	private Buffer buffer;

	public Consumidor(Buffer buffer) {
		
		this.buffer = buffer;

	}

	
	public void run() {
		for(;;){
		buffer.get();
		System.out.println(buffer.get());
		}
	}
}
