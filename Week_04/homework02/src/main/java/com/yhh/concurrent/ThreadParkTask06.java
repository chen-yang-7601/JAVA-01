package com.yhh.concurrent;

import java.util.concurrent.locks.LockSupport;
public class ThreadParkTask06 implements ResultQuery<Integer>{

	public static Fibo fibo = new Fibo(36);
	
	private static Integer sum;
	static ParkThread t1 = new ParkThread("t1");
	static ParkThread t2 = new ParkThread("t2");
	
	@Override
	public Integer get() {
		return sum;
	}
	
	public static class ParkThread extends Thread{
		
		public ParkThread(String name) {
			super(name);
		}
		@Override
		public void run() {
			synchronized (fibo) {
				LockSupport.park();
				sum = fibo.getSum();
				if (Thread.currentThread().isInterrupted()) {
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		int result = 0;
		
		ThreadParkTask06 byPark = new ThreadParkTask06();
		long start = System.currentTimeMillis();
		t1.start();
		Thread.sleep(50);
		t2.start();
		Thread.sleep(50);
		
		t1.interrupt();
		LockSupport.unpark(t2);
		t1.join();
		result = byPark.get();
		
		t2.join();
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
