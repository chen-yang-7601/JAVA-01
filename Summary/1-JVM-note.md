---
typora-root-url: images
---

### JVM 总结

​      Java从诞生之初，其关键点和设计思想是：一次编写，到处执行。JVM(java virtual machine)是Java程序运行的虚拟机平台，与Java支持库一期构成了Java程序的执行环境。JVM隔离了硬件平台与操作系统的差异，使得Java程序具备了跨平台执行的能力。因此，推测JVM的主要职责应该要具有如下的职责：

####     1. 支持java 字节码的解析： 

​       为了在独立的VM上执行，需要建立一套独立于操作系统的操作数和内存模型，使得代码不是直接与操作系统打交道，而是通过虚拟机来执行。为此，建立一套独立的字节码(java bytecode)，用于将编译后的程序流程，以便于转换为各个硬件平台的本地代码，及做运行期指令重排。

​       JAVA的字节码(java bytecode)由单字节（byte)的指令组成，分为栈操作指令、程序流程控制指令、对象操作指令，包括方法调用指令，算术运算及类型转换指令等。在编译源程序生成ATS(抽象语法树)之后，生成字节码，JVM装卸、解析字节码文件，分配内存空间等。

2. #### 执行Java内存管理功能

​       Java语言不提供指针，所有的内存交由JVM来管理，所以JVM需要有内存管理机制，进行内存的分配、回收、压缩等处理。 JVM规范了线程之间的变量操作，包括其读取、写入、同步等，结合CPU的指令集，在特定语境下限制CPU指令重排（volatile关键字）。屏蔽了各种硬件平台和操作系统之间的内存/外设访问差异，为Java高并发提供了能力基础。

​       其最为诟病及最为称道的GC机制，让Java程序员可以不必去像C++程序员一样做内存管理，而是交由JVM做内存管理。 GC机制不断在演化中，从串行化GC--> 并行化 --> 局部并行化，算法从复制算法-->标记清除算法，到混合使用的算法。使得JAVA语言越来越健壮，加上开发效率高，因而Java在服务器端应用中被广泛采用。

​      在实际应用中，为了更好的服务于应用，提升运行效率，各个JDK提供一些JVM工具，而人们也从原理和实践角度给出了JVM参数管理、性能调优的参考。 JVM的一些知识点的整理如下图:

​      ![https://github.com/changanjennifer/JAVA-01/tree/main/Summary/images/JVM-summary.png](https://github.com/changanjennifer/JAVA-01/tree/main/Summary/images/JVM-summary.png)

​      ![https://github.com/changanjennifer/JAVA-01/tree/main/Summary/images/JVM-summary.png](https://github.com/changanjennifer/JAVA-01/blob/main/Summary/images/JVM-summary.png)
