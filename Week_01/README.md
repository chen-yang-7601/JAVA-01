本节主要是学习理解Java虚拟机加载执行Class的过程。
一个Java的类在虚拟机中的加载有如下5个阶段组成
1. 加载 Loading
2. 校验 Verification
3. 准备 Preparation
4. 解析 Resolution
5. 初始化 Initialization

其中，在加载阶段，虚拟机要完成如下三个事情：
1. 通过类的限定名来获取此类的二进制字节流
2. 将字节流所代表的静态数据转化为方法区的运行时数据结构（... ）
3. 在内存中生成一个代表这个类的java.lang.Class对象，在方法区就可以访问这个类了（通过其类、方法、属性等）

本次的作业是要实现自定义的Classloader，加载一个 Hello.xlass 文件，执行 hello 方法， 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
分析： 从要求来看，因为从字节层面，类文件做了变更，就需要在加载字节时做反向处理，以便Java class文件通过校验。
所以，需要在调用java.lang.ClassLoader的defineClass()方法之前，去控制字节流的获取方式。


