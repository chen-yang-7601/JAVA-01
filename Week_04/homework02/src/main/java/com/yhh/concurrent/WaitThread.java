package com.yhh.concurrent;

public class WaitThread extends Thread implements ResultQuery<Integer>{
	private int sum;
	private int num;
	private String name;
	private boolean running = true;
	public WaitThread(String name, int num){
		super(name);
		this.num = num;
	}
	
	@Override 
	public Integer get() {
		return sum;
	}
	
	public void terminal() {
		running = false;
	}
	
	@Override
	public void run()  {
		while (!isInterrupted()) {
			sum = new Fibo(num).getSum();
			this.isInterrupted();
		}
	}

}
