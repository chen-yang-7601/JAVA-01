Project中有src 对应目录下有三个练习的HttpServer程序，和一个HttpClient的客户端程序

1. HttpServer01: 

   启动单线程监听服务client的Http server，绑定8801端口

2. HttpServer02:

   启动多线程监听服务的Http Server，绑定8802端口

3. HttpServer03:

    启动一个固定的线程池服务client的Http Server, 绑定8803端口

4. Requests: 

   用Apache HttpClient 5.0开发的Http client，分别访问三个上述的服务。

   参考： http://hc.apache.org/httpcomponents-client-5.0.x/httpclient5/xref-test/org/apache/hc/client5/http/examples/ClientWithResponseHandler.html

   将上述三个server程序都启动后，执行Requests程序，结果附图如下：

   ![](E:\03-Jennifer\GeekJAVA\Week_02\Sunday-work\java01\Requests-.PNG)

   

   

   

   

   

