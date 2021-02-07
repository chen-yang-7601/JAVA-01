package com.yhh.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadforJoin extends Thread implements ResultQuery<Integer>{
	
	private Fibo fibo ;
	private Integer sum;
	
	public ThreadforJoin (String name, int num) {
		super(name);
		fibo = new Fibo(num);
	}
	
	@Override
	public Integer get(){
		return this.sum;
	}
	
	@Override
	public void run() {
		sum = (Integer)this.fibo.getSum();
	}

}
