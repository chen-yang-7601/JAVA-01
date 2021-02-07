package com.yhh.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTask11 implements Runnable, ResultQuery<Integer> {
	private CountDownLatch latch ;
	private int sum;
	public CountDownLatchTask11(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public Integer get() {
		return sum;
	}
	
	
	@Override
	public void run() {
		try {
			//TimeUnit.MILLISECONDS.sleep(50);
			sum = new Fibo(36).getSum();
			this.latch.countDown();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		int num = 5;
		int result = 0;
		long start = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(num);
		List<CompletableFuture> list = new ArrayList<>(num);
		CountDownLatchTask11 task = new CountDownLatchTask11(latch);
		for (int i=0; i< num; i++) {
			CompletableFuture<Void> future = CompletableFuture.runAsync(task);
			list.add(future);
		}
		latch.await();

		result = task.get();
		
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
