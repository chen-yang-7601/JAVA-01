package com.yhh.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTask04 {

	public static void main(String[] args) {
		int result =0;
		long start=System.currentTimeMillis();
		
		result = CompletableFuture.supplyAsync(()->{ 
			return new Fibo(36).getSum();
				}
		).join();

		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
