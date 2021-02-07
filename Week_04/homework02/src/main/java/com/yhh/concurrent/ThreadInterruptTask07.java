package com.yhh.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptTask07 {

	public static void main(String[] args) throws Exception{
		
		int result = 0;
		long start =  System.currentTimeMillis();
		
		WaitThread thread = new WaitThread("name", 36);
			
		thread.start();
		
		TimeUnit.MILLISECONDS.sleep(100); 
		//Thread.currentThread().sleep(100);
		thread.interrupt();
		
		result = thread.get();
		
		System.out.println("异步计算结果为："+ result);
		
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

	}

}
