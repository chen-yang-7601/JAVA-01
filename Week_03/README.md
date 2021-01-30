

## 作业说明：

参考老师提供的模板程序，

### 1. 周三作业：（必做）整合上次作业的httpclient， 实现Netty服务器端与httpClient5整合

采用Netty推荐的主从Reactor 模式.

Accpetor : 监听并接收客户端的请求，并将请求转发给Handler

Handler: 一般是一个线程池进行后续I/O、编解码及相关处理



因为apacha HttpClient5与HttpClient4有较大的语法差异，即便参考老师的模板程序，花费时间也较多。

### 2. 周日作业：（必做）实现过滤器      添加router 与filter.

​        执行结果

​       启动第二节课提交的HttpServer01,8801,   HttpServer02:8802, HttpServer03:8803,

​        均能经过router顺利转发，并且在Response中，显示了在HttpResponseFilter中添加的内容kk. 

```
PS C:\Users\Administrator> curl http://127.0.01:8888


StatusCode        : 200
StatusDescription : OK
Content           : hello, nio3
RawContent        : HTTP/1.1 200 OK
                    kk: java-1-nio
                    Content-Length: 11
                    Content-Type: text/html;charset=utf-8

                    hello, nio3
Forms             : {}
Headers           : {[kk, java-1-nio], [Content-Length, 11], [Content-Type, text/html;charset=utf-8]}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 11

```

 但存在的问题是，若以NettyHttpServer作为一个Proxyserver, 转发的Http请求执行时，出现读数据的错误，问题尚未解决：

```
Open netty HttpServer, listen address and port:http://127.0.0.1:8808
java.io.IOException: 远程主机强迫关闭了一个现有的连接。
	at sun.nio.ch.SocketDispatcher.read0(Native Method)
	at sun.nio.ch.SocketDispatcher.read(SocketDispatcher.java:43)
	at sun.nio.ch.IOUtil.readIntoNativeBuffer(IOUtil.java:223)
	at sun.nio.ch.IOUtil.read(IOUtil.java:192)
	at sun.nio.ch.SocketChannelImpl.read(SocketChannelImpl.java:378)
	at io.netty.buffer.PooledByteBuf.setBytes(PooledByteBuf.java:253)
	at io.netty.buffer.AbstractByteBuf.writeBytes(AbstractByteBuf.java:1134)
	at io.netty.channel.socket.nio.NioSocketChannel.doReadBytes(NioSocketChannel.java:350)
```

