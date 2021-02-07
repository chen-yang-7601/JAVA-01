package com.yhh.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileFlagTask10 extends Thread implements ResultQuery<Integer> {
	
	private int sum;
	private int num;
	private volatile boolean running = true;
	
	public VolatileFlagTask10(String name, int num) {
		super(name);
		this.num = num;
	}
	
	@Override
	public Integer get() {
		return sum;
	}
	
	public void terminal() {
		running = false;
		this.interrupt();
	}
	
	@Override
	public void run()  {
		while (running && !isInterrupted()) {
			sum = new Fibo(num).getSum();
			this.isInterrupted();
		}
	}
	
	public static void main(String[] args) throws Exception  {
		int result = 0;
		long start = System.currentTimeMillis();
		
		VolatileFlagTask10 t1 = new VolatileFlagTask10("byFlag", 36);
		t1.start();
		
		TimeUnit.MILLISECONDS.sleep(100);
		
		result = t1.get();
		t1.terminal();
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
