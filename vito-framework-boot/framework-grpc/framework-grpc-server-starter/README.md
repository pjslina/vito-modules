### grpc服务端公共包

* 引入日志包，logback和log4j2二选一，需要使用者修改pom文件或者排除依赖，默认使用的是log4j2的包
* grpc的异常处理，结合了BaseCode的规范，将错误消息封装在Metadata中，其String类型数据使用Base64编码，使用者需要自行解码，主要是为了解决中文乱码的问题