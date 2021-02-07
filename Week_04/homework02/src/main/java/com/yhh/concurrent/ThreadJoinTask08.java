package com.yhh.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class ThreadJoinTask08 {
	
	
	public static void main(String[] args) throws Exception{
		int result =0;
		long start=System.currentTimeMillis();

		ThreadforJoin t1 = new ThreadforJoin("t1",36);
		ThreadforJoin t2 = new ThreadforJoin("t2",36);
		
		t1.start();
		t2.start();
		t1.join();
		result = t1.get();
		t2.join(200);
		
		System.out.println("异步计算结果为："+result);
		System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
		
	}

}
