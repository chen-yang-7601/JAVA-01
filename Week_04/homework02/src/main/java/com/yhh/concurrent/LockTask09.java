package com.yhh.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class LockTask09 implements ResultQuery<Integer>{
	final ReentrantLock lock = new ReentrantLock();
	Fibo fibo ;
	
	Integer result;
	
	public LockTask09(int num) {
		fibo = new Fibo(num);
	}
	
	@Override
	public Integer get() {
        return result;
	}

	public void getLockAndCalculate() {
        lock.lock();
        this.result = (Integer)new Fibo(36).getSum();
        lock.unlock();
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		final LockTask09 task = new LockTask09(36);
		int result = 0;
		long start =  System.currentTimeMillis();
		
		new Thread() {
			public void run() {
				task.getLockAndCalculate();
			}
		}.start();
		
		Thread.currentThread().sleep(100);
		result = task.get();
		
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
		 
	}

}
