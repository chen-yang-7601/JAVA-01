package com.yhh.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import java.util.concurrent.TimeUnit;
public class CallableTask03 implements Callable<Integer> {
	
	@Override
	public Integer call() throws Exception{
		Integer result = new Fibo(36).getSum();
		TimeUnit.MILLISECONDS.sleep(100);
		return result;
	}
	
	public static void main(String[] args) throws Exception {

		long start=System.currentTimeMillis();
		
		Callable<Integer> callableTask12 = new CallableTask03();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<Integer> future1 = executorService.submit(callableTask12);
		executorService.shutdown();
		Integer result = future1.get(1, TimeUnit.SECONDS);
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
