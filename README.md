# 手机号码归属地数据处理工具
初始化数据存在了一个CSV文件, 文件较大，就不上传到github了， 格式遵循如下：
 * 1	1300000	山东	济南	中国联通	531	250000
 * 2	1300001	江苏	常州	中国联通	519	213000
 * 3	1300002	安徽	巢湖	中国联通	551	238000
 * 4	1300003	四川	宜宾	中国联通	831	644000
 * 5	1300004	四川	自贡	中国联通	813	643000

## 功能解释
- 把CSV转换成数据库的insert语句

```java
java -jar PhoneOwner.jar <sourceFile Path> sqlfile <targetFile Path>
```

- 把CSV的内容直接插入到目标mysql数据库

```java
java -jar PhoneOwner.jar <sourceFile Path> mysql <host::port::db::user::password>
```

- 把CSV的内容解析后post到远程地址

```java
java -jar PhoneOwner.jar <sourceFile Path> restful <remote URL>
```


## TODO
- 把记录插入到目标redis数据库
- 把CSV转换成excel
- 可以根据某个字段排序后重新输出成CSV文件

## Reference
可以到如下地址下载归属地文件：<https://download.csdn.net/download/javalover_yao/10504240>