package BufferCircular;

import java.util.*;

public class Buffer  {

	public  int[] array;
	
	private int g=0;
	private int s=0;
	private int n ;

	public Buffer(int n) {
		this.n=n;
	 array = new int [n];
	}
	public Buffer (){
		this(10);
	}

	
	public int getG() {
		return g;
	}


	public void setG(int g) {
		this.g = g;
	}


	public int getS() {
		return s;
	}


	public void setS(int s) {
		this.s = s;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}

	
	public void clear(){
		 g=0;
		 s=0;
		 n=0;
		
	}
	
	public synchronized void  set (int valor){
		if (! complete()){
		array[s]=valor;
		s++;
		if (s == array.length)
			s=0;
		n++;
		notifyAll();
		}else{
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	
	public synchronized int  get(){
		for(;;){
		if (! empty()){
	int aux=array[g]; 
			g++;
			if (g == array.length)
				g=0;
			n--;
			notifyAll();
			return aux;
		}
		else {
			
			try {
				wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	}
	public  boolean empty(){
		return n==0;
		
	}
	
	
	
	public boolean complete(){
		return n==array.length;
				
	}
	
	

	public static void main(String[] args) {
		Buffer b = new Buffer();
		Thread p=new Thread(new Productor(b));
		Thread c=new Thread(new Consumidor(b));
		p.start();
		c.start();
		

	}

}
