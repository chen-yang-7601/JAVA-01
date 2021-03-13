##### **Week08 作业题目（周三）：**

**1.（选做）**分析前面作业设计的表，是否可以做垂直拆分。

  分析：

  垂直拆分是按业务分类的维度去对数据做拆分的。在第6-7周所设计的简单电商系统DB中，

客户信息的增长量是远远低于订单数据的，可以按业务的维度来做垂直拆分，分为三个DB：

-    客户DB: 包含customer表，以后可增加客户地址等信息
-    商品DB: 包含category, goods, store ,以后可包括商品快照等资料表
- ​    订单DB，包含orders,order_details, simpleorders等table，以后可以包含订单快照资料表

 做了垂直拆分后，相应的资料关联程序需要做修改。

**2.（必做）**设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

  采用shardingSphere-proxy做拆分，对订单表(simpleorders)的user_Id用2取模，分成2个库，之后将订单号按16取模，分成16个表. --代码尚未实现

  

#### **Week08 作业题目（周日）：**

**1.（选做）**列举常见的分布式事务，简单分析其使用场景和优缺点。

######         参见[overview_of_distributed_transaction.md](overview_of_distributed_transaction.md)

**2.（必做）**基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。 --未完成

```

```



```

```



