## 练习2(必做)说明

   要求：思考有多少种方式，在main函数启动一个新线程（或线程池），运行一个方法，拿到这个方法的返回值后，退出主线程？

   写出实现方法，越多越好。

## 设计说明

因为Thread类的run方法，及Runnable接口， 都是void返回类型，为了通过某个线程的运行获得返回值，定义了一个返回的接口ResultQuery。 WaitThread等直接使用Thread， Runnable的类都通过实现这个接口，来获得返回值。而Future及FutureTask等类，因为有返回值方法，就不用实现此接口。

```
public interface ResultQuery<T> {
	
	public T get();

}
```

## 实现的类说明

#### FutureTask01

用 FutureTask实现上述功能

#### ExecutorServiceTask02

   用Java 线程池实现上述功能。

### CallableTask03

 用Callable接口及ExecutorService 和Future的特性。

#### CompletableFutureTask04

#### CompletableFutureTask05

  CompletableFuture的不同调用方法

#### ThreadParkTask06

  用LockSupport 的park ，暂停当前线程的功能。

#### ThreadInterruptTask07

使用了Thread的Interrupt（）使其中断阻塞状态。 

#### ThreadJoinTask08

启动两个线程，应用Thread的join方法。

#### LockTask09

使用了ReentrantLock 的Lock及Unlock特性。

#### VolatileFlagTask10

自定义volatile属性，控制线程的中断。

#### CountDownLatchTask11

 用JAVA并发工具包的CountDownLatch 来实现线程控制。

