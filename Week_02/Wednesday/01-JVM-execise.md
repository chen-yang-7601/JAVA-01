#前言：
  ##1. 测试程序说明：
     通过对一个java程序GCLogAnalysis，分析其在不同的GC策略下的运行情况。这个程序在设定的时间内（默认为一秒），随机地创建100K以内的(byte/int/double类型）的随机对象，执行效率以时间范围内生成的对象数量来衡量。
  ##2. 环境说明：
     - 机器1： 16G内存，i7-1165G7cpu, WIN10, jdk1.8.0_221-b11,

#各个GC运行结果
   
  ##SerialGc
  先以指定堆内存为2G为例：
  
   > `java -classpath "D:\tech\geek;" -Xloggc:gc.Serial2048.log -Xmx2g -Xms2g -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

   ###GC的日志如下：
     (```)
Memory: 4k page, physical 16493352k(10858720k free), swap 19508008k(12875068k free)
CommandLine flags: -XX:InitialHeapSize=2147483648 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
2021-01-22T15:25:13.044+0800: 0.207: [GC (Allocation Failure) 2021-01-22T15:25:13.044+0800: 0.207: [DefNew: 559232K->69888K(629120K), 0.0463966 secs] 559232K->158975K(2027264K), 0.0465043 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2021-01-22T15:25:13.139+0800: 0.302: [GC (Allocation Failure) 2021-01-22T15:25:13.139+0800: 0.302: [DefNew: 629120K->69887K(629120K), 0.0593279 secs] 718207K->293129K(2027264K), 0.0593735 secs] [Times: user=0.02 sys=0.05, real=0.06 secs] 
2021-01-22T15:25:13.241+0800: 0.403: [GC (Allocation Failure) 2021-01-22T15:25:13.241+0800: 0.403: [DefNew: 629119K->69886K(629120K), 0.0471327 secs] 852361K->422376K(2027264K), 0.0471793 secs] [Times: user=0.03 sys=0.01, real=0.05 secs] 
2021-01-22T15:25:13.331+0800: 0.493: [GC (Allocation Failure) 2021-01-22T15:25:13.331+0800: 0.493: [DefNew: 629118K->69888K(629120K), 0.0488292 secs] 981608K->550718K(2027264K), 0.0488744 secs] [Times: user=0.01 sys=0.03, real=0.05 secs] 
2021-01-22T15:25:13.421+0800: 0.583: [GC (Allocation Failure) 2021-01-22T15:25:13.421+0800: 0.583: [DefNew: 629120K->69887K(629120K), 0.0481067 secs] 1109950K->682781K(2027264K), 0.0481529 secs] [Times: user=0.02 sys=0.05, real=0.05 secs] 
2021-01-22T15:25:13.516+0800: 0.678: [GC (Allocation Failure) 2021-01-22T15:25:13.516+0800: 0.678: [DefNew: 629119K->69888K(629120K), 0.0449226 secs] 1242013K->815946K(2027264K), 0.0449751 secs] [Times: user=0.03 sys=0.00, real=0.05 secs] 
2021-01-22T15:25:13.604+0800: 0.766: [GC (Allocation Failure) 2021-01-22T15:25:13.604+0800: 0.767: [DefNew: 629120K->69887K(629120K), 0.0450014 secs] 1375178K->948906K(2027264K), 0.0450495 secs] [Times: user=0.03 sys=0.02, real=0.04 secs] 
2021-01-22T15:25:13.693+0800: 0.856: [GC (Allocation Failure) 2021-01-22T15:25:13.693+0800: 0.856: [DefNew: 629119K->69887K(629120K), 0.0416915 secs] 1508138K->1067454K(2027264K), 0.0417355 secs] [Times: user=0.03 sys=0.02, real=0.04 secs] 
2021-01-22T15:25:13.778+0800: 0.941: [GC (Allocation Failure) 2021-01-22T15:25:13.778+0800: 0.941: [DefNew: 629119K->69887K(629120K), 0.0458094 secs] 1626686K->1199238K(2027264K), 0.0458561 secs] [Times: user=0.03 sys=0.01, real=0.05 secs] 
2021-01-22T15:25:13.867+0800: 1.029: [GC (Allocation Failure) 2021-01-22T15:25:13.867+0800: 1.029: [DefNew: 629119K->69887K(629120K), 0.0454568 secs] 1758470K->1327989K(2027264K), 0.0454995 secs] [Times: user=0.02 sys=0.03, real=0.04 secs] 
Heap
 def new generation   total 629120K, used 92903K [0x0000000080000000, 0x00000000aaaa0000, 0x00000000aaaa0000)
  eden space 559232K,   4% used [0x0000000080000000, 0x0000000081679cf8, 0x00000000a2220000)
  from space 69888K,  99% used [0x00000000a2220000, 0x00000000a665fff0, 0x00000000a6660000)
  to   space 69888K,   0% used [0x00000000a6660000, 0x00000000a6660000, 0x00000000aaaa0000)
 tenured generation   total 1398144K, used 1258101K [0x00000000aaaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 1398144K,  89% used [0x00000000aaaa0000, 0x00000000f773d490, 0x00000000f773d600, 0x0000000100000000)
 Metaspace       used 2694K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
   (```) 

   ###从上面的GC日志信息可以看到，在1秒中内，没有发生fullGC,发生了10次young GC，每次GC约为50ms，10次GC串行化执行大约为500ms，也就是说，在这个案例中，2G堆内存下，SerialGC中，JVM有50%的时间在做GC，只有50%的时间在执行程序，执行结果为20950次。

   ###若改为用1G的堆内存设定，GC日志如下
    (```)
ommandLine flags: -XX:InitialHeapSize=1073741824 -XX:MaxHeapSize=1073741824 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
2021-01-22T15:31:07.090+0800: 0.129: [GC (Allocation Failure) 2021-01-22T15:31:07.090+0800: 0.130: [DefNew: 279616K->34943K(314560K), 0.0254748 secs] 279616K->87470K(1013632K), 0.0255624 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-01-22T15:31:07.140+0800: 0.180: [GC (Allocation Failure) 2021-01-22T15:31:07.140+0800: 0.181: [DefNew: 314559K->34943K(314560K), 0.0335359 secs] 367086K->166520K(1013632K), 0.0335748 secs] [Times: user=0.02 sys=0.01, real=0.03 secs] 
2021-01-22T15:31:07.199+0800: 0.239: [GC (Allocation Failure) 2021-01-22T15:31:07.199+0800: 0.239: [DefNew: 314559K->34944K(314560K), 0.0287446 secs] 446136K->245715K(1013632K), 0.0287863 secs] [Times: user=0.00 sys=0.03, real=0.03 secs] 
2021-01-22T15:31:07.251+0800: 0.291: [GC (Allocation Failure) 2021-01-22T15:31:07.251+0800: 0.291: [DefNew: 314560K->34943K(314560K), 0.0280479 secs] 525331K->326545K(1013632K), 0.0280902 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 
2021-01-22T15:31:07.301+0800: 0.341: [GC (Allocation Failure) 2021-01-22T15:31:07.301+0800: 0.341: [DefNew: 314559K->34943K(314560K), 0.0269429 secs] 606161K->402762K(1013632K), 0.0269839 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-01-22T15:31:07.349+0800: 0.390: [GC (Allocation Failure) 2021-01-22T15:31:07.349+0800: 0.390: [DefNew: 314559K->34943K(314560K), 0.0285788 secs] 682378K->486079K(1013632K), 0.0286250 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-01-22T15:31:07.400+0800: 0.440: [GC (Allocation Failure) 2021-01-22T15:31:07.400+0800: 0.440: [DefNew: 314559K->34944K(314560K), 0.0287792 secs] 765695K->567048K(1013632K), 0.0288195 secs] [Times: user=0.00 sys=0.03, real=0.03 secs] 
2021-01-22T15:31:07.451+0800: 0.491: [GC (Allocation Failure) 2021-01-22T15:31:07.451+0800: 0.491: [DefNew: 314071K->34943K(314560K), 0.0294957 secs] 846175K->650671K(1013632K), 0.0295359 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-01-22T15:31:07.501+0800: 0.542: [GC (Allocation Failure) 2021-01-22T15:31:07.501+0800: 0.542: [DefNew: 314559K->314559K(314560K), 0.0000070 secs]2021-01-22T15:31:07.501+0800: 0.542: [Tenured: 615727K->376797K(699072K), 0.0495241 secs] 930287K->376797K(1013632K), [Metaspace: 2688K->2688K(1056768K)], 0.0495832 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2021-01-22T15:31:07.572+0800: 0.612: [GC (Allocation Failure) 2021-01-22T15:31:07.572+0800: 0.613: [DefNew: 279616K->34943K(314560K), 0.0120868 secs] 656413K->462127K(1013632K), 0.0121271 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-01-22T15:31:07.607+0800: 0.647: [GC (Allocation Failure) 2021-01-22T15:31:07.607+0800: 0.647: [DefNew: 314559K->34943K(314560K), 0.0152478 secs] 741743K->534404K(1013632K), 0.0152884 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-01-22T15:31:07.644+0800: 0.684: [GC (Allocation Failure) 2021-01-22T15:31:07.644+0800: 0.684: [DefNew: 314559K->34943K(314560K), 0.0179403 secs] 814020K->621435K(1013632K), 0.0179806 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2021-01-22T15:31:07.684+0800: 0.724: [GC (Allocation Failure) 2021-01-22T15:31:07.684+0800: 0.724: [DefNew: 314559K->34943K(314560K), 0.0232914 secs] 901051K->699754K(1013632K), 0.0233316 secs] [Times: user=0.01 sys=0.02, real=0.02 secs] 
2021-01-22T15:31:07.729+0800: 0.770: [GC (Allocation Failure) 2021-01-22T15:31:07.729+0800: 0.770: [DefNew: 314559K->314559K(314560K), 0.0000082 secs]2021-01-22T15:31:07.729+0800: 0.770: [Tenured: 664810K->394763K(699072K), 0.0497698 secs] 979370K->394763K(1013632K), [Metaspace: 2688K->2688K(1056768K)], 0.0498285 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2021-01-22T15:31:07.802+0800: 0.842: [GC (Allocation Failure) 2021-01-22T15:31:07.802+0800: 0.842: [DefNew: 279616K->34944K(314560K), 0.0123994 secs] 674379K->485730K(1013632K), 0.0124430 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-01-22T15:31:07.836+0800: 0.876: [GC (Allocation Failure) 2021-01-22T15:31:07.836+0800: 0.876: [DefNew: 314560K->34942K(314560K), 0.0152580 secs] 765346K->562290K(1013632K), 0.0152974 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2021-01-22T15:31:07.873+0800: 0.913: [GC (Allocation Failure) 2021-01-22T15:31:07.873+0800: 0.913: [DefNew: 314558K->34943K(314560K), 0.0150703 secs] 841906K->638484K(1013632K), 0.0151100 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2021-01-22T15:31:07.909+0800: 0.949: [GC (Allocation Failure) 2021-01-22T15:31:07.909+0800: 0.949: [DefNew: 314559K->34943K(314560K), 0.0169173 secs] 918100K->712535K(1013632K), 0.0169549 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2021-01-22T15:31:07.947+0800: 0.987: [GC (Allocation Failure) 2021-01-22T15:31:07.947+0800: 0.987: [DefNew: 314559K->314559K(314560K), 0.0000069 secs]2021-01-22T15:31:07.947+0800: 0.987: [Tenured: 677592K->409258K(699072K), 0.0524603 secs] 992151K->409258K(1013632K), [Metaspace: 2688K->2688K(1056768K)], 0.0525132 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
    (```)

   在1秒内，发生了15次young GC和3次full GC, 其中full GC每次持续约50ms, YoungGC每次大概20ms以内，即因为堆内存小，所以每次执行YoungGC的效率较高，所以整体GC时间约在450ms, JVM的效率略高于2G时。 执行结果是20383次，与2G内存结果相差不大。

   ###将堆内存设为256m时会发生OOM的错误,错误日志如下(部分)：

  (```)
CommandLine flags: -XX:InitialHeapSize=268435456 -XX:MaxHeapSize=268435456 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
2021-01-22T15:32:07.410+0800: 0.070: [GC (Allocation Failure) 2021-01-22T15:32:07.410+0800: 0.070: [DefNew: 69952K->8704K(78656K), 0.0086317 secs] 69952K->24479K(253440K), 0.0087320 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-01-22T15:32:07.430+0800: 0.090: [GC (Allocation Failure) 2021-01-22T15:32:07.430+0800: 0.090: [DefNew: 78306K->8703K(78656K), 0.0114521 secs] 94082K->54009K(253440K), 0.0114930 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
2021-01-22T15:32:07.448+0800: 0.107: [GC (Allocation Failure) 2021-01-22T15:32:07.448+0800: 0.107: [DefNew: 78634K->8699K(78656K), 0.0085503 secs] 123940K->77370K(253440K), 0.0085833 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2021-01-22T15:32:07.463+0800: 0.122: [GC (Allocation Failure) 2021-01-22T15:32:07.463+0800: 0.122: [DefNew: 78651K->8703K(78656K), 0.0084000 secs] 147322K->100406K(253440K), 0.0084306 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-01-22T15:32:07.477+0800: 0.136: [GC (Allocation Failure) 2021-01-22T15:32:07.477+0800: 0.136: [DefNew: 78187K->8699K(78656K), 0.0083481 secs] 169890K->125047K(253440K), 0.0083787 secs] [Times: user=0.00 sys=0.02, real=0.01 secs] 
2021-01-22T15:32:07.491+0800: 0.151: [GC (Allocation Failure) 2021-01-22T15:32:07.491+0800: 0.151: [DefNew: 78651K->8703K(78656K), 0.0090492 secs] 194999K->152129K(253440K), 0.0090785 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-01-22T15:32:07.506+0800: 0.166: [GC (Allocation Failure) 2021-01-22T15:32:07.506+0800: 0.166: [DefNew: 78585K->8702K(78656K), 0.0081570 secs] 222011K->175998K(253440K), 0.0081853 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-01-22T15:32:07.521+0800: 0.181: [GC (Allocation Failure) 2021-01-22T15:32:07.521+0800: 0.181: [DefNew: 77997K->77997K(78656K), 0.0000062 secs]2021-01-22T15:32:07.521+0800: 0.182: [Tenured: 167295K->163984K(174784K), 0.0216115 secs] 245293K->163984K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0216654 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2021-01-22T15:32:07.550+0800: 0.209: [GC (Allocation Failure) 2021-01-22T15:32:07.550+0800: 0.209: [DefNew: 69839K->69839K(78656K), 0.0000073 secs]2021-01-22T15:32:07.550+0800: 0.210: [Tenured: 163984K->174430K(174784K), 0.0233895 secs] 233823K->179084K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0234413 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2021-01-22T15:32:07.581+0800: 0.241: [Full GC (Allocation Failure) 2021-01-22T15:32:07.581+0800: 0.241: [Tenured: 174700K->174684K(174784K), 0.0256494 secs] 253323K->190833K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0256867 secs] [Times: user=0.01 sys=0.00, real=0.03 secs] 
2021-01-22T15:32:07.614+0800: 0.274: [Full GC (Allocation Failure) 2021-01-22T15:32:07.614+0800: 0.274: [Tenured: 174684K->174747K(174784K), 0.0276405 secs] 253147K->193657K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0276815 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-01-22T15:32:07.649+0800: 0.309: [Full GC (Allocation Failure) 2021-01-22T15:32:07.649+0800: 0.309: [Tenured: 174747K->174747K(174784K), 0.0064026 secs] 253310K->214757K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0064359 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
......
2021-01-22T15:32:08.053+0800: 0.712: [Full GC (Allocation Failure) 2021-01-22T15:32:08.053+0800: 0.712: [Tenured: 174656K->174656K(174784K), 0.0011509 secs] 253029K->252654K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0011650 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-01-22T15:32:08.054+0800: 0.714: [Full GC (Allocation Failure) 2021-01-22T15:32:08.054+0800: 0.714: [Tenured: 174656K->174366K(174784K), 0.0228002 secs] 252654K->252364K(253440K), [Metaspace: 2688K->2688K(1056768K)], 0.0228175 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
Heap
 def new generation   total 78656K, used 78373K [0x00000000f0000000, 0x00000000f5550000, 0x00000000f5550000)
  eden space 69952K, 100% used [0x00000000f0000000, 0x00000000f4450000, 0x00000000f4450000)
  from space 8704K,  96% used [0x00000000f4cd0000, 0x00000000f5509578, 0x00000000f5550000)
  to   space 8704K,   0% used [0x00000000f4450000, 0x00000000f4450000, 0x00000000f4cd0000)
 tenured generation   total 174784K, used 174366K [0x00000000f5550000, 0x0000000100000000, 0x0000000100000000)
   the space 174784K,  99% used [0x00000000f5550000, 0x00000000fff97a80, 0x00000000fff97c00, 0x0000000100000000)
 Metaspace       used 2719K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 292K, capacity 386K, committed 512K, reserved 1048576K  
  (```)


   ###此次执行中，先发生了9次YoungGc, 由于堆内存较小，每次YoungGC执行时间很短，每次都在10ms以内。但后面就频繁发生fullGC, Old区不断增大，直至内存溢出。

  ##ParNewGC
   ###执行指令：
   >`java -classpath "D:\tech\geek;" -Xloggc:parNew2048.log -Xmx2g -Xms2g -XX:+UseParNewGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

   ###GC日志为：

    (```)
CommandLine flags: -XX:InitialHeapSize=2147483648 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC 
2021-01-22T15:42:24.230+0800: 0.210: [GC (Allocation Failure) 2021-01-22T15:42:24.230+0800: 0.210: [ParNew: 559232K->69888K(629120K), 0.0198765 secs] 559232K->155863K(2027264K), 0.0199627 secs] [Times: user=0.01 sys=0.11, real=0.02 secs] 
2021-01-22T15:42:24.296+0800: 0.276: [GC (Allocation Failure) 2021-01-22T15:42:24.296+0800: 0.276: [ParNew: 629120K->69888K(629120K), 0.0234670 secs] 715095K->286846K(2027264K), 0.0235124 secs] [Times: user=0.17 sys=0.08, real=0.02 secs] 
2021-01-22T15:42:24.363+0800: 0.342: [GC (Allocation Failure) 2021-01-22T15:42:24.363+0800: 0.342: [ParNew: 629120K->69888K(629120K), 0.0546917 secs] 846078K->412383K(2027264K), 0.0547586 secs] [Times: user=0.36 sys=0.01, real=0.06 secs] 
2021-01-22T15:42:24.462+0800: 0.441: [GC (Allocation Failure) 2021-01-22T15:42:24.462+0800: 0.441: [ParNew: 629120K->69888K(629120K), 0.0528835 secs] 971615K->536056K(2027264K), 0.0529310 secs] [Times: user=0.38 sys=0.00, real=0.05 secs] 
2021-01-22T15:42:24.558+0800: 0.537: [GC (Allocation Failure) 2021-01-22T15:42:24.558+0800: 0.537: [ParNew: 629120K->69888K(629120K), 0.0562833 secs] 1095288K->662593K(2027264K), 0.0563306 secs] [Times: user=0.48 sys=0.02, real=0.06 secs] 
2021-01-22T15:42:24.661+0800: 0.640: [GC (Allocation Failure) 2021-01-22T15:42:24.661+0800: 0.640: [ParNew: 629120K->69886K(629120K), 0.0545919 secs] 1221825K->790622K(2027264K), 0.0546371 secs] [Times: user=0.38 sys=0.00, real=0.05 secs] 
2021-01-22T15:42:24.760+0800: 0.739: [GC (Allocation Failure) 2021-01-22T15:42:24.760+0800: 0.739: [ParNew: 629118K->69887K(629120K), 0.0528308 secs] 1349854K->918526K(2027264K), 0.0528814 secs] [Times: user=0.34 sys=0.03, real=0.05 secs] 
2021-01-22T15:42:24.858+0800: 0.838: [GC (Allocation Failure) 2021-01-22T15:42:24.858+0800: 0.838: [ParNew: 629119K->69888K(629120K), 0.0530347 secs] 1477758K->1044351K(2027264K), 0.0530878 secs] [Times: user=0.42 sys=0.05, real=0.05 secs] 
2021-01-22T15:42:24.957+0800: 0.936: [GC (Allocation Failure) 2021-01-22T15:42:24.957+0800: 0.936: [ParNew: 629120K->69886K(629120K), 0.0507959 secs] 1603583K->1169826K(2027264K), 0.0508417 secs] [Times: user=0.34 sys=0.03, real=0.05 secs] 
2021-01-22T15:42:25.055+0800: 1.035: [GC (Allocation Failure) 2021-01-22T15:42:25.056+0800: 1.035: [ParNew: 629118K->69888K(629120K), 0.0552077 secs] 1729058K->1305124K(2027264K), 0.0552616 secs] [Times: user=0.44 sys=0.03, real=0.06 secs] 
Heap
 par new generation   total 629120K, used 92857K [0x0000000080000000, 0x00000000aaaa0000, 0x00000000aaaa0000)
  eden space 559232K,   4% used [0x0000000080000000, 0x000000008166e6f8, 0x00000000a2220000)
  from space 69888K, 100% used [0x00000000a2220000, 0x00000000a6660000, 0x00000000a6660000)
  to   space 69888K,   0% used [0x00000000a6660000, 0x00000000a6660000, 0x00000000aaaa0000)
 tenured generation   total 1398144K, used 1235236K [0x00000000aaaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 1398144K,  88% used [0x00000000aaaa0000, 0x00000000f60e9138, 0x00000000f60e9200, 0x0000000100000000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
    (```)

   本次执行中发生了10次youngGC,每次大约在50ms左右，10次大概在500ms，因为并行的原因，执行效率要高于SerialGC, 为21093次。 

  ##ParallelGC
   ###执行指令：
   > `java -classpath "D:\tech\geek;" -Xloggc:gc.Parallel2048.log -Xmx2g -Xms2g -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`
   
   ###GC LOG信息如下:
   (```)
CommandLine flags: -XX:InitialHeapSize=2147483648 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
2021-01-22T15:09:40.246+0800: 0.197: [GC (Allocation Failure) [PSYoungGen: 524800K->87032K(611840K)] 524800K->157392K(2010112K), 0.0179597 secs] [Times: user=0.05 sys=0.08, real=0.02 secs] 
2021-01-22T15:09:40.309+0800: 0.260: [GC (Allocation Failure) [PSYoungGen: 611832K->87039K(611840K)] 682192K->268104K(2010112K), 0.0221851 secs] [Times: user=0.08 sys=0.17, real=0.02 secs] 
2021-01-22T15:09:40.372+0800: 0.323: [GC (Allocation Failure) [PSYoungGen: 611839K->87039K(611840K)] 792904K->381949K(2010112K), 0.0199354 secs] [Times: user=0.14 sys=0.11, real=0.02 secs] 
2021-01-22T15:09:40.434+0800: 0.386: [GC (Allocation Failure) [PSYoungGen: 611839K->87024K(611840K)] 906749K->492202K(2010112K), 0.0194391 secs] [Times: user=0.16 sys=0.09, real=0.02 secs] 
2021-01-22T15:09:40.495+0800: 0.445: [GC (Allocation Failure) [PSYoungGen: 611824K->87039K(611840K)] 1017002K->610582K(2010112K), 0.0201916 secs] [Times: user=0.13 sys=0.00, real=0.02 secs] 
2021-01-22T15:09:40.557+0800: 0.508: [GC (Allocation Failure) [PSYoungGen: 611839K->87031K(333312K)] 1135382K->724256K(1731584K), 0.0202216 secs] [Times: user=0.13 sys=0.00, real=0.02 secs] 
2021-01-22T15:09:40.596+0800: 0.547: [GC (Allocation Failure) [PSYoungGen: 333303K->142634K(389120K)] 970528K->785355K(1787392K), 0.0126750 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-01-22T15:09:40.628+0800: 0.579: [GC (Allocation Failure) [PSYoungGen: 388906K->178006K(470016K)] 1031627K->834611K(1868288K), 0.0162753 secs] [Times: user=0.13 sys=0.00, real=0.02 secs] 
2021-01-22T15:09:40.663+0800: 0.614: [GC (Allocation Failure) [PSYoungGen: 415062K->182905K(465920K)] 1071667K->872760K(1864192K), 0.0181497 secs] [Times: user=0.13 sys=0.00, real=0.02 secs] 
2021-01-22T15:09:40.701+0800: 0.652: [GC (Allocation Failure) [PSYoungGen: 419961K->131113K(463872K)] 1109816K->911983K(1862144K), 0.0197113 secs] [Times: user=0.20 sys=0.05, real=0.02 secs] 
2021-01-22T15:09:40.740+0800: 0.691: [GC (Allocation Failure) [PSYoungGen: 370217K->71448K(310784K)] 1151087K->959639K(1709056K), 0.0167302 secs] [Times: user=0.03 sys=0.09, real=0.02 secs] 
2021-01-22T15:09:40.776+0800: 0.727: [GC (Allocation Failure) [PSYoungGen: 310552K->75035K(465920K)] 1198743K->1021973K(1864192K), 0.0123888 secs] [Times: user=0.11 sys=0.01, real=0.01 secs] 
2021-01-22T15:09:40.807+0800: 0.758: [GC (Allocation Failure) [PSYoungGen: 307995K->75373K(465920K)] 1254933K->1085862K(1864192K), 0.0127559 secs] [Times: user=0.05 sys=0.08, real=0.01 secs] 
2021-01-22T15:09:40.836+0800: 0.788: [GC (Allocation Failure) [PSYoungGen: 308333K->78652K(465920K)] 1318822K->1149275K(1864192K), 0.0126621 secs] [Times: user=0.11 sys=0.01, real=0.01 secs] 
2021-01-22T15:09:40.866+0800: 0.818: [GC (Allocation Failure) [PSYoungGen: 311612K->80365K(465920K)] 1382235K->1213064K(1864192K), 0.0130683 secs] [Times: user=0.08 sys=0.05, real=0.01 secs] 
2021-01-22T15:09:40.898+0800: 0.849: [GC (Allocation Failure) [PSYoungGen: 313325K->71431K(465920K)] 1446024K->1267366K(1864192K), 0.0124750 secs] [Times: user=0.08 sys=0.05, real=0.01 secs] 
2021-01-22T15:09:40.928+0800: 0.879: [GC (Allocation Failure) [PSYoungGen: 304391K->73932K(465920K)] 1500326K->1328617K(1864192K), 0.0120256 secs] [Times: user=0.11 sys=0.02, real=0.01 secs] 
2021-01-22T15:09:40.957+0800: 0.909: [GC (Allocation Failure) [PSYoungGen: 306892K->74547K(465920K)] 1561577K->1388981K(1864192K), 0.0128010 secs] [Times: user=0.06 sys=0.06, real=0.01 secs] 
2021-01-22T15:09:40.971+0800: 0.922: [Full GC (Ergonomics) [PSYoungGen: 74547K->0K(465920K)] [ParOldGen: 1314434K->379262K(1398272K)] 1388981K->379262K(1864192K), [Metaspace: 2688K->2688K(1056768K)], 0.0409747 secs] [Times: user=0.23 sys=0.02, real=0.04 secs] 
2021-01-22T15:09:41.031+0800: 0.981: [GC (Allocation Failure) [PSYoungGen: 232960K->74790K(465920K)] 612222K->454053K(1864192K), 0.0066630 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-01-22T15:09:41.057+0800: 1.009: [GC (Allocation Failure) [PSYoungGen: 307750K->73876K(465920K)] 687013K->514164K(1864192K), 0.0114149 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-01-22T15:09:41.086+0800: 1.038: [GC (Allocation Failure) [PSYoungGen: 306836K->76343K(465920K)] 747124K->575360K(1864192K), 0.0113703 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 465920K, used 85844K [0x00000000d5580000, 0x0000000100000000, 0x0000000100000000)
  eden space 232960K, 4% used [0x00000000d5580000,0x00000000d5ec74f0,0x00000000e3900000)
  from space 232960K, 32% used [0x00000000e3900000,0x00000000e838dc68,0x00000000f1c80000)
  to   space 231936K, 0% used [0x00000000f1d80000,0x00000000f1d80000,0x0000000100000000)
 ParOldGen       total 1398272K, used 499017K [0x0000000080000000, 0x00000000d5580000, 0x00000000d5580000)
  object space 1398272K, 35% used [0x0000000080000000,0x000000009e7526d8,0x00000000d5580000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
   (```)

    ParallelGC在1秒内，共发生了11次youngGC 和1次full GC,其中fullGC时间为40ms, 每次youngGC时间约在15ms左右，执行结果为25305次

  ##CMS-ConcMarkSweepGC
    
    ###执行指令：
  > `java -classpath "D:\tech\geek;" -Xloggc:gc.cms2048.log -Xmx2g -Xms2g -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

    ### 2G的GC日志信息如下：
     (```)
CommandLine flags: -XX:InitialHeapSize=2147483648 -XX:MaxHeapSize=2147483648 -XX:MaxNewSize=697933824 -XX:MaxTenuringThreshold=6 -XX:NewSize=697933824 -XX:OldPLABSize=16 -XX:OldSize=1395867648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC 
2021-01-22T15:34:57.938+0800: 0.204: [GC (Allocation Failure) 2021-01-22T15:34:57.938+0800: 0.204: [ParNew: 545344K->68096K(613440K), 0.0193955 secs] 545344K->154414K(2029056K), 0.0195087 secs] [Times: user=0.03 sys=0.09, real=0.02 secs] 
2021-01-22T15:34:58.004+0800: 0.269: [GC (Allocation Failure) 2021-01-22T15:34:58.004+0800: 0.269: [ParNew: 613440K->68096K(613440K), 0.0239318 secs] 699758K->277802K(2029056K), 0.0239783 secs] [Times: user=0.00 sys=0.13, real=0.02 secs] 
2021-01-22T15:34:58.072+0800: 0.337: [GC (Allocation Failure) 2021-01-22T15:34:58.072+0800: 0.337: [ParNew: 613440K->68096K(613440K), 0.0531191 secs] 823146K->398828K(2029056K), 0.0531709 secs] [Times: user=0.47 sys=0.03, real=0.05 secs] 
2021-01-22T15:34:58.170+0800: 0.435: [GC (Allocation Failure) 2021-01-22T15:34:58.170+0800: 0.435: [ParNew: 613440K->68096K(613440K), 0.0520446 secs] 944172K->513044K(2029056K), 0.0520944 secs] [Times: user=0.48 sys=0.02, real=0.05 secs] 
2021-01-22T15:34:58.264+0800: 0.530: [GC (Allocation Failure) 2021-01-22T15:34:58.264+0800: 0.530: [ParNew: 613440K->68096K(613440K), 0.0565253 secs] 1058388K->641130K(2029056K), 0.0565725 secs] [Times: user=0.45 sys=0.01, real=0.06 secs] 
2021-01-22T15:34:58.366+0800: 0.631: [GC (Allocation Failure) 2021-01-22T15:34:58.366+0800: 0.631: [ParNew: 613440K->68095K(613440K), 0.0510597 secs] 1186474K->754793K(2029056K), 0.0511078 secs] [Times: user=0.34 sys=0.03, real=0.05 secs] 
2021-01-22T15:34:58.465+0800: 0.730: [GC (Allocation Failure) 2021-01-22T15:34:58.465+0800: 0.730: [ParNew: 613439K->68094K(613440K), 0.0552856 secs] 1300137K->884267K(2029056K), 0.0553324 secs] [Times: user=0.45 sys=0.03, real=0.06 secs] 
2021-01-22T15:34:58.520+0800: 0.786: [GC (CMS Initial Mark) [1 CMS-initial-mark: 816173K(1415616K)] 895560K(2029056K), 0.0002122 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-01-22T15:34:58.521+0800: 0.786: [CMS-concurrent-mark-start]
2021-01-22T15:34:58.524+0800: 0.789: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-01-22T15:34:58.524+0800: 0.789: [CMS-concurrent-preclean-start]
2021-01-22T15:34:58.525+0800: 0.791: [CMS-concurrent-preclean: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-01-22T15:34:58.525+0800: 0.791: [CMS-concurrent-abortable-preclean-start]
2021-01-22T15:34:58.565+0800: 0.830: [GC (Allocation Failure) 2021-01-22T15:34:58.565+0800: 0.830: [ParNew: 613438K->68095K(613440K), 0.0526028 secs] 1429611K->1007871K(2029056K), 0.0526479 secs] [Times: user=0.34 sys=0.03, real=0.05 secs] 
2021-01-22T15:34:58.663+0800: 0.928: [GC (Allocation Failure) 2021-01-22T15:34:58.663+0800: 0.928: [ParNew: 613439K->68095K(613440K), 0.0559436 secs] 1553215K->1138654K(2029056K), 0.0559916 secs] [Times: user=0.38 sys=0.00, real=0.06 secs] 
2021-01-22T15:34:58.764+0800: 1.030: [GC (Allocation Failure) 2021-01-22T15:34:58.764+0800: 1.030: [ParNew: 613439K->68094K(613440K), 0.0527366 secs] 1683998K->1264548K(2029056K), 0.0527806 secs] [Times: user=0.41 sys=0.06, real=0.05 secs] 
Heap
 par new generation   total 613440K, used 90136K [0x0000000080000000, 0x00000000a9990000, 0x00000000a9990000)
  eden space 545344K,   4% used [0x0000000080000000, 0x00000000815868c8, 0x00000000a1490000)
  from space 68096K,  99% used [0x00000000a1490000, 0x00000000a570f910, 0x00000000a5710000)
  to   space 68096K,   0% used [0x00000000a5710000, 0x00000000a5710000, 0x00000000a9990000)
 concurrent mark-sweep generation total 1415616K, used 1196454K [0x00000000a9990000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 2695K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 289K, capacity 386K, committed 512K, reserved 1048576K
   (```)

    ###CMS GC在2g内存下，发生了一次full GC和10次YoungGC,但每次youngGC的时间都约为50ms，总体GC效率不高，执行结果为21093次。 
   当改为用1g内存时，虽然full GC次数增多到4次，YoungGC有19次之多，但因为堆内存减小，YoungGC平均在20ms以内，GC效率高，执行结果为20067

  ##G1GC
  
   ###先不考虑增加其他参数，仅仅是指定堆大小，指令如下
  >`java -classpath "D:\tech\geek;" -Xloggc:gc.g1-2048.log -Xmx2g -Xms2g -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

   G1GC的日志（部分)如下：
     (```)
CommandLine flags: -XX:InitialHeapSize=2147483648 -XX:MaxGCPauseMillis=50 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation 
2021-01-22T16:04:37.684+0800: 0.114: [GC pause (G1 Evacuation Pause) (young), 0.0068420 secs]
   [Parallel Time: 5.0 ms, GC Workers: 8]
      [GC Worker Start (ms): Min: 114.1, Avg: 114.2, Max: 114.3, Diff: 0.1]
      [Ext Root Scanning (ms): Min: 0.1, Avg: 0.1, Max: 0.2, Diff: 0.1, Sum: 1.1]
      [Update RS (ms): Min: 0.3, Avg: 0.3, Max: 0.4, Diff: 0.0, Sum: 2.7]
         [Processed Buffers: Min: 6, Avg: 6.1, Max: 7, Diff: 1, Sum: 49]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 4.2, Avg: 4.2, Max: 4.3, Diff: 0.2, Sum: 33.6]
      [Termination (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.9]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 8]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.2]
      [GC Worker Total (ms): Min: 4.8, Avg: 4.8, Max: 4.9, Diff: 0.1, Sum: 38.5]
      [GC Worker End (ms): Min: 119.0, Avg: 119.0, Max: 119.0, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.8 ms]
   [Other: 1.1 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.1 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.7 ms]
      [Humongous Register: 0.0 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.0 ms]
   [Eden: 102.0M(102.0M)->0.0B(89.0M) Survivors: 0.0B->13.0M Heap: 132.6M(2048.0M)->50.3M(2048.0M)]
 [Times: user=0.02 sys=0.11, real=0.01 secs] 
2021-01-22T16:04:37.709+0800: 0.139: [GC pause (G1 Evacuation Pause) (young), 0.0074696 secs]
   [Parallel Time: 5.5 ms, GC Workers: 8]
      [GC Worker Start (ms): Min: 139.6, Avg: 139.7, Max: 139.7, Diff: 0.1]
      [Ext Root Scanning (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.1, Sum: 0.7]
      [Update RS (ms): Min: 0.0, Avg: 0.3, Max: 0.4, Diff: 0.4, Sum: 2.6]
         [Processed Buffers: Min: 0, Avg: 6.9, Max: 12, Diff: 12, Sum: 55]
      [Scan RS (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Code Root Scanning (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.0]
      [Object Copy (ms): Min: 4.2, Avg: 4.4, Max: 4.6, Diff: 0.4, Sum: 35.0]
      [Termination (ms): Min: 0.0, Avg: 0.1, Max: 0.2, Diff: 0.2, Sum: 0.8]
         [Termination Attempts: Min: 1, Avg: 1.0, Max: 1, Diff: 0, Sum: 8]
      [GC Worker Other (ms): Min: 0.0, Avg: 0.0, Max: 0.0, Diff: 0.0, Sum: 0.1]
      [GC Worker Total (ms): Min: 4.8, Avg: 4.9, Max: 5.0, Diff: 0.1, Sum: 39.2]
      [GC Worker End (ms): Min: 144.6, Avg: 144.6, Max: 144.6, Diff: 0.0]
   [Code Root Fixup: 0.0 ms]
   [Code Root Purge: 0.0 ms]
   [Clear CT: 0.9 ms]
   [Other: 1.1 ms]
      [Choose CSet: 0.0 ms]
      [Ref Proc: 0.2 ms]
      [Ref Enq: 0.0 ms]
      [Redirty Cards: 0.6 ms]
      [Humongous Register: 0.0 ms]
      [Humongous Reclaim: 0.0 ms]
      [Free CSet: 0.0 ms]
   [Eden: 89.0M(89.0M)->0.0B(89.0M) Survivors: 13.0M->13.0M Heap: 167.9M(2048.0M)->91.5M(2048.0M)]
 [Times: user=0.00 sys=0.00, real=0.01 secs] 
    ......
   (```)

   
    2G内存时，运行结果大概在23110次。
    分别加大堆内存，4g时，结果为25046次，8g为26601次，也就是说，在加大堆内存时，对这个测试案例来说，G1GC性能有所提升。

   ###增加-XX:MaxGCPauseMillis 参数，
  >`java -classpath "D:\tech\geek;" -Xloggc:gc.g1-2048.log -Xmx2g -Xms2g -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis`

   执行结果与没有加此项参数时，无差别。

#总结&分析
 
 以上各类GC, 在机器1的执行测试程序的结果如下：

 GC类型（执行次数| 使用内存8G | 内存4G | 内存2G| 内存1G| 内存512M
----------------|:-------- :|------:|------:|------:|---------
Serial          |   18991   | 21861 |20950  |20383  |12305 
CMS             |   19116   | 23362 |21093  |20067  |12225 
ParNew          |   22992   | 20498 |20695  |20260  |12377 
Parallel        |   23672   | 28967 |25305  |22592  |10747 
G1GC            |   26691   | 25046 |23110  |22592  |12786 

在JDK8下，针对本次的测试问题，G1GC增大堆内存能提升性能。
Serial，CMS在小内存下（1g,2g)的性能较好，性价比较高。
在大内存下，G1GC性能向好。
整体而言，就这个测试问题，在jdk8下，ParallelGC性能最佳，G1GC次之。





