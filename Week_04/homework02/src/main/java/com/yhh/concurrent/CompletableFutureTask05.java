package com.yhh.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class CompletableFutureTask05 {

	public static void main(String[] args) throws Exception {

		int result =0;
		long start=System.currentTimeMillis();
		
		CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
		//提交异步任务
		ExecutorService executeService = Executors.newCachedThreadPool();
		executeService.submit(()->{
			completableFuture.complete((Integer)new Fibo(36).getSum());
		});
		executeService.shutdown();
		result =  completableFuture.get();
		
		System.out.println("异步计算结果为："+ result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
