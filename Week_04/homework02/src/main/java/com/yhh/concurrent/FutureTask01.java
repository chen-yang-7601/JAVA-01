package com.yhh.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
public class FutureTask01 {

	public static void main(String[] args) throws Exception {

		int result =0;
		long start=System.currentTimeMillis();
		
		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {	
		@Override
		public Integer call() throws Exception {
			return (Integer)new Fibo(36).getSum();
		}
		});
		new Thread(task).start();
		result = task.get();
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	}

}
