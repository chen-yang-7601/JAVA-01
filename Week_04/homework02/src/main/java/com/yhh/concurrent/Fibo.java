package com.yhh.concurrent;

public class Fibo {
	
	private int num;

	public Fibo(int num) {
		this.num = num;
	}
	
	public int getSum() {
		return fibo(num);
	}
	
	private int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);		
	}
	
	public static void main(String[] args) {
		 Fibo fibo = new Fibo(36);
		 System.out.println("Fibo=" + fibo.getSum());
		

	}

}
