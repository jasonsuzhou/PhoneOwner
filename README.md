# �ֻ�������������ݴ�����
��ʼ�����ݴ�����һ��CSV�ļ�, �ļ��ϴ󣬾Ͳ��ϴ���github�ˣ� ��ʽ��ѭ���£�
 * 1	1300000	ɽ��	����	�й���ͨ	531	250000
 * 2	1300001	����	����	�й���ͨ	519	213000
 * 3	1300002	����	����	�й���ͨ	551	238000
 * 4	1300003	�Ĵ�	�˱�	�й���ͨ	831	644000
 * 5	1300004	�Ĵ�	�Թ�	�й���ͨ	813	643000

## ���ܽ���
- ��CSVת�������ݿ��insert���

```java
java -jar PhoneOwner.jar <sourceFile Path> sqlfile <targetFile Path>
```

- ��CSV������ֱ�Ӳ��뵽Ŀ��mysql���ݿ�

```java
java -jar PhoneOwner.jar <sourceFile Path> mysql <host::port::db::user::password>
```

- ��CSV�����ݽ�����post��Զ�̵�ַ

```java
java -jar PhoneOwner.jar <sourceFile Path> restful <remote URL>
```


## TODO
- �Ѽ�¼���뵽Ŀ��redis���ݿ�
- ��CSVת����excel
- ���Ը���ĳ���ֶ���������������CSV�ļ�

## Reference
���Ե����µ�ַ���ع������ļ���<https://download.csdn.net/download/javalover_yao/10504240>